package com.cogent.main.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cogent.main.dto.DiscountDao;
import com.cogent.main.dto.DiscountEntity;


@FeignClient(name = "discount-service", url = "localhost:8086/discount")
public interface DiscountClientInAdmin {
	
	@GetMapping("/discounts")
	public List<DiscountEntity> fetchAllDiscount();
	
	@PostMapping("/addDiscount")
	public DiscountDao insertDiscount(@RequestBody DiscountDao discountDao, @RequestHeader("Authorization") String header);
	
	@DeleteMapping("/deleteDiscount/{discountId}")
	public DiscountDao deleteDiscout(@PathVariable("discountId") Integer discountId, @RequestHeader("Authorization") String header);
}
