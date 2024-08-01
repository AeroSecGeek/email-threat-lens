package com.aerosecgeek.emailthreatlensservice.core.event.model;

import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.OverallEmailAnalysisResult;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.io.Serial;

@Getter
public class StartAnalysisEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = 2454319070943904735L;
    private final OverallEmailAnalysisResult result;

    public StartAnalysisEvent(Object source, OverallEmailAnalysisResult result) {
        super(source);
        this.result = result;
    }
}
