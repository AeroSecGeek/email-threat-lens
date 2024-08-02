package com.aerosecgeek.emailthreatlensservice.modules.analysis.header;

import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.AnalysisOutcome;
import com.aerosecgeek.emailthreatlensservice.modules.email.model.EmailHeader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@AllArgsConstructor
public class HeaderAnalysisService {
    public AnalysisOutcome analyzeHeaders(List<EmailHeader> headers) {
        if (areCommonHeadersSuspicious(headers)) {
            return AnalysisOutcome.SUSPICIOUS;
        }

        if (containsUnexpectedAttachment(headers)) {
            return AnalysisOutcome.MALWARE;
        }

        if (isSpamScoreHigherThanThreshold(headers)) {
            return AnalysisOutcome.SPAM;
        }

        if (!isSenderInfoConsistent(headers)) {
            return AnalysisOutcome.PHISHING;
        }
        return AnalysisOutcome.CLEAN;
    }

    private boolean areCommonHeadersSuspicious(List<EmailHeader> headers) {
        AtomicBoolean isSuspicious = new AtomicBoolean(false);
        headers.forEach(header -> {
            if(header.key().contains("Authentication-Results".toLowerCase())){
                var value = header.value();
                if(value!=null && value.contains("spf") && !value.contains("pass")){
                        isSuspicious.set(true);
                    }

                if(value!=null && value.contains("dkim") && !(value.contains("pass")||value.contains("none"))){
                        isSuspicious.set(true);
                    }
                if(value!=null && value.contains("dmarc") && !(value.contains("pass")||value.contains("none"))){
                        isSuspicious.set(true);
                    }
            }
        });
        return isSuspicious.get();
    }

    private boolean isSenderInfoConsistent(List<EmailHeader> headers) {
        String from = headers.stream().filter(header -> header.key().toLowerCase().contains("From".toLowerCase())).map(EmailHeader::value).findFirst().orElse(null);
        String replyTo = headers.stream().filter(header -> header.key().toLowerCase().contains("Reply-To".toLowerCase())).map(EmailHeader::value).findFirst().orElse(null);
        String returnPath = headers.stream().filter(header -> header.key().toLowerCase().contains("Return-Path".toLowerCase())).map(EmailHeader::value).findFirst().orElse(null);

        Set<String> domains = new HashSet<>();
        if(from!=null){
            domains.add(extractDomain(from));
        }
        if(replyTo!=null){
            domains.add(extractDomain(replyTo));
        }
        if(returnPath!=null){
            domains.add(extractDomain(returnPath));
        }

        return domains.size() <= 1;
    }

    private boolean containsUnexpectedAttachment(List<EmailHeader> headers) {
        AtomicBoolean containsUnexpectedAttachment = new AtomicBoolean(false);
        headers.forEach(header -> {
            if (header.key().toLowerCase().contains("Content-Type".toLowerCase())) {
                var value = header.value();
                if (value != null && (value.contains("application/exe") || value.contains("application/zip"))) {
                    containsUnexpectedAttachment.set(true);
                }
            }
        }
        );
        return containsUnexpectedAttachment.get();
    }

    private boolean isSpamScoreHigherThanThreshold(List<EmailHeader> headers) {
        AtomicBoolean isSpam = new AtomicBoolean(false);
        headers.forEach(header -> {
            if (header.key().toLowerCase().contains("X-Spam-Score".toLowerCase())) {
                var value = header.value();
                if (value != null && Double.parseDouble(value) > 50.0) {
                    isSpam.set(true);
                }
            }
        });
       return isSpam.get();
    }

    private String extractDomain(String email){
        return email.substring(email.indexOf('@')+1);
    }
}
