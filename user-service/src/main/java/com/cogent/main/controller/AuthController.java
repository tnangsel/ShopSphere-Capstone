/**
 * @author: Tenzin 
 * @date : Feb 12, 2024
 */
package com.cogent.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.main.dao.LoginResponseDao;
import com.cogent.main.dao.UserDao;
import com.cogent.main.entity.AuthRequest;
import com.cogent.main.service.AuthService;


@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthService service;
	

	@PostMapping("/register")
	public String registerUser(@RequestBody UserDao userEntityDao) {
		return service.addUser(userEntityDao);
	}
	
	@PostMapping("/login")
	public LoginResponseDao loginUser(@RequestBody AuthRequest userCredentials) {
		return service.validateLogin(userCredentials);
	}
	
	@GetMapping("/getId/{userEmail}")
	public Integer getUserId(@PathVariable String userEmail) {
		return service.getUserIdByEmail(userEmail);
	}       


}
