package com.cogent.main.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.cogent.main.dto.CartDao;


@FeignClient(name = "cart-service", url = "localhost:8084/cart")
public interface CartClientInUser {

	@GetMapping("/{userId}")
	List<CartDao> getAllProductInCart(@PathVariable("userId") Integer userId);
	
	@PostMapping("/{userId}/add")
    CartDao addProductToCart(@PathVariable("userId") Integer userId, @RequestParam Integer productId, @RequestParam Integer quantity);
	
	@DeleteMapping("/{userId}/remove/{productId}")
	String deleteProductFromCart(@PathVariable("userId") Integer userId, @PathVariable Integer productId);
	
	@PutMapping("{userId}/update")
	List<CartDao> updateProductQuantity(@PathVariable("userId") Integer userId, @RequestBody List<CartDao> pds);
}
