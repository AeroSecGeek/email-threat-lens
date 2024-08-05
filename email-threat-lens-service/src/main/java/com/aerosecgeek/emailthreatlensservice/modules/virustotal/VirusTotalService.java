package com.aerosecgeek.emailthreatlensservice.modules.virustotal;

import com.aerosecgeek.emailthreatlensservice.core.exception.AnalysisFailedException;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisOutcome;
import com.aerosecgeek.emailthreatlensservice.modules.util.DateUtils;
import com.aerosecgeek.emailthreatlensservice.modules.virustotal.model.VirusTotalAnalysisResult;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class VirusTotalService {
    @Value("${virustotal.api.key}")
    private String apiKey;

    @Setter
    private String baseUrl = "https://www.virustotal.com";

    private final WebClient.Builder webClientBuilder;
    private final VirusTotalAnalysisResultRepository virusTotalAnalysisResultRepository;

    @Autowired
    public VirusTotalService(WebClient.Builder webClientBuilder,
                             VirusTotalAnalysisResultRepository virusTotalAnalysisResultRepository) {
        this.webClientBuilder = webClientBuilder;
        this.virusTotalAnalysisResultRepository = virusTotalAnalysisResultRepository;
    }

    public AnalysisOutcome getUrlAnalysisReport(String analysisId) {
        String apiUrl =baseUrl +"/api/v3/analyses";

        WebClient webClient = getWebClient(apiUrl);

        ResponseEntity<String> response = webClient.get()
                .uri("/" + analysisId)
                .retrieve()
                .toEntity(String.class)
                .block();
        if(response == null || response.getBody() == null) {
            throw new AnalysisFailedException("VirusTotal API response is null");
        }
        return analyzeResponse(response.getBody());
    }

    public String triggerUrlScan(String url){
        String apiUrl =baseUrl+"/api/v3/urls";

        WebClient webClient = getWebClient(apiUrl);


        ResponseEntity<String> response = webClient.post()
                .header(HttpHeaders.ACCEPT,MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .body(BodyInserters.fromFormData("url", url))
                .retrieve()
                .toEntity(String.class)
                .block();
        if(response == null || response.getBody() == null) {
            throw new AnalysisFailedException("VirusTotal API response is null");
        }
        return getAnalysisIdFromResponse(response.getBody());
    }

    public AnalysisOutcome getOutcomeForUrl(String url) throws InterruptedException {
        VirusTotalAnalysisResult existingResult = findResultForUrl(url);
        if (existingResult != null &&
                existingResult.isCompleted() &&
                !DateUtils.isOlderThan24Hours(existingResult.getLastScanDate())) {
            return existingResult.getOutcome();
        } else {
            existingResult = triggerAndWaitForUrlAnalysis(url, existingResult);
        }
        virusTotalAnalysisResultRepository.save(existingResult);
        return existingResult.getOutcome();
    }

    public VirusTotalAnalysisResult triggerAndWaitForUrlAnalysis(String url, VirusTotalAnalysisResult existingResult) throws InterruptedException {
        if(existingResult == null) {
            existingResult = new VirusTotalAnalysisResult();
            existingResult.setUrl(url);
        }
        log.info("Triggering URL analysis for {}", url);
        String analysisId = triggerUrlScan(url);
        existingResult.setAnalysisId(analysisId);
        existingResult.setCompleted(false);
        AnalysisOutcome outcome = null;
        int maxRetries = 60;
        int retryCount = 0;

        while (retryCount < maxRetries) {
            outcome = getUrlAnalysisReport(analysisId);
            if (outcome != null) {
                log.info("Analysis outcome for {}: completed with {}", url,outcome);
                break;
            }
            TimeUnit.SECONDS.sleep(1);
            retryCount++;
        }
        if (outcome == null) {
            throw new AnalysisFailedException("VirusTotal analysis timed out");
        }
        existingResult.setOutcome(outcome);
        existingResult.setLastScanDate(new Date());
        existingResult.setCompleted(true);
        return existingResult;
    }

    public VirusTotalAnalysisResult findResultForUrl(String url) {
        return virusTotalAnalysisResultRepository.findByUrl(url).orElse(null);
    }

    private AnalysisOutcome analyzeResponse(String responseBody) {
        JSONObject jsonResponse = new JSONObject(responseBody);
        JSONObject data = jsonResponse.optJSONObject("data");
        if (data == null) {
            return AnalysisOutcome.FAILED;
        }

        JSONObject attributes = data.optJSONObject("attributes");
        if (attributes == null) {
            return AnalysisOutcome.FAILED;
        }

        String status = attributes.optString("status");

        if(!"completed".equals(status)) {
            return null;
        }

        JSONObject stats = attributes.optJSONObject("stats");
        if(stats == null) {
            return AnalysisOutcome.FAILED;
        }

        int harmless = stats.optInt("harmless", 0);
        int maliciousCount = stats.optInt("malicious", 0);
        int suspiciousCount = stats.optInt("suspicious", 0);
        int total = harmless + maliciousCount + suspiciousCount;

        double maliciousRatio = (double) maliciousCount / total;
        double suspiciousRatio = (double) suspiciousCount / total;
        double harmlessRatio = (double) harmless / total;

        if (maliciousRatio >= suspiciousRatio && maliciousRatio >= harmlessRatio) {
            return AnalysisOutcome.MALWARE;
        } else if (suspiciousRatio >= maliciousRatio && suspiciousRatio >= harmlessRatio) {
            return AnalysisOutcome.SUSPICIOUS;
        } else {
            if (maliciousRatio >= 0.1) {
                return AnalysisOutcome.MALWARE;
            } else if (suspiciousRatio >= 0.1) {
                return AnalysisOutcome.SUSPICIOUS;
            }
            return AnalysisOutcome.CLEAN;
        }
    }

    private WebClient getWebClient(String baseUrl) {
        return webClientBuilder
                .baseUrl(baseUrl)
                .defaultHeader("x-apikey", apiKey)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    private String getAnalysisIdFromResponse(String responseBody) {
        JSONObject jsonResponse = new JSONObject(responseBody);
        JSONObject data = jsonResponse.optJSONObject("data");
        if (data == null) {
            return null;
        }
        return data.optString("id");
    }
}
