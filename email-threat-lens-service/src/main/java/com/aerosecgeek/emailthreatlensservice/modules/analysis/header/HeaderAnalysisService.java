package com.aerosecgeek.emailthreatlensservice.modules.analysis.header;

import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisOutcome;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@AllArgsConstructor
public class HeaderAnalysisService {
    public AnalysisOutcome analyzeHeaders(Map<String,String> headers) {

        return AnalysisOutcome.CLEAN;
    }
}
