package com.aerosecgeek.emailthreatlensservice.modules.email;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class Email implements Serializable {
    @Serial
    private static final long serialVersionUID = -3730906658035704456L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String fromAddress;
    private String toAddress;
    private Date receivedDate;
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String headers;

    @Column(columnDefinition = "TEXT")
    private String body;
}
