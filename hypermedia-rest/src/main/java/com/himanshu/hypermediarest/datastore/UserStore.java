package com.himanshu.hypermediarest.datastore;

import java.util.HashMap;
import java.util.Map;

import com.himanshu.hypermediarest.domain.User;

public class UserStore {
  
/**
 * Method to return specific user from Map.
 * @param userId
 * @return User
 */
  public User getUser(Integer userId) {
    return getAllUsers().get(userId);
  }

  /**
   * Method to return a map of users.
   * @return usersMap
   */
  public Map<Integer, User> getAllUsers() {
    Map<Integer, User> usersMap = new HashMap<>();
    usersMap.put(123, new User(123, "user1first", "user1last"));
    usersMap.put(456, new User(456, "user2first", "user2last"));
    usersMap.put(789, new User(789, "user3first", "user3last"));
    usersMap.put(111, new User(111, "user4first", "user4last"));
    usersMap.put(222, new User(222, "user5first", "user5last"));
    usersMap.put(333, new User(333, "user6first", "user6last"));
    return usersMap;
  }
}
