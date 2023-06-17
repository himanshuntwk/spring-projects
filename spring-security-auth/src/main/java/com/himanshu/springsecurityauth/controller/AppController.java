package com.himanshu.springsecurityauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello Admin";
    }

    @GetMapping("/hi")
    public String hi() {
        return "Hi Consultant";
    }

    @GetMapping("/other")
    public String other() {
        return "Other Controller endpoint";
    }

    @GetMapping("/img_676")
    public String regexData() {
        return "regex endpoint";
    }

}
