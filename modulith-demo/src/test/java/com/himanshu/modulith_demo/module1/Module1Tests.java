package com.himanshu.modulith_demo.module1;

import com.himanshu.modulith_demo.module1.exposed.TestEvent;
import com.himanshu.modulith_demo.module1.internal.InternalData1;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

@ApplicationModuleTest
@EmbeddedKafka(controlledShutdown = true)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class Module1Tests {

    @Autowired
    Module1Api module1Api;

    @Test
    void testModule1(Scenario scenario) {
        scenario
                .stimulate(() -> module1Api.sendEvent())
                .andWaitForEventOfType(TestEvent.class)
                .toArriveAndVerify(testEvent -> Assertions.assertNotNull(testEvent.getId()));

    }
}
