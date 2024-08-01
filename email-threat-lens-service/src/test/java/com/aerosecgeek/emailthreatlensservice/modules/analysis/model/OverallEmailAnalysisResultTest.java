package com.aerosecgeek.emailthreatlensservice.modules.analysis.model;

import com.aerosecgeek.emailthreatlensservice.modules.email.model.Email;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OverallEmailAnalysisResultTest {
    @Test
    void givenOverallEmailAnalysisResult_whenGetterSetter_thenSuccessful() {
        // given
        OverallEmailAnalysisResult overallEmailAnalysisResult = new OverallEmailAnalysisResult(new Email());

        // when
        overallEmailAnalysisResult.setEmail(new Email());

        // then
        assertNotNull(overallEmailAnalysisResult.getEmail());
        assertEquals(AnalysisStatus.PENDING, overallEmailAnalysisResult.getHeaderAnalysisResult().getStatus());
        assertNotNull(overallEmailAnalysisResult.getEmail());
    }

}