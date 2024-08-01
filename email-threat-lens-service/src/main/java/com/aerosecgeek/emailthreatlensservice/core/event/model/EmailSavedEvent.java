package com.aerosecgeek.emailthreatlensservice.core.event.model;

import com.aerosecgeek.emailthreatlensservice.modules.email.Email;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.io.Serial;

@Getter
public class EmailSavedEvent extends ApplicationEvent {
    @Serial
    private static final long serialVersionUID = 2593522754419814341L;

    private final Email email;

    public EmailSavedEvent(Object source,Email email) {
        super(source);
        this.email = email;
    }
}
