package com.himanshu.modulith_demo.module3.internal;

import com.himanshu.modulith_demo.module1.exposed.TestEvent;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class InternalData3 {

    @EventListener
    public void processEvent(TestEvent testEvent) throws InterruptedException {
        System.out.println("Event Received >> " + testEvent);
    }
}
