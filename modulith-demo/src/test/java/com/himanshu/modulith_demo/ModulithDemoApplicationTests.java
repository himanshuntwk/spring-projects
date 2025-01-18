package com.himanshu.modulith_demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;
import org.springframework.modulith.test.ApplicationModuleTest;


@ApplicationModuleTest
@EmbeddedKafka(controlledShutdown = true)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class ModulithDemoApplicationTests {

    ApplicationModules modules = ApplicationModules.of(ModulithDemoApplication.class);

    @Test
    void contextLoads() {
        System.out.println("==============");
        modules.forEach(System.out::println);
        System.out.println("==============");
        modules.verify();
    }

    @Test
    void writeDocumentation() {
        new Documenter(modules)
                .writeModulesAsPlantUml(Documenter.DiagramOptions.defaults()
                        .withStyle(Documenter.DiagramOptions.DiagramStyle.UML)).writeAggregatingDocument();
    }


}
