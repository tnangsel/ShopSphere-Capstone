package com.cogent.main.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "user-service", url = "localhost:8081/admin")
public interface UserClientinDiscount {
	
	@GetMapping("/user")
	public boolean authUserHeader(@RequestHeader("Authorization") String header);
	
}
