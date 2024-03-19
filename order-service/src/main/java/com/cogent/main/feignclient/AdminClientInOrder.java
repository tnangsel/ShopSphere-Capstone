package com.cogent.main.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "admin-service", url = "localhost:8081/admin")
public interface AdminClientInOrder {
	
	@GetMapping("/user")
	public boolean authUserHeader(@RequestHeader("Authorization") String header);
	
}
