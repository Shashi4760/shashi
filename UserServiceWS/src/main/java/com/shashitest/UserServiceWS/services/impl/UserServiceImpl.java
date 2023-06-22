package com.shashitest.UserServiceWS.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.shashitest.UserServiceWS.exceptions.UserServiceException;
import com.shashitest.UserServiceWS.model.User;
import com.shashitest.UserServiceWS.model.UserDataModel;
import com.shashitest.UserServiceWS.services.UserServices;

@Service
public class UserServiceImpl implements UserServices {

	Map<String, User> userData = null;

	public String serviceExceptionTest() {
		if (true)
			throw new UserServiceException("This is Userservice Exception");
		return "Hello";
	}

	@Override
	public User createUser(UserDataModel reqdata) {
		String userId = UUID.randomUUID().toString();
		User userTemp = new User();
		reqdata.setUserId(userId);
		userTemp.setUserId(reqdata.getUserId());
		userTemp.setFirstName(reqdata.getFirstName());
		userTemp.setLastName(reqdata.getLastName());
		userTemp.setPassword(reqdata.getPassword());
		userTemp.setAge(reqdata.getAge());
		userTemp.setEmail(reqdata.getEmail());
		if (userData == null) {
			userData = new HashMap<String, User>();
		}
		userData.put(userId, userTemp);

		return userTemp;
	}

	public List<User> getUserAll() {
		List<User> userList = new ArrayList<User>();
		if (userData != null) {
			userList = userData.entrySet().stream().map(key -> key.getValue()).collect(Collectors.toList());
		}
		return userList;
	}

	public User getUserById(String userId) {
		if (null != userData && userData.containsKey(userId)) {
			return (userData.get(userId));
		} else {
			return null;
		}

	}
}
