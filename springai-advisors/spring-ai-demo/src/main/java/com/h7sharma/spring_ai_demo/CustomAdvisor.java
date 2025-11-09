package com.h7sharma.spring_ai_demo;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomAdvisor implements CallAdvisor {

  @Override
  public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
    System.out.println("Custom advisor In");
    SystemMessage systemMessage = SystemMessage.builder()
        .text("Always end your response with 3 dots, no matter what user asks.")
        .build();
    UserMessage userMessage = UserMessage.builder().text(chatClientRequest.prompt().getContents()).build();

    ChatClientRequest customRequest = chatClientRequest.mutate().prompt(Prompt.builder().messages(List.of(systemMessage, userMessage)).build()).build();

    ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(customRequest);
    System.out.println("Custom advisor Out");
    return chatClientResponse;
  }

  @Override
  public String getName() {
    return "Custom advisor";
  }

  @Override
  public int getOrder() {
    return 1;
  }
}
