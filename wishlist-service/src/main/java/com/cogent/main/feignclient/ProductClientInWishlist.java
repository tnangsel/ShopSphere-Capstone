package com.cogent.main.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cogent.main.dto.ProductDao;
import com.cogent.main.entity.ProductEntity;


@FeignClient(name = "product-service", url = "localhost:8082/products")
public interface ProductClientInWishlist {
	
	@GetMapping("/")
	public List<ProductEntity> getAllProducts();
	
	@GetMapping("/{productId}")
	ProductDao getProductById(@PathVariable("productId") Integer productId);
}
