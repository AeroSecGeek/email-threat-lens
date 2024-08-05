package com.aerosecgeek.emailthreatlensservice.modules.virustotal.model;

import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisOutcome;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class VirusTotalAnalysisResult {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String analysisId;
    private String url;
    private boolean completed;
    private Date lastScanDate = new Date();
    @Enumerated(EnumType.STRING)
    private AnalysisOutcome outcome;
}
