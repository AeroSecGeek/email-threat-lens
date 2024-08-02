package com.aerosecgeek.emailthreatlensservice.modules.email.model;

import io.hypersistence.utils.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Type;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;

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

    //@Convert(converter = EmailHeadersConverter.class)
    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private List<EmailHeader> headers= new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String body;
}
