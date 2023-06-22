package com.shashitest.UserServiceWS.services;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;

import com.shashitest.UserServiceWS.model.User;
import com.shashitest.UserServiceWS.model.UserDataModel;


public interface UserServices {

	User createUser(UserDataModel reqdata);
	List<User> getUserAll();
	User getUserById(String userId);
	String serviceExceptionTest();
	
}
