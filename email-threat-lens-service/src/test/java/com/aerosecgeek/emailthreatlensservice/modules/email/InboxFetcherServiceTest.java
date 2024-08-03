package com.aerosecgeek.emailthreatlensservice.modules.email;

import com.aerosecgeek.emailthreatlensservice.core.event.EventPublisher;
import com.aerosecgeek.emailthreatlensservice.core.event.model.EmailSavedEvent;
import com.aerosecgeek.emailthreatlensservice.modules.email.model.Email;
import com.aerosecgeek.emailthreatlensservice.modules.util.AbstractIntegrationTest;
import jakarta.mail.Address;
import jakarta.mail.Header;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;

import static org.mockito.Mockito.*;

class InboxFetcherServiceTest extends AbstractIntegrationTest {
    @Mock
    private EmailService emailService;

    @Mock
    private EventPublisher eventPublisher;

    @Mock
    private Message message;

    @InjectMocks
    private InboxFetcherService inboxFetcherService;

    @Test
    void givenMessageAndEmail_whenSaveEmail_thenEventPublished() throws MessagingException, IOException {
        // given
        when(message.getSubject()).thenReturn("Test");
        when(message.getContent()).thenReturn("Test");
        when(message.getReceivedDate()).thenReturn(new Date());
        when(message.getAllHeaders()).thenReturn(getHeaders());
        when(message.getFrom()).thenReturn(getAddresses());
        when(message.getAllRecipients()).thenReturn(getAddresses());

        // when
        inboxFetcherService.saveEmail(message);

        // then
        verify(emailService, times(1)).saveEmail(any(Email.class));
        verify(eventPublisher, times(1)).publishDomainEvent(any(EmailSavedEvent.class));
    }

    private Enumeration<Header> getHeaders(){
        // Create a mock Header
        Header header1 = new Header("test","test");
        Header header2 = new Header("Subject","Test Subject");

        // Create an Enumeration of Headers
        return Collections.enumeration(
                Arrays.asList(header1, header2)
        );
    }

    private Address[] getAddresses() throws AddressException {
        // Create a mock Address
        Address address1 = new InternetAddress("sender1@example.com");
        Address address2 = new InternetAddress("sender2@example.com");

        // Define the array of Address objects
        return new Address[]{address1, address2};
    }
}