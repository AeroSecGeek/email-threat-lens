package com.aerosecgeek.emailthreatlensservice.facade.listener;

import com.aerosecgeek.emailthreatlensservice.core.event.model.EmailSavedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EmailEventListener {
    private final Logger logger = LoggerFactory.getLogger(EmailEventListener.class);

    @EventListener
    @Async
    public void handleEmailSavedEvent(EmailSavedEvent event){
        logger.info("Starting async handling of new email {}",event.getEmail().getFromAddress()); // TODO #4: further processing of emails
    }
}
