package com.himanshu.springaiollama.config;

import com.himanshu.springaiollama.advisors.CustomLoggingAdvisor;
import com.himanshu.springaiollama.advisors.ReadAgainAdvisor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class ChatClientConfig {

  @Value("${spring.ai.ollama.base-url}")
  private String ollamaApiUrl;

  private final ReadAgainAdvisor readAgainAdvisor;
  private final CustomLoggingAdvisor customLoggingAdvisor;

  public ChatClientConfig(ReadAgainAdvisor readAgainAdvisor, CustomLoggingAdvisor customLoggingAdvisor) {
    this.readAgainAdvisor = readAgainAdvisor;
    this.customLoggingAdvisor = customLoggingAdvisor;
  }

  @Bean
  public ChatClient chatClient() {

    SimpleLoggerAdvisor simpleLoggerAdvisor = new SimpleLoggerAdvisor(request -> "System " + request.systemText(),
        response -> "Total Tokens :: " + response.getMetadata().getUsage().getTotalTokens(), Ordered.HIGHEST_PRECEDENCE);
    String systemBehaviour = "Answer in the voice of a {nationality} individual";
    OllamaChatModel ollamaChatModel = OllamaChatModel.builder()
        .ollamaApi(new OllamaApi(ollamaApiUrl))
        .defaultOptions(OllamaOptions.builder().model("llama3.2").build())
        .build();

    return ChatClient.builder(ollamaChatModel)
        .defaultSystem(systemBehaviour)
        .defaultAdvisors(simpleLoggerAdvisor, readAgainAdvisor, customLoggingAdvisor)
        .build();
  }


  @Bean
  public ChatClient chatClientDeepSeek() {

    OllamaChatModel ollamaChatModel = OllamaChatModel.builder()
        .ollamaApi(new OllamaApi(ollamaApiUrl))
        .defaultOptions(OllamaOptions.builder().model("deepseek-r1:8b").build())
        .build();

    return ChatClient.builder(ollamaChatModel)
        .build();
  }
}
