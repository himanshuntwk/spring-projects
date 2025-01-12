package com.himanshu.modulith_demo;

import com.himanshu.modulith_demo.module1.Module1Api;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class ModulithDemoApplication implements CommandLineRunner {

	private final Module1Api module1Api;

    public ModulithDemoApplication(Module1Api module1Api) {
        this.module1Api = module1Api;
    }

    public static void main(String[] args) {
		SpringApplication.run(ModulithDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		module1Api.sendEvent();
	}
}
