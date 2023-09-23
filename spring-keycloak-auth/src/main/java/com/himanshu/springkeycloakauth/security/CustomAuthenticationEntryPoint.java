package com.himanshu.springkeycloakauth.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * This is responsible to send custom authentication message when token itself fails or any other exception occurred prior to security enforcer.
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

    response.setStatus(HttpStatus.SC_UNAUTHORIZED);
    response.getWriter().write("{\n" +
        "    \"status\": " + HttpStatus.SC_UNAUTHORIZED + ",\n" +
        "    \"error\": \"" + authException.getMessage() + "\",\n" +
        "    \"message\": \"" + "Your token is expired, Please generate a new one." + "\",\n" +
        "}");
    response.setHeader(HTTP.CONTENT_TYPE, "application/json");
  }
}
