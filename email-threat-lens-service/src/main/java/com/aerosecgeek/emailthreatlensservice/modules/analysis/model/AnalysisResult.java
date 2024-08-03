package com.aerosecgeek.emailthreatlensservice.modules.analysis.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
public class AnalysisResult implements Serializable {
    @Serial
    private static final long serialVersionUID = -5158298565550124430L;

    @Enumerated(EnumType.STRING)
    private AnalysisStatus status;

    @Enumerated(EnumType.STRING)
    private AnalysisOutcome outcome;

    @Column(columnDefinition = "TEXT")
    private String description;

    public AnalysisResult() {
        this(AnalysisStatus.PENDING, AnalysisOutcome.UNKNOWN, "");
    }

    public AnalysisResult(AnalysisStatus status, AnalysisOutcome outcome) {
        this(status, outcome, "");
    }
}
