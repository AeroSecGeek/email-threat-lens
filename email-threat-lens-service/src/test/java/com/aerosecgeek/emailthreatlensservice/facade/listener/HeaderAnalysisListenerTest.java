package com.aerosecgeek.emailthreatlensservice.facade.listener;

import com.aerosecgeek.emailthreatlensservice.core.event.model.StartAnalysisEvent;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.OverallEmailAnalysisResultService;
import com.aerosecgeek.emailthreatlensservice.modules.analysis.model.OverallEmailAnalysisResult;
import com.aerosecgeek.emailthreatlensservice.modules.email.EmailRepository;
import com.aerosecgeek.emailthreatlensservice.modules.email.model.Email;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("unittest")
@Transactional
@Rollback
class HeaderAnalysisListenerTest {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Autowired
    private EmailRepository emailRepository;

    @SpyBean
    private HeaderAnalysisListener headerAnalysisListener;

    @SpyBean
    private OverallEmailAnalysisResultService overallEmailAnalysisResultService;

    @Test
    void givenStartAnalysisEvent_whenHandleStartAnalysisEvent_thenMethodTriggered() throws Exception {
        // given
        var overallResult = new OverallEmailAnalysisResult();
        Email email = new Email();
        email.setFromAddress("test@test.com");
        email.setToAddress("test2@test.ch");
        email.setSubject("Test");
        email.setBody("Test content");
        email.setHeaders(new ArrayList<>());
        email = emailRepository.save(email);
        overallResult.setEmail(email);
        overallEmailAnalysisResultService.saveResult(overallResult);

        var event = new StartAnalysisEvent(this, overallResult);

        // when
        applicationEventPublisher.publishEvent(event);

        // then
        ArgumentCaptor<StartAnalysisEvent> eventCaptor = ArgumentCaptor.forClass(StartAnalysisEvent.class);
        verify(headerAnalysisListener, times(1)).handleStartAnalysisEvent(eventCaptor.capture());
        assertEquals(event, eventCaptor.getValue());
    }
}