package com.himanshu.springpractice.controller;

import com.himanshu.springpractice.domain.User;
import com.himanshu.springpractice.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

/**
 * Controller class demonstrating different kinds of REST handling.
 */
@RestController
public class RestWebController {

  @Autowired
  TestService testService;
  /**
   * Method demonstrating GET requests handling.
   * Sample Request: curl -X GET http://localhost:8080/getData
   *
   * @return String
   */
  @GetMapping("/getData")
  public String getHandling() {
    testService.testMethod();
    User user = new User(123l, "Himanshu");
    return ("GET:: User Information : " + user.getUserId() + "::" + user.getUserName());
  }

  /**
   * Method demonstrating POST requests handling.
   * Sample Request:
   * curl -X POST -d '{"userId":123,"userName":"Himanshu"}' -H 'Content-Type:
   * application/json' http://localhost:8080/setData
   *
   * @param user
   * @return String
   */
  @PostMapping("/setData")
  public String postHandling(@RequestBody User user) {
    return ("POST:: User Information : " + user.getUserId() + "::" + user.getUserName());
  }

  /**
   * Method demonstrating PUT requests handling.
   * Sample Request:
   * curl -X PUT -d '{"userId":123,"userName":"Himanshu"}' -H 'Content-Type:
   * application/json' http://localhost:8080/setData
   *
   * @param user
   * @return String
   */
  @PutMapping("/setData")
  public String putHandling(@RequestBody User user) {
    return ("PUT:: User Information : " + user.getUserId() + "::" + user.getUserName());
  }

  /**
   * Method demonstrating POST requests handling.
   * Sample Request:
   * curl -X DELETE -d '{"userId":123,"userName":"Himanshu"}' -H 'Content-Type:
   * application/json' http://localhost:8080/setData
   *
   * @param user
   * @return String
   */
  @DeleteMapping("/deleteData")
  public String deleteHandling(@RequestBody User user) {
    return ("DELETE:: User Information : " + user.getUserId() + "::" + user.getUserName());
  }

}
