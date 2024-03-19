package com.cogent.main.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cogent.main.dto.UserDao;

@FeignClient(name = "user-service", url = "localhost:8081/user")
public interface UserClientInCart {
	
	@GetMapping("/user/{userId}")
	UserDao getOneUser(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String header);
}
