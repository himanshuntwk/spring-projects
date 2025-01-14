package com.himanshu.modulith_demo;

import com.himanshu.modulith_demo.module1.Module1Api;
import org.junit.jupiter.api.AutoClose;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

@SpringBootTest
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
