package com.himanshu.configserverclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Value("${msg}")
    private String message;

    @GetMapping("/get-method")
    public String getRequestHandler() {
        return message;
    }
}
