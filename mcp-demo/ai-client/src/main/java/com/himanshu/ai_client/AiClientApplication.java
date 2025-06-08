package com.himanshu.ai_client;

import ch.qos.logback.classic.pattern.Util;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.i18n.LocaleContextHolder;

import java.time.LocalDateTime;

@SpringBootApplication
public class AiClientApplication implements CommandLineRunner {

	private final ChatClient chatClient;

	@Autowired
	private ToolCallbackProvider tools;

  public AiClientApplication(ChatClient.Builder chatClientBuilder) {
    this.chatClient = chatClientBuilder.build();
  }

  public static void main(String[] args) {
		SpringApplication.run(AiClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ChatClient.CallResponseSpec responseSpec = chatClient.prompt("What is the current date ?")
				.tools(tools)
				.call();

		System.out.println("Response >> " + responseSpec.content());
	}
}


//class UtilTools {
//
//	@Tool(description = "This method provide current date and time as per user's timezone.")
//	String getCurrentDateTime() {
//		return LocalDateTime.now().atZone(LocaleContextHolder.getTimeZone().toZoneId()).toString();
//	}
//}