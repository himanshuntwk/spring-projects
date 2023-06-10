package com.himanshu.springsecuritycustom.security.provider;

import com.himanshu.springsecuritycustom.security.authentication.CustomAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Value("${app.secret.key}")
    private String appSecretKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthentication customAuthentication = (CustomAuthentication) authentication;
        String headerKey = customAuthentication.getKey();

        if(appSecretKey.equalsIgnoreCase(headerKey)) {
            log.info("Request have valid key");
            return new CustomAuthentication(true, headerKey);
        }
        throw new BadCredentialsException("Secret key in header did not match Application Secret key...");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthentication.class.equals(authentication);
    }
}
