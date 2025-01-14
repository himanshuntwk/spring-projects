package com.himanshu.modulith_demo.module3;

import com.himanshu.modulith_demo.module1.Module1Api;
import com.himanshu.modulith_demo.module1.exposed.ExposedData1;
import org.springframework.modulith.moments.support.TimeMachine;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;

@RestController
public class Module3Api {
    ExposedData1 exposedData1;

    Module1Api module1Api;

    private final TimeMachine timeMachine;

    public Module3Api(TimeMachine timeMachine) {
        this.timeMachine = timeMachine;
    }

    @Transactional
    @GetMapping("/moment")
    public void testMoment() {
        for(int i=0;i<7;i++) {
            timeMachine.shiftBy(Duration.ofDays(1));
        }
    }
}
