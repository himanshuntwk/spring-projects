package com.himanshu.reactive_client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BookClientConfig {

  private final String bookBaseUrl;

  public BookClientConfig(@Value("${book.base.url}") String bookBaseUrl) {
    this.bookBaseUrl = bookBaseUrl;
  }

  @Bean
  public WebClient getWebClient() {
    return WebClient.builder().baseUrl(bookBaseUrl).build();
  }

}
