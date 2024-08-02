package com.aerosecgeek.emailthreatlensservice.modules.analysis.header;

import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisOutcome;
import com.aerosecgeek.emailthreatlensservice.modules.email.model.EmailHeader;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeaderAnalysisServiceTest {
    private final HeaderAnalysisService analyzer = new HeaderAnalysisService();

    @Test
    void testAnalyzeHeaders_ValidHeaders_ReturnsClean() {
        // Arrange
        List<EmailHeader> headers = new ArrayList<>();
        headers.add(new EmailHeader("Authentication-Results".toLowerCase(), "mx.infomaniak.com; dmarc=pass (p=reject dis=none) header.from=mailings.brack.ch"));
        headers.add(new EmailHeader("Authentication-Results".toLowerCase(), "mx.infomaniak.com; dkim=pass (1024-bit key; unprotected) header.d=mailings.brack.ch"));
        headers.add(new EmailHeader("Authentication-Results".toLowerCase(), "mx.infomaniak.com; spf=pass smtp.mailfrom=mailings.brack.ch"));
        headers.add(new EmailHeader("From".toLowerCase(), "user@example.com"));
        headers.add(new EmailHeader("Reply-To".toLowerCase(), "user@example.com"));
        headers.add(new EmailHeader("Return-Path".toLowerCase(), "user@example.com"));
        headers.add(new EmailHeader("Content-Type".toLowerCase(), "text/html; charset=UTF-8"));

        // Act
        AnalysisOutcome outcome = analyzer.analyzeHeaders(headers);

        // Assert
        assertEquals(AnalysisOutcome.CLEAN, outcome);
    }

    @Test
    void testAnalyzeHeaders_suspiciousHeaders_ReturnsClean() {
        // Arrange
        List<EmailHeader> headers = new ArrayList<>();
        headers.add(new EmailHeader("Authentication-Results".toLowerCase(), "mx.infomaniak.com; dmarc=none (p=reject dis=none) header.from=mailings.brack.ch"));
        headers.add(new EmailHeader("Authentication-Results".toLowerCase(), "mx.infomaniak.com; dkim=none (1024-bit key; unprotected) header.d=mailings.brack.ch"));
        headers.add(new EmailHeader("Authentication-Results".toLowerCase(), "mx.infomaniak.com; spf=fail smtp.mailfrom=mailings.brack.ch"));

        // Act
        AnalysisOutcome outcome = analyzer.analyzeHeaders(headers);

        // Assert
        assertEquals(AnalysisOutcome.SUSPICIOUS, outcome);
    }

    @Test
    void testAnalyzeHeaders_UnexpectedAttachment_ReturnsMalware() {
        // Arrange
        List<EmailHeader> headers = new ArrayList<>();
        headers.add(new EmailHeader("Content-Type".toLowerCase(), "application/exe"));

        // Act
        AnalysisOutcome outcome = analyzer.analyzeHeaders(headers);

        // Assert
        assertEquals(AnalysisOutcome.MALWARE, outcome);
    }

    @Test
    void givenSpamHeaders_whenAnalyzeHeaders_thenReturnsSpam() {
        // Arrange
        List<EmailHeader> headers = new ArrayList<>();
        headers.add(new EmailHeader("X-Spam-Score".toLowerCase(), "51.0"));

        // Act
        AnalysisOutcome outcome = analyzer.analyzeHeaders(headers);

        // Assert
        assertEquals(AnalysisOutcome.SPAM, outcome);
    }

    @Test
    void testAnalyzeHeaders_SuspiciousReturnPath_ReturnsPhishing() {
        // Arrange
        List<EmailHeader> headers = new ArrayList<>();
        headers.add(new EmailHeader("From".toLowerCase(), "user@example.com"));
        headers.add(new EmailHeader("Reply-To".toLowerCase(), "user@exampllle.com"));
        headers.add(new EmailHeader("Return-Path".toLowerCase(), "user@exaaample.com"));

        // Act
        AnalysisOutcome outcome = analyzer.analyzeHeaders(headers);

        // Assert
        assertEquals(AnalysisOutcome.PHISHING, outcome);
    }
}