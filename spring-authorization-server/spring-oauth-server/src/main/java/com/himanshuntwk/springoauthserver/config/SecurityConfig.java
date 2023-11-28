package com.himanshuntwk.springoauthserver.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;
import java.util.Set;
import java.util.UUID;

import static org.springframework.security.oauth2.core.AuthorizationGrantType.*;
import static org.springframework.security.oauth2.core.ClientAuthenticationMethod.CLIENT_SECRET_BASIC;

@Configuration
public class SecurityConfig {

  @Bean
  JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
    return new JdbcUserDetailsManager(dataSource);
  }

  @Bean
  RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
    return new JdbcRegisteredClientRepository(jdbcTemplate);
  }

  @Bean
  ApplicationRunner clientsRunner(RegisteredClientRepository registeredClientRepository, UserDetailsManager userDetailsManager) {
    return args -> {
      var clientId = "demo";
      if (registeredClientRepository.findByClientId(clientId) == null) {
        registeredClientRepository.save(RegisteredClient
            .withId(UUID.randomUUID().toString())
            .clientId(clientId)
            .clientSecret("{bcrypt}$2a$10$lHEIhOIZBybWCzRZOVuko.rFe9oGBtGHnRAU97wgvJZmWQWvkBIm6")
            .authorizationGrantTypes(authorizationGrantTypes -> authorizationGrantTypes
                .addAll(Set.of(CLIENT_CREDENTIALS, AUTHORIZATION_CODE, REFRESH_TOKEN)))
            .redirectUris(uri -> uri.add("http://127.0.0.1:8082/login/oauth2/code/reg-client"))
            .scopes(scopes -> scopes.addAll(Set.of("user.read", "user.write", "openid")))
            .clientAuthenticationMethods(cam -> cam.add(CLIENT_SECRET_BASIC))
            .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
            .build());
      }

      if (!userDetailsManager.userExists("himanshu")) {
        var userBuilder = User.builder();
        UserDetails himanshu = userBuilder.username("himanshu").
            password("{bcrypt}$2a$10$Smgk4iacoRi6vHRxFUf47OIJferGMqKSG37yXRXHcVj3HkA7LU8n2").roles("USER", "ADMIN").build();
        userDetailsManager.createUser(himanshu);
      }

    };
  }

}
