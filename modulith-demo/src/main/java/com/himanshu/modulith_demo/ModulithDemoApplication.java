package com.himanshu.modulith_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ModulithDemoApplication {

    public static void main(String[] args) {
		SpringApplication.run(ModulithDemoApplication.class, args);
	}

}
