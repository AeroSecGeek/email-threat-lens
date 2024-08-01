package com.aerosecgeek.emailthreatlensservice.modules.analysis;

import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.OverallEmailAnalysisResult;
import com.aerosecgeek.emailthreatlensservice.modules.email.model.Email;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class OverallEmailAnalysisResultService {
    private final OverallEmailAnalysisResultRepository overallEmailAnalysisResultRepository;

    public OverallEmailAnalysisResult createNewResult(Email email){
        OverallEmailAnalysisResult result = new OverallEmailAnalysisResult(email);
        return overallEmailAnalysisResultRepository.save(result);
    }

    public OverallEmailAnalysisResult saveResult(OverallEmailAnalysisResult result){
        return overallEmailAnalysisResultRepository.save(result);
    }
}
