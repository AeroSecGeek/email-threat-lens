package com.aerosecgeek.emailthreatlensservice.facade.listener;

import com.aerosecgeek.emailthreatlensservice.core.event.EventPublisher;
import com.aerosecgeek.emailthreatlensservice.core.event.model.EmailSavedEvent;
import com.aerosecgeek.emailthreatlensservice.modules.email.EmailService;
import com.aerosecgeek.emailthreatlensservice.modules.email.model.Email;
import com.aerosecgeek.emailthreatlensservice.modules.util.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmailEventListenerTest extends AbstractIntegrationTest {
    @Autowired
    private EventPublisher eventPublisher;

    @Autowired
    private EmailService emailService;

    @SpyBean
    private EmailEventListener emailEventListener;

    @Test
    void givenEmailSavedEvent_whenHandleEmailSavedEvent_thenLog() {
        // given
        Email email = new Email();
        String emailAddress = "test@example.com";
        email.setFromAddress(emailAddress);
        email = emailService.saveEmail(email);

        EmailSavedEvent emailSavedEvent = new EmailSavedEvent(this, email);

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