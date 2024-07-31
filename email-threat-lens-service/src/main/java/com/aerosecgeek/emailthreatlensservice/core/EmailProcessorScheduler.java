package com.aerosecgeek.emailthreatlensservice.core;

import com.aerosecgeek.emailthreatlensservice.modules.email.InboxFetcherService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailProcessorScheduler {
    private final InboxFetcherService inboxFetcherService;

    @Scheduled(fixedRate = 20000) // Every 20 seconds
    public void scheduleEmailProcessing() {
        inboxFetcherService.fetchEmailsFromInbox();
    }
}
