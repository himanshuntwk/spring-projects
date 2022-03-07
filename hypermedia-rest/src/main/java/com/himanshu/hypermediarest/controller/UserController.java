package com.himanshu.hypermediarest.controller;

import com.himanshu.hypermediarest.datastore.UserStore;
import com.himanshu.hypermediarest.domain.User;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @GetMapping("/get-user")
  public HttpEntity<User> getUser(@RequestParam Integer userId) {
    UserStore userStore = new UserStore();
    User responseUser = userStore.getUser(userId);

    responseUser
        .add(linkTo(methodOn(UserController.class)
            .getUser(userId)).withSelfRel());

    responseUser.add(linkTo(methodOn(UserController.class)
    .getAllusers()).withRel("other-apis"));
    responseUser.add(linkTo(methodOn(UserController.class)
    .updateUser(userId)).withRel("other-apis"));
    return new ResponseEntity<>(responseUser, HttpStatus.OK);
  }

  @GetMapping("/get-all")
  public HttpEntity<Map<Integer,User>> getAllusers() {
    UserStore userStore = new UserStore();
    return new ResponseEntity<>(userStore.getAllUsers(), HttpStatus.OK);
  }

  @PostMapping("/update-user")
  public HttpEntity<String> updateUser(@RequestParam Integer userId) {
    
    return new ResponseEntity<>("User updated", HttpStatus.OK);
  }
}
