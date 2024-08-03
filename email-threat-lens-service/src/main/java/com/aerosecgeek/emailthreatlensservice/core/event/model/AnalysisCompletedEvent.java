package com.aerosecgeek.emailthreatlensservice.core.event.model;

import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.OverallEmailAnalysisResult;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.io.Serial;
import java.util.UUID;

@Getter
public class AnalysisCompletedEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = 7241503053939742369L;

    private final UUID resultUuid;

    public AnalysisCompletedEvent(Object source, UUID resultUuid) {
        super(source);
        this.resultUuid = resultUuid;
    }
}
