package com.himanshu.modulith_demo.module3.internal;

import com.himanshu.modulith_demo.module1.exposed.TestEvent;
import org.springframework.context.event.EventListener;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.modulith.moments.DayHasPassed;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;

@Service
public class InternalData3 {

    @ApplicationModuleListener
    public void processEvent(TestEvent testEvent) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " received event");
        System.out.println("Event Received >> " + testEvent);
    }

    @ApplicationModuleListener
    public void momentListener(final DayHasPassed dayHasPassed) {
        System.out.println(dayHasPassed.getDate() + " :: Date");
        if(dayHasPassed.getDate().getDayOfWeek() == DayOfWeek.SATURDAY) {
            System.out.println("Printing but you can emit further event or do anything");
        }
    }
}
