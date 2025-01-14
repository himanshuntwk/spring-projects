package com.himanshu.modulith_demo.module1.internal;

import com.himanshu.modulith_demo.module1.exposed.TestEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Async
@Transactional
@Service
public class InternalData1 {

    private final ApplicationEventPublisher applicationEventPublisher;

    public InternalData1(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void sendEvent() {
        System.out.println(Thread.currentThread().getName() + " send event");
        applicationEventPublisher.publishEvent(new TestEvent(UUID.randomUUID().toString(), "Test-Event"));
    }
}
