package com.himanshu.modulith_demo.module1.internal;

import com.himanshu.modulith_demo.module1.exposed.TestEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class InternalData1 {

    private final ApplicationEventPublisher applicationEventPublisher;

    public InternalData1(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void sendEvent() {
        applicationEventPublisher.publishEvent(new TestEvent(1, "Test-Event"));
    }
}
