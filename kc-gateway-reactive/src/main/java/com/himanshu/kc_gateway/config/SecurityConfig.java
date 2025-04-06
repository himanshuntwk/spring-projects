package com.himanshu.kc_gateway.config;

import com.himanshu.kc_gateway.security.ReactivePolicyEnforcerFilter;
import org.keycloak.adapters.authorization.spi.ConfigurationResolver;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.keycloak.representations.adapters.config.PolicyEnforcerConfig;
import org.keycloak.util.JsonSerialization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoders;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.io.IOException;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

  @Bean
  public SecurityWebFilterChain filterChain(ServerHttpSecurity http) throws Exception {
    return http
        .authorizeExchange(authorize -> authorize
            .anyExchange().authenticated()
        )
        .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()))
        .addFilterAt(new ReactivePolicyEnforcerFilter(new ConfigurationResolver() {
          @Override
          public PolicyEnforcerConfig resolve(HttpRequest request) {
            try {
              return JsonSerialization.readValue(getClass().getResourceAsStream("/policy-enforcer.json"), PolicyEnforcerConfig.class);
            } catch (IOException e) {
              throw new RuntimeException(e);
            }
          }
        }), SecurityWebFiltersOrder.AUTHENTICATION)
        .build();
  }

  @Bean
  public ReactiveJwtDecoder jwtDecoder() {
    return ReactiveJwtDecoders.fromOidcIssuerLocation("http://localhost:8080/realms/new-demo-realm");
  }


}
