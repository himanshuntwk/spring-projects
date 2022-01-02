package com.himanshu.springpractice.domain;

/**
 * User class acting as data object for post requests.
 */
public class User {

  private Long userId;
  private String userName;

  public User() {
  }

  public User(Long userId, String userName) {
    this.userId = userId;
    this.userName = userName;
  }

  public Long getUserId() {
    return userId;
  }

  public String getUserName() {
    return userName;
  }

}
