package com.aerosecgeek.emailthreatlensservice.facade.listener;

import com.aerosecgeek.emailthreatlensservice.core.event.EventPublisher;
import com.aerosecgeek.emailthreatlensservice.core.event.model.EmailSavedEvent;
import com.aerosecgeek.emailthreatlensservice.modules.email.model.Email;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("unittest")
@Transactional
@Rollback
class EmailEventListenerTest {
    @Autowired
    private EventPublisher eventPublisher;

    @SpyBean
    private EmailEventListener emailEventListener;

    @Test
    void givenEmailSavedEvent_whenHandleEmailSavedEvent_thenLog() {
        // given
        Email email = new Email();
        String emailAddress = "test@test.com";
        email.setFromAddress(emailAddress);
        EmailSavedEvent emailSavedEvent = new EmailSavedEvent(this,email);

        // when
        eventPublisher.publishDomainEvent(emailSavedEvent);

        // then
        // Verify the listener was triggered
        ArgumentCaptor<EmailSavedEvent> eventCaptor = ArgumentCaptor.forClass(EmailSavedEvent.class);
        verify(emailEventListener, times(1)).handleEmailSavedEvent(eventCaptor.capture());

        // Assert that the event is the one we published
        EmailSavedEvent capturedEvent = eventCaptor.getValue();
        assertEquals(emailAddress, capturedEvent.getEmail().getFromAddress());
    }
}