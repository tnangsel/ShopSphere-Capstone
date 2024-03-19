package com.cogent.main.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cogent.main.dto.ProductDao;


@FeignClient(name = "product-service", url = "localhost:8082/products")
public interface ProductClientInCart {

	@GetMapping("/getOneProduct/{productId}")
	ProductDao getOneProductById(@PathVariable("productId") Integer productId);

		
}
