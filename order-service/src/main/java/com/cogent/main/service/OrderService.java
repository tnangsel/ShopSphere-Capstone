package com.cogent.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.main.dto.CartDao;
import com.cogent.main.dto.OrderDao;
import com.cogent.main.dto.ProductDao;
import com.cogent.main.dto.UserDao;
import com.cogent.main.entity.CategoryEntity;
import com.cogent.main.entity.OrderEntity;
import com.cogent.main.entity.ProductEntity;
import com.cogent.main.entity.UserEntity;
import com.cogent.main.feignclient.CartClientInOrder;
import com.cogent.main.feignclient.ProductClientInOrder;
import com.cogent.main.feignclient.UserClientInOrder;
import com.cogent.main.repository.OrderRepository;
import com.cogent.main.repository.UserRepository;

@Service
public class OrderService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private CartClientInOrder cartClient;
	
	@Autowired
	private UserClientInOrder userClientInOrder;
	
	@Autowired
	private ProductClientInOrder productClient;
	
	

	public OrderDao newUserOrder(Integer userId, List<ProductEntity> products, String header) {
			
			UserDao userDao = userClientInOrder.getOneUser(userId, header);
			UserEntity userEntity = UserEntity.builder()
					.name(userDao.getName())
					.address(userDao.getAddress())
					.email(userDao.getEmail())
					.build();
			userRepository.save(userEntity);
			OrderEntity newOrder = OrderEntity.builder()
					.user(userEntity)
					.products(products)
					.status("ordered")
					.build();
			orderRepository.save(newOrder);
			
			return OrderDao.builder()
					.user(newOrder.getUser())
					.products(newOrder.getProducts())
					.status(newOrder.getStatus())
					.build();
		
	}

	public OrderDao getUserOrders(Integer userId, String header) {
		
			List<CartDao> cartDaos = cartClient.getProductsInCart(userId, header);
			if (cartDaos.get(0).getProduct_id() != 0) {
				System.out.println("Here");
				List<ProductEntity> listProducts = new ArrayList<ProductEntity>();
				
				for (CartDao cartDao : cartDaos) {
					ProductDao productDao = productClient.getProductById(cartDao.getProduct_id());
					ProductEntity productE = ProductEntity.builder().title(productDao.getTitle())
							.description(productDao.getDescription())
							.category(CategoryEntity.builder().name(productDao.getCategory().getName()).build())
							.price(productDao.getPrice()).build();
					listProducts.add(productE);
				}
				UserDao userDao = userClientInOrder.getOneUser(userId, header);
					UserEntity userEntity = UserEntity.builder().name(userDao.getName())
							.address(userDao.getAddress()).email(userDao.getEmail()).build();
					return OrderDao.builder().status("pending").user(userEntity).products(listProducts).build();
				
			} else {
				return null;
			}
		
	}
	

//	public List<OrderEntity> getUserOrderDetails(Integer userId, String header) {
//		UserDao userDao = userClientInOrder.getOneUser(userId, header);
//		OrderDao orderDao = orderRepository.findByUserId(userId);
//		if( userDao.getName().equals(orderDao.getUser().getName())) {
//			return orderRepository.findAll();
//		}else {
//			return null;
//		}
//	}
	
	
	public OrderDao getOrderDetails(Integer userId, String header) {
		UserDao userDao = userClientInOrder.getOneUser(userId, header);
		List<ProductEntity> productEntities = new ArrayList<ProductEntity>();
		List<CartDao> cartProducts = cartClient.getProductsInCart(userId, header);
		for (CartDao cartDao : cartProducts) {
			Integer product_id = cartDao.getProduct_id();

			ProductDao product = productClient.getProductById(product_id);
			ProductEntity productEntity = ProductEntity.builder()
					.title(product.getTitle())
					.description(product.getDescription())
					.price(product.getPrice())
					.category(CategoryEntity.builder()
											.name(product.getCategory().getName())
											.build())
					.build();
			productEntities.add(productEntity);
		}
		OrderEntity orderEntity = OrderEntity.builder()
				.user(UserEntity.builder().name(userDao.getName())
						.address(userDao.getAddress())
						.email(userDao.getEmail())
						.build())
				.products(productEntities).build();
		orderRepository.save(orderEntity);
		return OrderDao.builder()
				.user(orderEntity.getUser())
				.products(orderEntity.getProducts())
				.build();
		
	}


	
	
	
	
	
	
	

	
	
	
	
	
	
}
