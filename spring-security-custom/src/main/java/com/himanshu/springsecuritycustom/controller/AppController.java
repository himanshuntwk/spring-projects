package com.himanshu.springsecuritycustom.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/hello")
    public String getHello() {
        return "Hello from application";
    }
}
