package com.cogent.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.cogent.main.dto.DiscountDao;
import com.cogent.main.entity.DiscountEntity;
import com.cogent.main.service.DiscountService;


@RestController
@RequestMapping("/discount")
public class DiscountController {

	@Autowired
	private DiscountService discountService;
	
	@GetMapping("/{discountCode}")
	public Integer getDiscount(@PathVariable("discountCode") String discountCode) {
		return discountService.fetchDiscount(discountCode);
	}
	
//--------------------------Admin method callers---------------------------
	@GetMapping("/discounts")
	public List<DiscountEntity> fetchAllDiscount() {
		return discountService.getAllDiscounts();
	}
	
	@PostMapping("/addDiscount")
	public DiscountDao insertDiscount(@RequestBody DiscountDao discountDao, @RequestHeader("Authorization") String header) {
		return discountService.addDiscount(discountDao, header);
	}
	
	@DeleteMapping("/deleteDiscount/{discountId}")
	public DiscountDao deleteDiscout(@PathVariable("discountId") Integer discountId, @RequestHeader("Authorization") String header) {
		return discountService.removeDiscount(discountId, header);
	}
	
	
}
