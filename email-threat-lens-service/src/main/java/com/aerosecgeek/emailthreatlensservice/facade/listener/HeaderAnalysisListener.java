package com.aerosecgeek.emailthreatlensservice.facade.listener;

import com.aerosecgeek.emailthreatlensservice.core.event.EventPublisher;
import com.aerosecgeek.emailthreatlensservice.core.event.model.AnalysisCompletedEvent;
import com.aerosecgeek.emailthreatlensservice.core.event.model.StartAnalysisEvent;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.OverallEmailAnalysisResultService;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.header.HeaderAnalysisService;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisResult;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisResultType;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisStatus;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HeaderAnalysisListener {
    private final OverallEmailAnalysisResultService overallEmailAnalysisResultService;
    private final HeaderAnalysisService headerAnalysisService;
    private final EventPublisher eventPublisher;

    @EventListener
    @Async
    public void handleStartAnalysisEvent(StartAnalysisEvent event){
        var result = event.getResult();
        overallEmailAnalysisResultService.updateAnalysisStatus(result.getUuid(),
                AnalysisResultType.HEADER, AnalysisStatus.IN_PROGRESS);

        var analysisOutcome = headerAnalysisService.analyzeHeaders(result.getEmail().getHeaders());
        var analysisResult = new AnalysisResult(AnalysisStatus.COMPLETED, analysisOutcome);
        overallEmailAnalysisResultService.updateAnalysisResult(result.getUuid(), analysisResult, AnalysisResultType.HEADER);

        eventPublisher.publishDomainEvent(new AnalysisCompletedEvent(this, result.getUuid()));

    }
}
