package com.aerosecgeek.emailthreatlensservice.modules.analysis.model;

import com.aerosecgeek.emailthreatlensservice.modules.email.model.Email;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
public class OverallEmailAnalysisResult implements Serializable {
    @Serial
    private static final long serialVersionUID = -2346971114056550503L;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @OneToOne
    @JoinColumn(name = "email_uuid")
    private Email email;

    @Embedded
    @AttributeOverride(name = "status", column = @Column(name = "header_status"))
    @AttributeOverride(name = "outcome", column = @Column(name = "header_outcome"))
    @AttributeOverride(name = "description", column = @Column(name = "header_description"))
    private AnalysisResult headerAnalysisResult = new AnalysisResult();

    @Embedded
    @AttributeOverride(name = "status", column = @Column(name = "body_status"))
    @AttributeOverride(name = "outcome", column = @Column(name = "body_outcome"))
    @AttributeOverride(name = "description", column = @Column(name = "body_description"))
    private AnalysisResult bodyAnalysisResult = new AnalysisResult();

    @Embedded
    @AttributeOverride(name = "status", column = @Column(name = "attachment_status"))
    @AttributeOverride(name = "outcome", column = @Column(name = "attachment_outcome"))
    @AttributeOverride(name = "description", column = @Column(name = "attachment_description"))
    private AnalysisResult attachmentAnalysisResult = new AnalysisResult();

    public OverallEmailAnalysisResult(Email email) {
        this.email = email;
    }

}
