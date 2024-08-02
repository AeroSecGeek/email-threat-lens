package com.aerosecgeek.emailthreatlensservice.modules.analysis.body;

import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisOutcome;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.OverallEmailAnalysisResult;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BodyAnalysisService {
    public AnalysisOutcome analyzeContent(OverallEmailAnalysisResult result) {
        List<String> containedUrls = LinkExtractor.extractLinks(result.getEmail().getBody(),
                isBodyHtml(result.getEmail().getBody()));
        if(containedUrls.isEmpty()) {
            return AnalysisOutcome.CLEAN;
        }
        else{
            // TODO: Implement URL analysis
        }
        return AnalysisOutcome.CLEAN;
    }

    private boolean isBodyHtml(String body) {
        return body.contains("<html>") || body.contains("<body>");
    }
}
