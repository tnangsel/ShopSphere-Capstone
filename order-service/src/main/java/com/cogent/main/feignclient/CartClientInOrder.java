package com.cogent.main.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cogent.main.dto.CartDao;

@FeignClient(name = "cart-service", url = "localhost:8084/cart")
public interface CartClientInOrder {
	
	@GetMapping("/{userId}")
	public List<CartDao> getProductsInCart(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String header);
	
	@DeleteMapping("/deletemycart/{userId}")
	public String emptyMyCart(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String header);
	
}