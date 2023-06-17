package com.himanshu.springsecurityauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello admin";
    }

    @GetMapping("/hi")
    public String hi() {
        return "Hi consultant";
    }

    @GetMapping("/other")
    public String other() {
        return "Other endpoint accessible after authentication";
    }

    @GetMapping("/img_783")
    public String staticImage() {
        return "Static image served";
    }

}
