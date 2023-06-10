package com.himanshu.springsecuritycustom.security.filters;

import com.himanshu.springsecuritycustom.security.authentication.CustomAuthentication;
import com.himanshu.springsecuritycustom.security.authentication.ExternalAuthentication;
import com.himanshu.springsecuritycustom.security.manager.CustomAuthenticationManager;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class CustomAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAuthenticationManager customAuthenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String headerKey = request.getHeader("key");
        String externalHeaderKey = request.getHeader("external-key");
        Authentication authenticatedObject;
        if (externalHeaderKey != null) {
            ExternalAuthentication externalAuthentication = new ExternalAuthentication(false, externalHeaderKey);
            authenticatedObject = customAuthenticationManager.authenticate(externalAuthentication);
        }else {
            CustomAuthentication customAuthentication = new CustomAuthentication(false, headerKey);
            authenticatedObject = customAuthenticationManager.authenticate(customAuthentication);
        }
        SecurityContextHolder.getContext().setAuthentication(authenticatedObject);
        filterChain.doFilter(request, response);
    }
}
