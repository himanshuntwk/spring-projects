package com.h7sharma.spring_ai_demo;

import org.springframework.ai.chat.client.ChatClientRequest;
import org.springframework.ai.chat.client.ChatClientResponse;
import org.springframework.ai.chat.client.advisor.api.CallAdvisor;
import org.springframework.ai.chat.client.advisor.api.CallAdvisorChain;
import org.springframework.stereotype.Component;

@Component
public class CustomSecondAdvisor implements CallAdvisor {
  @Override
  public ChatClientResponse adviseCall(ChatClientRequest chatClientRequest, CallAdvisorChain callAdvisorChain) {
    System.out.println("Custom second advisor In");
    ChatClientResponse chatClientResponse = callAdvisorChain.nextCall(chatClientRequest);
    System.out.println("Custom second advisor Out");
    return chatClientResponse;
  }

  @Override
  public String getName() {
    return "Custom second advisor";
  }

  @Override
  public int getOrder() {
    return 2;
  }
}
