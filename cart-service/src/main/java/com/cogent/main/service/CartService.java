package com.cogent.main.service;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.cogent.main.dto.CartDao;
import com.cogent.main.dto.ProductDao;
import com.cogent.main.dto.UserDao;
import com.cogent.main.entity.CartEntity;
import com.cogent.main.feignclient.ProductClientInCart;
import com.cogent.main.feignclient.UserClientInCart;
import com.cogent.main.repository.CartRepository;

@Service
public class CartService {
	
	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private ProductClientInCart productClientInCart;
	
	@Autowired
	private UserClientInCart userClientInCart;
	
	
	public List<CartDao> fetchCartByUserId(Integer userId, String header) {
		
			List<CartEntity> cartProducts = cartRepository.findAllByUserId(userId);
			List<CartDao> cartP = cartProducts.stream().map(product -> CartDao.builder()
					.product_id(product.getProduct_id())
					.user_id(product.getUser_id())
					.quantity(product.getQuantity())
					.build())
					.collect(Collectors.toList());
			return cartP;
		
	}
	
	public CartDao addItem(Integer userId, Integer productId, Integer quantity, String header) {
		
			UserDao userDao = userClientInCart.getOneUser(userId, header);
			ProductDao productDao = productClientInCart.getOneProductById(productId);
			if(userDao.getName()!= null && productDao.getTitle()!= null) {
				
				CartEntity cart = CartEntity.builder().user_id(userId).product_id(productId).quantity(quantity).build();
				cartRepository.save(cart);
				return CartDao.builder().user_id(cart.getUser_id()).product_id(cart.getProduct_id())
						.quantity(cart.getQuantity()).build();
			}else {
				return new CartDao();
			}
		
	}
	
	public String removeProduct(Integer userId, Integer productId, String header) {
	    	UserDao user = userClientInCart.getOneUser(userId, header);
	    	if(!user.getEmail().isEmpty()) {
		
	    		cartRepository.deleteProduct(userId, productId);
	    		return "Your product is removed from the cart";
	    	}
	    	return "User doesn't exist";
	}


	public List<CartDao> adjustQuantity(Integer userId, List<CartDao> pds) {

	    List<CartEntity> cartProducts = cartRepository.findAllByUserId(userId);
	    for (CartDao updatedCartItem : pds) {
	    	boolean found = false;
	        for (CartEntity cartItem : cartProducts) {
	            if (updatedCartItem.getUser_id() == userId && updatedCartItem.getProduct_id() == cartItem.getProduct_id()) {
	                cartItem.setQuantity(updatedCartItem.getQuantity());
	                cartRepository.save(cartItem);
	                found = true;
	                break;
	            }
	        }
	        if(!found) {
	        	throw new IllegalArgumentException("User or Product  doesn't exist");
	        }
	    }
		List<CartDao> cartP = cartProducts.stream().map(product -> CartDao.builder()
				.product_id(product.getProduct_id())
				.user_id(product.getUser_id())
				.quantity(product.getQuantity())
				.build())
				.collect(Collectors.toList());
		return cartP;
    }

	public String removedAllProducts(Integer userId, String header) {
		UserDao userD = userClientInCart.getOneUser(userId, header);
		if(userD != null) {
			List<CartEntity> cartProducts = cartRepository.findAllByUserId(userId);
			for (CartEntity cartEntity : cartProducts) {
				System.out.println(cartEntity);
				cartRepository.deleteAllProducts(userId);
				return "Cart is empty. Order placed.";
			}
		}
		return "User doesn't exist in the cart";
		
	}
		
	
	
}