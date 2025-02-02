package com.himanshu.springaiollama;

import org.springframework.ai.autoconfigure.chat.client.ChatClientBuilderConfigurer;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringAiOllamaApplication implements CommandLineRunner {


  private final ChatClient chatClient;

  private final ChatClient chatClientDeepSeek;

  public SpringAiOllamaApplication(ChatClient chatClient, ChatClient chatClientDeepSeek) {
    this.chatClient = chatClient;
    this.chatClientDeepSeek = chatClientDeepSeek;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringAiOllamaApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    ChatClient.CallResponseSpec responseSpec = chatClient.prompt()
        .user("Describe a movie action scene in 50 words")
        .system(promptSystemSpec -> promptSystemSpec.param("nationality", "French"))
        .call();
    System.out.println("Response: " + responseSpec.content());

    System.out.println("==================================");

    ChatClient.CallResponseSpec responseSpecDeepSeek = chatClientDeepSeek.prompt()
        .user("What should be the value of x in 5x + 20 = 55")
        .call();
    System.out.println("Response: " + responseSpecDeepSeek.content());

  }
}
