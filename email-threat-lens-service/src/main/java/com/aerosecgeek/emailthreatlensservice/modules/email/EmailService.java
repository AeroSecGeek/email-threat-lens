package com.aerosecgeek.emailthreatlensservice.modules.email;

import com.aerosecgeek.emailthreatlensservice.modules.email.model.Email;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@AllArgsConstructor
public class EmailService {
    private final EmailRepository emailRepository;

    @Transactional
    public Email saveEmail(Email email) {
        return emailRepository.save(email);
    }

    public Email getEmailByUuid(UUID id){
        return emailRepository.findById(id).orElse(null);
    }
}
