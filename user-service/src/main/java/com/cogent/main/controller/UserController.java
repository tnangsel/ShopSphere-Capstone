package com.cogent.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.main.dao.UserDao;
import com.cogent.main.service.AuthService;



@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private AuthService service;
	

	//------------------------feignClient passing user by User_ID--------------------------------
	
	@GetMapping("/user/{userId}")
	public UserDao getOneUser(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String header) {
		return service.fetchOneUser(userId, header);
	}
	
	//change the endpoint to userOrder
	@GetMapping("/userOrder/{userId}")
	public UserDao getUser(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String header) {
		return service.fetchOneUser(userId, header);
	}
	


}
