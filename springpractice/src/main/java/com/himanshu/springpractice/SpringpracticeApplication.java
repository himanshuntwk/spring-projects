package com.himanshu.springpractice;

import com.himanshu.springpractice.service.TestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringpracticeApplication {

  @Bean
  public TestService getTestService() {
    return new TestService();
  }
  public static void main(String[] args) {
    SpringApplication.run(SpringpracticeApplication.class, args);
  }

}
