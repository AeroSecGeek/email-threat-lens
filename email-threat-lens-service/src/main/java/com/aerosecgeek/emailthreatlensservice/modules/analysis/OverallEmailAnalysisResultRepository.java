package com.aerosecgeek.emailthreatlensservice.modules.analysis;

import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.OverallEmailAnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface OverallEmailAnalysisResultRepository extends JpaRepository<OverallEmailAnalysisResult, UUID> {
}
