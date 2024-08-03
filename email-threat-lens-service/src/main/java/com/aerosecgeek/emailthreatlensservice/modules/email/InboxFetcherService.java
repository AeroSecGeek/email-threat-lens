package com.aerosecgeek.emailthreatlensservice.modules.email;

import com.aerosecgeek.emailthreatlensservice.core.event.EventPublisher;
import com.aerosecgeek.emailthreatlensservice.core.event.model.EmailSavedEvent;
import com.aerosecgeek.emailthreatlensservice.core.exception.AttachmentIsEmail;
import com.aerosecgeek.emailthreatlensservice.modules.email.model.Email;
import com.aerosecgeek.emailthreatlensservice.modules.email.model.EmailHeader;
import jakarta.mail.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Enumeration;
import java.util.Properties;

@Service
public class InboxFetcherService {

    @Value("${mail.imap.host}")
    private String imapHost;

    @Value("${mail.imap.port}")
    private String imapPort;

    @Value("${mail.imap.username}")
    private String imapUsername;

    @Value("${mail.imap.password}")
    private String imapPassword;

    @Value("${mail.imap.protocol}")
    private String imapProtocol;

    private final EmailService emailService;
    private final EventPublisher eventPublisher;

    private final Logger logger = LoggerFactory.getLogger(InboxFetcherService.class);

    @Autowired
    public InboxFetcherService(EmailService emailService, EventPublisher eventPublisher) {
        this.emailService = emailService;
        this.eventPublisher = eventPublisher;
    }

    public void fetchEmailsFromInbox() {
        Properties properties = new Properties();
        properties.put("mail.store.protocol", imapProtocol);
        logger.info("Fetching emails from inbox");
        try {
            Session session = Session.getInstance(properties);
            Store store = session.getStore();
            store.connect(imapHost, Integer.parseInt(imapPort), imapUsername, imapPassword);

            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_WRITE);

            Message[] messages = inbox.getMessages();

            for (Message message : messages) {
                saveEmail(message);
                message.setFlag(Flags.Flag.DELETED, true);
            }
            inbox.close(true);
            store.close();
            logger.info("{} Emails fetched successfully", messages.length);
        } catch (Exception e) {
            logger.warn("Error processing emails", e);
        }
    }

    public void saveEmail(Message message){
        try {
            Email email = new Email();
            email.setSubject(message.getSubject());
            if (message.getFrom()!=null && message.getFrom().length > 0){
                email.setFromAddress(message.getFrom()[0].toString());
            }
            if(message.getAllRecipients()!=null && message.getAllRecipients().length>0){
                email.setToAddress(message.getAllRecipients()[0].toString());
            }

            // Extract and save headers
            for (Enumeration<Header> e = message.getAllHeaders(); e.hasMoreElements();) {
                Header header = e.nextElement();
                EmailHeader emailHeader = new EmailHeader(header.getName().toLowerCase(),header.getValue());
                email.getHeaders().add(emailHeader);
            }

            Date receivedDate = message.getReceivedDate();
            if (receivedDate == null) {
                receivedDate = new Date();
            }
            email.setReceivedDate(receivedDate);

            handleEmailContent(message, email);
        } catch (Exception e) {
           logger.warn("Error saving email", e);
        }
    }

    private void handleEmailContent(Message message, Email email) throws Exception {
        try {
            email.setBody(extractContent(message));
            email=emailService.saveEmail(email);
            eventPublisher.publishDomainEvent(new EmailSavedEvent(this, email));
        } catch (AttachmentIsEmail e) {
            // Do nothing
        }
    }

    private String extractContent(Part part) throws Exception {
        if (part.isMimeType("text/plain")) {
            return (String) part.getContent();
        } else if (part.isMimeType("text/html")) {
            return (String) part.getContent();
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < multipart.getCount(); i++) {
                BodyPart bodyPart = multipart.getBodyPart(i);
                result.append(extractContent(bodyPart));
            }
            return result.toString();
        }
        else if (part.isMimeType("message/rfc822")) {
            // Handle attached .eml files
            Message attachedMessage = (Message) part.getContent();
            saveEmail(attachedMessage); // Save the attached email separately
            throw new AttachmentIsEmail("Attached email found");
        }
        return "";
    }
}
