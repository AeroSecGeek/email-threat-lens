package com.aerosecgeek.emailthreatlensservice.modules.virustotal;

import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisOutcome;
import com.aerosecgeek.emailthreatlensservice.modules.testdata.VirusTotalResponse;
import com.aerosecgeek.emailthreatlensservice.modules.util.AbstractIntegrationTest;
import com.aerosecgeek.emailthreatlensservice.modules.virustotal.model.VirusTotalAnalysisResult;
import okhttp3.HttpUrl;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class VirusTotalServiceTest extends AbstractIntegrationTest {
    @Autowired
    private VirusTotalService virusTotalService;

    @Autowired
    private VirusTotalAnalysisResultRepository resultRepository;

    private MockWebServer mockWebServer;

    @BeforeEach
    void setUp() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterEach
    void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void givenUrl_whenTriggerUrlScan_thenReturnsAnalysisId() throws InterruptedException {
        // when
        String url = "www.google.com";
        setBaseUrlOfService();
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(VirusTotalResponse.triggerUrlScanResponse()));

        // when
        String analysisId = virusTotalService.triggerUrlScan(url);

        // then
        RecordedRequest request = mockWebServer.takeRequest();
        assertNotNull(analysisId);
        assertEquals("u-dd014af5ed6b38d9130e3f466f850e46d21b951199d53a18ef29ee9341614eaf-1722769335",analysisId);
        assertEquals(HttpMethod.POST.toString(),request.getMethod());
        assertEquals("/api/v3/urls",request.getPath());
    }

    @Test
    void givenAnalysisId_whenGetUrlReport_thenReturnsAnalysisOutcome() throws InterruptedException {
        // when
        String analysisId = "u-dd014af5ed6b38d9130e3f466f850e46d21b951199d53a18ef29ee9341614eaf-1722769335";
        setBaseUrlOfService();
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(VirusTotalResponse.getUrlAnalysisReportResponse()));

        // when
        AnalysisOutcome analysisOutcome = virusTotalService.getUrlAnalysisReport(analysisId);

        // then
        RecordedRequest request = mockWebServer.takeRequest();
        assertNotNull(analysisOutcome);
        assertEquals(HttpMethod.GET.toString(),request.getMethod());
        assertEquals("/api/v3/analyses/u-dd014af5ed6b38d9130e3f466f850e46d21b951199d53a18ef29ee9341614eaf-1722769335",request.getPath());
    }

    @Test
    void givenUrl_whenTriggerAndWaitForUrlScan_thenReturnsAnalysisOutcome() throws InterruptedException {
        // when
        String url = "www.google.com";
        setBaseUrlOfService();
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(VirusTotalResponse.triggerUrlScanResponse()));
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(VirusTotalResponse.getPendingUrlAnalysisReport()));
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(VirusTotalResponse.getUrlAnalysisReportResponse()));

        // when
        AnalysisOutcome analysisOutcome = virusTotalService.getOutcomeForUrl(url);

        // then
        RecordedRequest request = mockWebServer.takeRequest();
        assertNotNull(analysisOutcome);
        assertEquals(HttpMethod.POST.toString(),request.getMethod());
        assertEquals("/api/v3/urls",request.getPath());
        assertEquals(AnalysisOutcome.CLEAN,analysisOutcome);
    }

    @Test
    void givenUrl_whenFindResultForUrl_thenReturnsAnalysisResult() {
        // when
        String url = "www.google.com";
        var result = new VirusTotalAnalysisResult();
        result.setUrl(url);
        result.setAnalysisId("u-dd014af5ed6b38d9130e3f466f850e46d21b951199d53a18ef29ee9341614eaf-1722769335");
        result = resultRepository.save(result);

        // when
        var analysisResult = virusTotalService.findResultForUrl(url);

        // then
        assertNotNull(analysisResult);
        assertEquals(result.getUuid(),analysisResult.getUuid());
        assertEquals(result.getUrl(),analysisResult.getUrl());
        assertEquals(result.getAnalysisId(),analysisResult.getAnalysisId());
        assertNotNull(analysisResult.getLastScanDate());
    }

    @Test
    void givenUrlAndResultNewerThan24Hours_whenGetOutcomeForUrl_thenReturnsAnalysisOutcome() throws InterruptedException {
        // when
        setBaseUrlOfService();
        String url = "www.google.com";
        var result = new VirusTotalAnalysisResult();
        result.setUrl(url);
        result.setAnalysisId("u-dd014af5ed6b38d9130e3f466f850e46d21b951199d53a18ef29ee9341614eaf-1722769335");
        result.setLastScanDate(Date.from(new Date().toInstant().minusSeconds(60)));
        result.setCompleted(true);
        result.setOutcome(AnalysisOutcome.CLEAN);
        resultRepository.save(result);

        // when
        var analysisOutcome = virusTotalService.getOutcomeForUrl(url);

        // then
        assertNotNull(analysisOutcome);
        assertEquals(AnalysisOutcome.CLEAN,analysisOutcome);
        assertEquals(0, mockWebServer.getRequestCount());
    }

    @Test
    void givenUrlAndAnalysisOlder24Hours_whenGetOutcomeForUrl_thenReturnsAnalysisOutcome() throws InterruptedException {
        // when
        setBaseUrlOfService();
        String url = "www.google.com";
        var result = new VirusTotalAnalysisResult();
        result.setUrl(url);
        result.setAnalysisId("u-dd014af5ed6b38d9130e3f466f850e46d21b951199d53a18ef29ee9341614eaf-1722769335");
        result.setLastScanDate(Date.from(new Date().toInstant().minusSeconds(86400)));
        result.setCompleted(true);
        result.setOutcome(AnalysisOutcome.CLEAN);
        resultRepository.save(result);
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(VirusTotalResponse.triggerUrlScanResponse()));
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(VirusTotalResponse.getUrlAnalysisReportResponse()));

        // when
        var analysisOutcome = virusTotalService.getOutcomeForUrl(url);

        // then
        assertNotNull(analysisOutcome);
        assertEquals(AnalysisOutcome.CLEAN,analysisOutcome);
        assertEquals(2, mockWebServer.getRequestCount());
    }

    private void setBaseUrlOfService(){
        HttpUrl apiUrl = mockWebServer.url("");
        virusTotalService.setBaseUrl(apiUrl.toString());
    }
}