package com.cogent.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.main.dto.CartDao;
import com.cogent.main.dto.OrderDao;
import com.cogent.main.dto.ProductDao;
import com.cogent.main.dto.UserDao;
import com.cogent.main.entity.OrderEntity;
import com.cogent.main.entity.ProductEntity;
import com.cogent.main.feignclient.AdminClientInOrder;
import com.cogent.main.feignclient.CartClientInOrder;
import com.cogent.main.feignclient.ProductClientInOrder;
import com.cogent.main.feignclient.UserClientInOrder;
import com.cogent.main.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService; 
	
	@Autowired
	private ProductClientInOrder productClientInOrder;
	
	@Autowired
	private CartClientInOrder cartClientInOrder;
	
	@Autowired
	private UserClientInOrder userClientInOrder;
	
	@Autowired
	private AdminClientInOrder adminClientInOrder;
	
	@GetMapping("/{email}")
    public List<OrderEntity> listUserOrders(@PathVariable("email") String email, @RequestHeader("Authorization") String header) {
        return orderService.getUserOrders(email, header);
    }
	

    @PostMapping("/{userId}")
    public OrderDao createOrder(@PathVariable("userId") Integer userId, @RequestBody List<ProductEntity> products, @RequestHeader("Authorization") String header) {
        return orderService.newUserOrder(userId, products, header);
    }

    
    

    //----------------------FeignClient methods calling--------------------
    
    @GetMapping("/product/{productId}")
	public ProductDao getProductById(@PathVariable("productId") Integer productId) {
		return productClientInOrder.getProductById(productId);
	}
    
    @GetMapping("/userOrder/{userId}")
	public UserDao getOneUser(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String header) {
		return userClientInOrder.getOneUser(userId, header);
	}
    
    @GetMapping("/user")
	public boolean getOneUser(@RequestHeader("Authorization") String header) {
		return adminClientInOrder.authUserHeader(header);
	}
    
    @GetMapping("/cart/{userId}")
	public List<CartDao> getProductsInCart(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String header) {
		return cartClientInOrder.getProductsInCart(userId, header);
	}
    

    
}
