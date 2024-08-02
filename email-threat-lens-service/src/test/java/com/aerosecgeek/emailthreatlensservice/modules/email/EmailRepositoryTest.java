package com.aerosecgeek.emailthreatlensservice.modules.email;

import com.aerosecgeek.emailthreatlensservice.modules.email.model.Email;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("unittest")
@Transactional
@Rollback
class EmailRepositoryTest {
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

        // when
        Email savedEmail = emailRepository.save(email);

        // then
        assertNotNull(savedEmail.getUuid());
    }
}