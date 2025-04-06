package com.himanshu.kc_gateway.security;

import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

public class ReactivePolicyEnforcerFilter implements WebFilter {

  private final ConfigurationResolver configurationResolver;

  public ReactivePolicyEnforcerFilter(ConfigurationResolver configurationResolver) {
    this.configurationResolver = configurationResolver;
  }

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
    return Mono.fromCallable(() -> {
          HttpRequest httpRequest = new CustomHttpRequest(exchange.getRequest());
          PolicyEnforcerConfig policyEnforcerConfig = configurationResolver.resolve(httpRequest);
          return policyEnforcerConfig;
        }).flatMap(policyEnforcerConfig -> chain.filter(exchange))
        .onErrorResume(e -> {
          exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
          return exchange.getResponse().setComplete();
        });
  }
}
