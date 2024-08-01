package com.aerosecgeek.emailthreatlensservice.core.event;

import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public void publishDomainEvent(ApplicationEvent event){
        applicationEventPublisher.publishEvent(event);
    }
}
