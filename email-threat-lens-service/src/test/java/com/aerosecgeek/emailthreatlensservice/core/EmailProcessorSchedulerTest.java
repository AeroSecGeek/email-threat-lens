package com.aerosecgeek.emailthreatlensservice.core;

import com.aerosecgeek.emailthreatlensservice.modules.email.InboxFetcherService;
import com.teketik.test.mockinbean.MockInBean;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("unittest")
@Transactional
@Rollback
class EmailProcessorSchedulerTest {
    @Autowired
    private EmailProcessorScheduler emailProcessorScheduler;

    @MockInBean(EmailProcessorScheduler.class)
    private InboxFetcherService inboxFetcherService;

    @Test
    void givenEmailProcessorScheduler_whenScheduleEmailProcessing_thenProcessEmails() {
        // given
        doNothing().when(inboxFetcherService).fetchEmailsFromInbox();

        // when
        emailProcessorScheduler.scheduleEmailProcessing();

        // then
        verify(inboxFetcherService,atLeastOnce()).fetchEmailsFromInbox();
    }
}