package com.aerosecgeek.emailthreatlensservice.modules.virustotal;

import com.aerosecgeek.emailthreatlensservice.modules.virustotal.model.VirusTotalAnalysisResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface VirusTotalAnalysisResultRepository extends JpaRepository<VirusTotalAnalysisResult, UUID> {
    Optional<VirusTotalAnalysisResult> findByUrl(String url);
}
