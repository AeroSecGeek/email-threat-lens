package com.aerosecgeek.emailthreatlensservice.modules.email;

import com.aerosecgeek.emailthreatlensservice.modules.email.model.Email;
import com.aerosecgeek.emailthreatlensservice.modules.email.model.EmailHeader;
import com.aerosecgeek.emailthreatlensservice.modules.util.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("unittest")
class EmailRepositoryTest extends AbstractIntegrationTest {
    @Autowired
    private EmailRepository emailRepository;

    @Test
    void givenEmail_whenSave_thenSuccess() {
        // given
        Email email = new Email();
        email.setFromAddress("test@test.com");
        email.setToAddress("test2@test.ch");
        email.setSubject("Test");
        email.setBody("Test content");
        email.setHeaders(new ArrayList<>());
        email.getHeaders().add(new EmailHeader("test", "test"));

        // when
        Email savedEmail = emailRepository.save(email);

        // then
        assertNotNull(savedEmail.getUuid());
        Optional<Email> emailOptional = emailRepository.findById(savedEmail.getUuid());
        assertTrue(emailOptional.isPresent());
    }
}