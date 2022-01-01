package com.himanshu.springpractice.controller;

import com.himanshu.springpractice.domain.User;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class demonstrating different kinds of REST handling.
 */
@RestController
public class RestWebController {

  /**
   * Method demonstrating GET requests handling.
   * Sample Request: curl http://localhost:8080/sayHello
   * @return String
   */
  @GetMapping("/sayHello")
  public String getHandling() {
    return "Hello World";
  }

  /**
   * Method demonstrating POST requests handling.
   * Sample Request:
   * curl -d '{"userId":123,"userName":"Himanshu"}' -H 'Content-Type:
   * application/json' http://localhost:8080/setData
   * 
   * @param user
   * @return String
   */
  @PostMapping("/setData")
  public String postHandling(@RequestBody User user) {
    return ("User Information : " + user.getUserId() + "::" + user.getUserName());
  }

}
