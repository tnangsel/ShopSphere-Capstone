package com.cogent.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.main.entity.WishlistEntity;
import com.cogent.main.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
	
	@Autowired
	private WishlistService wishlistService;
	
	@GetMapping("/{userId}")
	public List<WishlistEntity> showWishlist(@PathVariable("userId") Integer userId){
		return wishlistService.displayWishlist(userId);
	}
	
	@PostMapping("/{userId}/add")
	public WishlistEntity addProductToWish(@PathVariable("userId") Integer userId, @RequestParam Integer productId, @RequestHeader("Authorization") String header) {
		return wishlistService.insertProductToWishlist(userId, productId, header);
	}
	
	@DeleteMapping("/{userId}/remove/{productId}")
	public String deleteProductFromWish(@PathVariable("userId") Integer userId, @PathVariable("productId") Integer productId, @RequestHeader("Authorization") String header) {
		return wishlistService.removeProductFromWishlist(userId, productId, header);
	}
	
}