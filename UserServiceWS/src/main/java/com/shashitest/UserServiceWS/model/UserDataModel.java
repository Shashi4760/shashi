package com.shashitest.UserServiceWS.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDataModel {
	
	private String userId;
	@NotNull(message ="First Name can not empty")
	@Size(min=2, max=10, message="First name in between (2-10) characters")
	private String firstName;
	private String lastName;
	private String password;
	private int age;
	
	@NotNull (message="Please Enter Email field !")
	@NotEmpty (message="Email field should not blank !")
	@Email(message ="Please Enter correct email format")
	private String email;
	
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
