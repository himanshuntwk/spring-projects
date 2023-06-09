package com.himanshu.springsecuritycustom.security.filters;

import com.himanshu.springsecuritycustom.security.authentication.CustomAuthentication;
import com.himanshu.springsecuritycustom.security.managers.CustomAuthenticationManager;
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

        CustomAuthentication customAuthentication = new CustomAuthentication(false, headerKey);
        Authentication authenticateObject = customAuthenticationManager.authenticate(customAuthentication);
        SecurityContextHolder.getContext().setAuthentication(authenticateObject);
        filterChain.doFilter(request, response);
    }

}
