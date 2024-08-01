package com.aerosecgeek.emailthreatlensservice.modules.email.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class EmailHeader implements Serializable {
    @Serial
    private static final long serialVersionUID = 187015874761858205L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String key;
    @Column(columnDefinition = "TEXT")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email_id")
    private Email email;

    public EmailHeader(String key,String value,Email email){
        this.key = key;
        this.value = value;
        this.email = email;
    }
}
