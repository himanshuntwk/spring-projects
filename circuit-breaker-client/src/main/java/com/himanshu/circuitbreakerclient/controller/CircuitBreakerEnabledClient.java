package com.himanshu.circuitbreakerclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBreakerEnabledClient {

    @Autowired
    private CircuitBreakerFactory circuitBreakerFactory;

    /**
     * Method destined to fail and throw null pointer exception.
     * @return String
     */
    static String failMethod() {
        System.out.println("Method invoked....");
        String str = null;
        str.toString(); // will throw null pointer exception
        return str;
    }

    /**
     * Method accept incoming request and trigger further processing using circuit breaker.
     * @return String
     */
    @GetMapping("/test-cb")
    public String testCircuitBreaker() {
       var circuitBreaker = circuitBreakerFactory.create("cb1");
       return circuitBreaker.run(() -> failMethod(), throwable -> "Exception occurred");
    }
}
