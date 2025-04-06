package com.himanshu.kc_gateway.security;

import org.keycloak.adapters.authorization.TokenPrincipal;
import org.keycloak.adapters.authorization.spi.HttpRequest;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class CustomHttpRequest implements HttpRequest {
  private final ServerHttpRequest request;

  public CustomHttpRequest(ServerHttpRequest request) {
    this.request = request;
  }

  @Override
  public String getRelativePath() {
    return request.getPath().value();
  }

  @Override
  public String getMethod() {
    return request.getMethod().name();
  }

  @Override
  public String getURI() {
    return request.getURI().toString();
  }

  @Override
  public List<String> getHeaders(String name) {
    return request.getHeaders().get(name);
  }

  @Override
  public String getFirstParam(String name) {
    return request.getQueryParams().getFirst(name);
  }

  @Override
  public String getCookieValue(String name) {
    return request.getCookies().get(name).getFirst().getName();
  }

  @Override
  public String getRemoteAddr() {
    return request.getRemoteAddress().getAddress().getHostAddress();
  }

  @Override
  public boolean isSecure() {
    return false;
  }

  @Override
  public String getHeader(String name) {
    return request.getHeaders().getFirst(name);
  }

  @Override
  public InputStream getInputStream(boolean buffered) {
    Flux<DataBuffer> body = request.getBody();
    Iterator<DataBuffer> iterator = body.toIterable().iterator();
    Vector<InputStream> inputStreams = new Vector<>();

    while (iterator.hasNext()) {
      DataBuffer dataBuffer = iterator.next();
      inputStreams.add(dataBuffer.asInputStream());
    }

    return new SequenceInputStream(inputStreams.elements());
  }

  @Override
  public TokenPrincipal getPrincipal() {
    Mono<Authentication> authenticationMono = ReactiveSecurityContextHolder.getContext()
        .map(SecurityContext::getAuthentication);

    Authentication authentication = authenticationMono.block();
    if (authentication != null && authentication.getPrincipal() instanceof TokenPrincipal) {
      return (TokenPrincipal) authentication.getPrincipal();
    }
    return null;
  }
}