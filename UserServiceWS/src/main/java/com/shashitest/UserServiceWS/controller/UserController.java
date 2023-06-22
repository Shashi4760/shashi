package com.shashitest.UserServiceWS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shashitest.UserServiceWS.model.User;
import com.shashitest.UserServiceWS.model.UserDataModel;
import com.shashitest.UserServiceWS.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired UserServices services;
	
	// http://localhost:8080/swagger-ui/index.html
	//http://localhost:8080/users/asdfkasdasd
	@GetMapping(path = "{userId}", produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Object> getUserById(@PathVariable String userId) {
		User user = services.getUserById(userId);
		if (null != user) {
			return (new ResponseEntity<Object>(user, HttpStatus.OK));
		} else {
			return (new ResponseEntity<Object>(HttpStatus.NO_CONTENT));
		}

	}
	//http://localhost:8080/users
	@GetMapping //(produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<Object> getUserAll() {
		List<User> userList = services.getUserAll();
		if( userList.size()>0) {
			return (new ResponseEntity<Object>(userList, HttpStatus.OK));
		}else {
			return (new ResponseEntity<Object>(HttpStatus.NO_CONTENT));
		}
	}
	//to test exception , http://localhost:8080/users/error
	@GetMapping(value="/error" ,produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity testException() {
		String temp=null;
	//	int len = temp.length(); // handle nullpointer exception
		//if(true) throw new UserServiceException("This is Userservice Exception");
		services.serviceExceptionTest();
		return (new ResponseEntity(HttpStatus.NO_CONTENT));
	}

	//http://localhost:8080/users
	@PostMapping()
	public ResponseEntity<Object> createUser(@Valid @RequestBody UserDataModel reqdata) {
		return (new ResponseEntity<Object>(services.createUser(reqdata), HttpStatus.OK));
	}
}
