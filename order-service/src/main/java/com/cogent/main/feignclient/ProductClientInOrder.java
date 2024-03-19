package com.cogent.main.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cogent.main.dto.ProductDao;

@FeignClient(name = "product-service", url = "localhost:8082/products")
public interface ProductClientInOrder {

	@GetMapping("/{productId}")
	ProductDao getProductById(@PathVariable("productId") Integer productId);
	
	
	
}