package com.aerosecgeek.emailthreatlensservice;

import com.aerosecgeek.emailthreatlensservice.modules.email.InboxFetcherService;
import com.aerosecgeek.emailthreatlensservice.modules.util.AbstractIntegrationTest;
import com.aerosecgeek.emailthreatlensservice.modules.testdata.MessageExample;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@ActiveProfiles("unittest")
@Transactional
@Rollback
class EmailThreatLensServiceApplicationTests extends AbstractIntegrationTest {
    @Autowired
    private InboxFetcherService inboxFetcherService;

    @Test
    void givenNewMessage_whenSaveEmail_thenCorrectProcess() throws MessagingException {
        // given
        Message message = MessageExample.getMessage();

        // when
        inboxFetcherService.saveEmail(message);

        // then
    }

}
