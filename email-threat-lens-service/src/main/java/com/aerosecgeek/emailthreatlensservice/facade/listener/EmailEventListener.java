package com.aerosecgeek.emailthreatlensservice.facade.listener;

import com.aerosecgeek.emailthreatlensservice.core.event.EventPublisher;
import com.aerosecgeek.emailthreatlensservice.core.event.model.EmailSavedEvent;
import com.aerosecgeek.emailthreatlensservice.core.event.model.StartAnalysisEvent;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.OverallEmailAnalysisResultService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailEventListener {
    private final OverallEmailAnalysisResultService overallEmailAnalysisResultService;
    private final EventPublisher eventPublisher;

    @EventListener
    @Async
    public void handleEmailSavedEvent(EmailSavedEvent event){
        var result = overallEmailAnalysisResultService.createNewResult(event.getEmail());
        eventPublisher.publishDomainEvent(new StartAnalysisEvent(this,result));
    }
}
