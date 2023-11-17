package com.himanshuntwk.springoauthserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

  @Bean
  UserDetailsService inMemoryUserDetailsManager() {
    var userBuilder = User.builder();
    UserDetails himanshu = userBuilder.username("himanshu").
        password("{bcrypt}$2a$10$Smgk4iacoRi6vHRxFUf47OIJferGMqKSG37yXRXHcVj3HkA7LU8n2").roles("USER", "ADMIN").build();
    return new InMemoryUserDetailsManager(himanshu);
  }

}
