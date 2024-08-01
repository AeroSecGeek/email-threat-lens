package com.aerosecgeek.emailthreatlensservice.modules.email.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    @OneToMany(mappedBy = "email", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmailHeader> headers = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String body;
}
