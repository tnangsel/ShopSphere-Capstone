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
import org.springframework.web.bind.annotation.RestController;

import com.cogent.main.dao.UserDao;
import com.cogent.main.dto.DiscountDao;
import com.cogent.main.dto.DiscountEntity;
import com.cogent.main.dto.OrderDao;
import com.cogent.main.dto.ProductDao;
import com.cogent.main.dto.ProductEntity;

import com.cogent.main.entity.UserEntity;
import com.cogent.main.feignclient.DiscountClientInAdmin;
import com.cogent.main.feignclient.OrderClientInAdmin;
import com.cogent.main.feignclient.ProductClientAdmin;
import com.cogent.main.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@Autowired
	private ProductClientAdmin productClient;

	@Autowired
	private DiscountClientInAdmin discountClientInAdmin;
	
	@Autowired
	private OrderClientInAdmin orderClientInAdmin;

// -------------------------feignClient to pass user-------------------------

	@GetMapping("/user")
	public boolean authUserHeader(@RequestHeader("Authorization") String header) {
		return adminService.validateHeader(header);
	}

//----------------------------User manage methods-------------------------	

	@GetMapping("/users/{userId}")
	public UserDao getOneUser(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String header) {
		return adminService.fetchOneUser(userId, header);
	}
	
	@GetMapping("/users")
	public List<UserEntity> getAllUsers() {
		return adminService.getUserList();
	}

	@PutMapping("/users/update/{userId}")
	public UserDao userEntityUpdate(@PathVariable("userId") Integer userId,
			@RequestBody UserDao entityDao) {
		return adminService.updateUserInfo(userId, entityDao);
	}

	@DeleteMapping("/users/{userId}")
	public String deleteUser(@PathVariable("userId") Integer userId) {
		return adminService.removeUser(userId);
	}

//---------------------------Product manage methods-------------------------

	@PostMapping("/products")
	public ProductEntity addProductByAdmin(@RequestBody ProductDao productDao) {
		return productClient.addProduct(productDao);
	}
	
	@GetMapping("/products/{productId}")
	public ProductDao getProductById(@PathVariable("productId") Integer productId) {
		return productClient.getProductById(productId);
	}

	@PutMapping("/products/{productId}")
	public ProductEntity updateProductById(@PathVariable("productId") Integer productId,
			@RequestBody ProductDao productDao) {
		return productClient.updateProduct(productId, productDao);
	}

	@DeleteMapping("/products/{productId}")
	public String deleteProductById(@PathVariable("productId") Integer productId) {
		return productClient.deleteProduct(productId);
	}

	// -----------------------Discount manage methods---------------------------------

	@GetMapping("/discounts")
	public List<DiscountEntity> fetchAllDiscount() {
		return discountClientInAdmin.fetchAllDiscount();
	}
	
	@PostMapping("/addDiscount")
	public DiscountDao insertDiscount(@RequestBody DiscountDao discountDao,
			@RequestHeader("Authorization") String header) {
		return discountClientInAdmin.insertDiscount(discountDao, header);
	}

	@DeleteMapping("/deleteDiscount/{discountId}")
	public DiscountDao deleteDiscout(@PathVariable("discountId") Integer discountId,
			@RequestHeader("Authorization") String header) {
		return discountClientInAdmin.deleteDiscout(discountId, header);
	}

	//------------------------Order manage methods ------------------------------------
	
	@GetMapping("/{userId}")
    public OrderDao listUserOrders(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String header) {
        return orderClientInAdmin.listUserOrders(userId, header);
    }
	
}
