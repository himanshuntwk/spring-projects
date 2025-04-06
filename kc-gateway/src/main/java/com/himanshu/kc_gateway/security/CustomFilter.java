package com.himanshu.kc_gateway.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.io.IOException;

/**
 * Responsible to handle unauthorized response generated after keycloak enforcer and customize the response as needed.
 */
@Component
public class CustomFilter implements WebFilter {

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    return chain.filter(exchange).doOnSuccess(aVoid -> {
      if (exchange.getResponse().getStatusCode() == org.springframework.http.HttpStatus.FORBIDDEN) {
        exchange.getResponse().setStatusCode(HttpStatus.SERVICE_UNAVAILABLE);
        exchange.getResponse().getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBuffer buffer = exchange.getResponse().bufferFactory().wrap(("{\n" +
            "    \"status\": 503,\n" +
            "    \"error\": \"This resource is only for premium user. Kindly pay first.\",\n" +
            "    \"message\": \"Your are not authorized for this end-point\"\n" +
            "}").getBytes());
        exchange.getResponse().writeWith(Mono.just(buffer));
      }
    });
  }
}
