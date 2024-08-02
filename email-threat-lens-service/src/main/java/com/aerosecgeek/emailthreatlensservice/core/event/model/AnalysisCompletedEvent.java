package com.aerosecgeek.emailthreatlensservice.core.event.model;

import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.OverallEmailAnalysisResult;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.io.Serial;

@Getter
public class AnalysisCompletedEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = 7241503053939742369L;

    private final OverallEmailAnalysisResult result;

    public AnalysisCompletedEvent(Object source, OverallEmailAnalysisResult result) {
        super(source);
        this.result = result;
    }
}
