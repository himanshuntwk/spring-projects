package com.himanshu.hypermediarest.domain;

import org.springframework.hateoas.RepresentationModel;

public class User extends RepresentationModel<User> {
  
	private Integer userId;
  private String firstName;
	private String lastName;
	
	public User(Integer userId, String firstName, String lastName) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	

}
