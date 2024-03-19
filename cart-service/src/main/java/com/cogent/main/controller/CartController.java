package com.cogent.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.main.dto.ProductDao;
import com.cogent.main.dto.CartDao;
import com.cogent.main.feignclient.ProductClientInCart;
import com.cogent.main.service.CartService;


@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductClientInCart productClientInCart;

	@GetMapping("/{userId}")
	public List<CartDao> getProductsInCart(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String header) {
		return cartService.fetchCartByUserId(userId, header);
	}
	
	@PostMapping("/{userId}/add")
    public CartDao addProductToCart(@PathVariable("userId") Integer userId, @RequestParam Integer productId, @RequestParam Integer quantity, @RequestHeader("Authorization") String header) {
        return cartService.addItem(userId, productId, quantity, header);
    }
	
	@DeleteMapping("/{userId}/remove/{productId}")
	public String deleteProductFromCart(@PathVariable("userId") Integer userId, @PathVariable Integer productId, @RequestHeader("Authorization") String header) {
		return cartService.removeProduct(userId, productId, header);
	}
	
	@PutMapping("{userId}/update")
	public List<CartDao> updateProductQuantity(@PathVariable("userId") Integer userId, @RequestBody List<CartDao> listProductUserId) {
		return cartService.adjustQuantity(userId, listProductUserId);
	}
	
	@DeleteMapping("/deletemycart/{userId}")
	public String emptyMyCart(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String header) {
		return cartService.removedAllProducts(userId, header);
	}
	
//------------------------feignClient method calling for product----------------------	
	
	@GetMapping("/getOneProduct/{productId}")
	public ProductDao getOneProductById(@PathVariable("productId") Integer productId) {
		return productClientInCart.getOneProductById(productId);
	}
	
	
	
}
