package com.aerosecgeek.emailthreatlensservice.facade.listener;

import com.aerosecgeek.emailthreatlensservice.core.event.model.StartAnalysisEvent;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.OverallEmailAnalysisResultService;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisStatus;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HeaderAnalysisListener {
    private final OverallEmailAnalysisResultService overallEmailAnalysisResultService;

    @EventListener
    @Async
    public void handleStartAnalysisEvent(StartAnalysisEvent event){
        var result = event.getResult();
        result.getHeaderAnalysisResult().setStatus(AnalysisStatus.IN_PROGRESS);
        overallEmailAnalysisResultService.saveResult(result);

        // trigger analysis in new service
    }
}
