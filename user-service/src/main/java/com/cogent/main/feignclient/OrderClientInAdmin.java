package com.cogent.main.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cogent.main.dto.OrderDao;
import com.cogent.main.dto.ProductEntity;


@FeignClient(name = "order-service", url = "localhost:8083/orders")
public interface OrderClientInAdmin {
	
	@GetMapping("/{userId}")
    OrderDao listUserOrders(@PathVariable("userId") Integer userId, @RequestHeader("Authorization") String header);

	@PostMapping("/{userId}")
    OrderDao createOrder(@PathVariable("userId") Integer userId, @RequestBody List<ProductEntity> products, @RequestHeader("Authorization") String header);
	
	
//    @GetMapping("/detail/{orderId}")
//    public OrderDao fetchOrderDetails(@PathVariable("orderId") Integer orderId);
    
}
