package com.aerosecgeek.emailthreatlensservice.modules.analysis;

import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisOutcome;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisStatus;
import com.aerosecgeek.emailthreatlensservice.modules.email.model.Email;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("unittest")
@Transactional
@Rollback
class OverallEmailAnalysisResultServiceTest {
    @Autowired
    private OverallEmailAnalysisResultService overallEmailAnalysisResultService;

    @Test
    void givenService_whenCreateNewResult_thenResultCreated(){
        // given
        Email email = new Email();
        email.setUuid(UUID.randomUUID());

        // when
        var result = overallEmailAnalysisResultService.createNewResult(email);

        // then
        assertNotNull(result);
        assertNotNull(result.getUuid());
        assertEquals(AnalysisStatus.PENDING,result.getBodyAnalysisResult().getStatus());
        assertEquals(AnalysisOutcome.UNKNOWN,result.getHeaderAnalysisResult().getOutcome());
    }

    @Test
    void givenService_whenSaveResult_thenResultSaved(){
        // given
        Email email = new Email();
        email.setUuid(UUID.randomUUID());
        var result = overallEmailAnalysisResultService.createNewResult(email);

        // when
        result.getBodyAnalysisResult().setStatus(AnalysisStatus.COMPLETED);
        result.getHeaderAnalysisResult().setOutcome(AnalysisOutcome.CLEAN);
        var savedResult = overallEmailAnalysisResultService.saveResult(result);

        // then
        assertNotNull(savedResult);
        assertNotNull(savedResult.getUuid());
        assertEquals(AnalysisStatus.COMPLETED,savedResult.getBodyAnalysisResult().getStatus());
        assertEquals(AnalysisOutcome.CLEAN,savedResult.getHeaderAnalysisResult().getOutcome());
    }

}