package com.aerosecgeek.emailthreatlensservice.modules.analysis.header;

import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisOutcome;
import com.aerosecgeek.emailthreatlensservice.modules.email.model.EmailHeader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class HeaderAnalysisService {
    public AnalysisOutcome analyzeHeaders(List<EmailHeader> headers) {
        
        return AnalysisOutcome.CLEAN;
    }
}
