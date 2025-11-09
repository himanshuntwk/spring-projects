package com.h7sharma.spring_ai_demo;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAiDemoApplication implements CommandLineRunner {

  private final ChatClient chatClient;
  private final CustomAdvisor customAdvisor;
  private final CustomSecondAdvisor customSecondAdvisor;

  public SpringAiDemoApplication(ChatClient.Builder chatClientBuilder, CustomAdvisor customAdvisor,
                                 CustomSecondAdvisor customSecondAdvisor) {
    this.chatClient = chatClientBuilder.build();
    this.customAdvisor = customAdvisor;
    this.customSecondAdvisor = customSecondAdvisor;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringAiDemoApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    String response = chatClient.prompt().user("Which model are you? just respond with the name.")
        .advisors(customAdvisor, customSecondAdvisor)
        .call().content();


    System.out.println(response);
    System.exit(0);
  }
}
