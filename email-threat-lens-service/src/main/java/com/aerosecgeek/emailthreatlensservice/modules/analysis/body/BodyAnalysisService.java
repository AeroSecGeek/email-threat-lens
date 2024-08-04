package com.aerosecgeek.emailthreatlensservice.modules.analysis.body;

import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisOutcome;
import com.aerosecgeek.emailthreatlensservice.modules.email.model.Email;
import com.aerosecgeek.emailthreatlensservice.modules.virustotal.VirusTotalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@AllArgsConstructor
public class BodyAnalysisService {
    private final VirusTotalService virusTotalService;

    public AnalysisOutcome analyzeContent(Email email) {
        List<String> containedUrls = LinkExtractor.extractLinks(email.getBody(),
                isBodyHtml(email.getBody()));
        if(containedUrls.isEmpty()) {
            return AnalysisOutcome.CLEAN;
        }
        else{
            List<AnalysisOutcome> outcomes = new ArrayList<>();
            for (String url : containedUrls) {
                try {
                    outcomes.add(virusTotalService.triggerAndWaitForUrlAnalysis(url));
                } catch (InterruptedException e) {
                    log.error("Error while waiting for URL analysis", e);
                    Thread.currentThread().interrupt();
                }
            }
            return analyzeOutcomes(outcomes);
        }
    }

    private boolean isBodyHtml(String body) {
        return body.contains("<html>") || body.contains("<body>");
    }

    private AnalysisOutcome analyzeOutcomes(List<AnalysisOutcome> outcomes) {
        Map<AnalysisOutcome, Integer> outcomeCount = new EnumMap<>(AnalysisOutcome.class);
        for (AnalysisOutcome outcome : outcomes) {
            outcomeCount.put(outcome, outcomeCount.getOrDefault(outcome, 0) + 1);
        }
        AnalysisOutcome mostFrequentOutcome = null;
        int maxCount = 0;

        for (Map.Entry<AnalysisOutcome, Integer> entry : outcomeCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                mostFrequentOutcome = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return mostFrequentOutcome;
    }
}
