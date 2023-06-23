package com.himanshu.springresserver.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

  @GetMapping("/hello")
  String hello(@AuthenticationPrincipal Jwt jwt) {
    return "Hello " + jwt.getSubject();
  }
}
