package com.aerosecgeek.emailthreatlensservice.modules.testdata;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MessageExample {
    public static Message getMessage() throws MessagingException {
        // Set up properties for the mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "localhost");
        properties.put("mail.smtp.port", "25");

        // Create a mail session
        Session session = Session.getDefaultInstance(properties, null);

        // Create a MimeMessage object
        MimeMessage message = new MimeMessage(session);

        // Set the From address
        message.setFrom(new InternetAddress("sender@example.com"));

        // Set the To address
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("recipient@example.com"));

        // Set the Subject
        message.setSubject("Test Email");

        // Add custom headers
        message.addHeader("X-Priority", "1");
        message.addHeader("X-Mailer", "JavaMail Test");

        // Set HTML content with a hyperlink
        String htmlContent = "<h1>Hello!</h1>"
                + "<p>This is a test email with a <a href='https://www.example.com'>hyperlink</a>.</p>";
        message.setContent(htmlContent, "text/html");

        // Save changes to the message
        message.saveChanges();
        return message;
    }
}
