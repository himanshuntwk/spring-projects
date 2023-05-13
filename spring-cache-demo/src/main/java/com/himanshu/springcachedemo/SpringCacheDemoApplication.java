package com.himanshu.springcachedemo;

import com.himanshu.springcachedemo.service.LongRunningService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringCacheDemoApplication implements CommandLineRunner{

	@Autowired
	LongRunningService longRunningService;

	public static void main(String[] args) {
		SpringApplication.run(SpringCacheDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(System.currentTimeMillis());
		System.out.println(longRunningService.longRunningMethod(2) + "::" + System.currentTimeMillis());
		System.out.println("SecondTime >>");
		System.out.println(System.currentTimeMillis());
		System.out.println(longRunningService.longRunningMethod(2) + "::" + System.currentTimeMillis());

		System.out.println("-----------------------");

		System.out.println(System.currentTimeMillis());
		System.out.println(longRunningService.longRunningMethod2(1) + "::" + System.currentTimeMillis());
		System.out.println("SecondTime >>");
		System.out.println(System.currentTimeMillis());
		System.out.println(longRunningService.longRunningMethod2(1) + "::" + System.currentTimeMillis());

	}


}
