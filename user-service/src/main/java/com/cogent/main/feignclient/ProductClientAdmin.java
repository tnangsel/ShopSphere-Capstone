package com.cogent.main.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.cogent.main.dto.ProductDao;
import com.cogent.main.dto.ProductEntity;


@FeignClient(name = "product-service", url = "localhost:8082/products")
public interface ProductClientAdmin {


	@PostMapping("/products")
	ProductEntity addProduct(@RequestBody ProductDao productDao);
	
	@PutMapping("/products/{productId}")
    ProductEntity updateProduct(@PathVariable("productId") Integer productId,
    										@RequestBody ProductDao productDao);
	
	@GetMapping("/products/{productId}")
	ProductDao getProductById(@PathVariable("productId") Integer productId);
    
	@DeleteMapping("/products/{productId}")
    String deleteProduct(@PathVariable("productId") Integer productId);

}
