package com.himanshu.configserverclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    /**
     * Value will be injected from configuration parameter provided in config-server
     */
    @Value("${msg}")
    private String message;

    /**
     * Method handle get request and return a configuration value
     *
     * @return String
     */
    @GetMapping("/get-method")
    public String getRequestHandler() {
        return message;
    }
}
