package com.himanshu.springsecurityauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.httpBasic(withDefaults()).authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                        .requestMatchers("/hello").hasAuthority("admin")
                        .requestMatchers("/hi").hasAuthority("consultant")
                        .requestMatchers(RegexRequestMatcher.regexMatcher("/img_[0-9]{3}")).authenticated()
                        .anyRequest().authenticated()).build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager();

        UserDetails himanshuUser =
                User.withUsername("himanshu").password(passwordEncoder().encode("12345")).authorities("admin").build();

        UserDetails consulUser =
                User.withUsername("consul").password(passwordEncoder().encode("12345")).authorities("consultant").build();

        inMemoryUserDetailsManager.createUser(himanshuUser);
        inMemoryUserDetailsManager.createUser(consulUser);

        return inMemoryUserDetailsManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
