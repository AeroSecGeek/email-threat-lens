package com.aerosecgeek.emailthreatlensservice.modules.email;

import com.aerosecgeek.emailthreatlensservice.modules.email.model.Email;
import com.aerosecgeek.emailthreatlensservice.modules.email.model.EmailHeader;
import com.aerosecgeek.emailthreatlensservice.modules.util.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;


class EmailServiceTest extends AbstractIntegrationTest {

    @Autowired
    private EmailService emailService;

    @Test
    void givenEmail_whenSaveEmail_thenSave() {
        // given
        Email email = new Email();
        email.setFromAddress("test@test.com");
        email.setToAddress("test2@example.com");
        email.setSubject("Test Subject");
        email.setBody("Test Body");
        email.getHeaders().add(new EmailHeader("Test Header", "Test Value"));

        // when
        Email savedEmail = emailService.saveEmail(email);

        // then
        assertNotNull(savedEmail.getUuid());
        assertEquals(email.getFromAddress(), savedEmail.getFromAddress());
        assertEquals(email.getToAddress(), savedEmail.getToAddress());
        assertEquals(email.getSubject(), savedEmail.getSubject());
        assertEquals(email.getBody(), savedEmail.getBody());
        assertEquals(email.getHeaders().size(), savedEmail.getHeaders().size());
    }

    @Test
    void givenEmailUuid_whenGetEmail_thenGet() {
        // given
        Email email = new Email();
        email.setFromAddress("test@test.com");
        Email savedEmail = emailService.saveEmail(email);

        // when
        Email retrievedEmail = emailService.getEmailByUuid(savedEmail.getUuid());

        // then
        assertNotNull(retrievedEmail);
        assertEquals(savedEmail.getUuid(), retrievedEmail.getUuid());
    }

}