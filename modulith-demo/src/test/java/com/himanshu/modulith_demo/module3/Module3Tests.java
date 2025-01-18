package com.himanshu.modulith_demo.module3;

import com.himanshu.modulith_demo.module1.exposed.TestEvent;
import com.himanshu.modulith_demo.module3.internal.InternalData3;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

import java.util.UUID;

@ApplicationModuleTest
@EmbeddedKafka(controlledShutdown = true)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class Module3Tests {

    @Autowired
    InternalData3 internalData3;

    @Test
    void testModule3(Scenario scenario) {
        scenario
                .publish(new TestEvent(UUID.randomUUID().toString(), "From Test"))
                .andWaitForEventOfType(TestEvent.class)
                .toArriveAndVerify(testEvent -> Assertions.assertTrue(internalData3.getEventStatus()));
    }

}
