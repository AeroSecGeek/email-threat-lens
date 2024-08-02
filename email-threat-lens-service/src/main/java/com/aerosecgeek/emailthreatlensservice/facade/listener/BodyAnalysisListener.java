package com.aerosecgeek.emailthreatlensservice.facade.listener;

import com.aerosecgeek.emailthreatlensservice.core.event.EventPublisher;
import com.aerosecgeek.emailthreatlensservice.core.event.model.AnalysisCompletedEvent;
import com.aerosecgeek.emailthreatlensservice.core.event.model.StartAnalysisEvent;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.OverallEmailAnalysisResultService;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.body.BodyAnalysisService;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisStatus;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BodyAnalysisListener {
    private final OverallEmailAnalysisResultService overallEmailAnalysisResultService;
    private final BodyAnalysisService bodyAnalysisService;
    private final EventPublisher eventPublisher;

    @EventListener
    @Async
    public void handleStartAnalysisEvent(StartAnalysisEvent event){
        var result = event.getResult();
        result.getBodyAnalysisResult().setStatus(AnalysisStatus.IN_PROGRESS);
        result = overallEmailAnalysisResultService.saveResult(result);

        var analysisResult = bodyAnalysisService.analyzeContent(result);
        result.getBodyAnalysisResult().setStatus(AnalysisStatus.COMPLETED);
        result.getBodyAnalysisResult().setOutcome(analysisResult);
        overallEmailAnalysisResultService.saveResult(result);

        eventPublisher.publishDomainEvent(new AnalysisCompletedEvent(this, result));
    }
}
