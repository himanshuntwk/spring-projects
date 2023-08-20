package com.himanshu.springkeycloakauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

  @GetMapping("/hello")
  public String hello() {
    return "Hello normal user";
  }

  @GetMapping("/hello-pr")
  public String helloProtected() {
    return "Hello premium user";
  }
}
