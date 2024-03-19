package com.cogent.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.main.dto.ProductDao;
import com.cogent.main.dto.UserDao;
import com.cogent.main.entity.WishlistEntity;
import com.cogent.main.feignclient.ProductClientInWishlist;
import com.cogent.main.feignclient.UserClientInWishlist;
import com.cogent.main.repository.WishlistRepository;

@Service
public class WishlistService {
	
	@Autowired
	private WishlistRepository wishlistRepository;
	
	@Autowired
	private ProductClientInWishlist productClientInWishlist;
	
	@Autowired
	private UserClientInWishlist userClientInWishlist;
	

	public List<WishlistEntity> displayWishlist(Integer userId) {
		
		return wishlistRepository.findAllByUserId(userId);
	}

	public WishlistEntity insertProductToWishlist(Integer userId, Integer productId, String header) {
		UserDao userD = userClientInWishlist.getOneUser(userId, header);
		ProductDao prod = productClientInWishlist.getProductById(productId);
		if(userD != null && prod != null) {
			WishlistEntity myWish = WishlistEntity.builder()
					.userId(userId)
					.productId(productId)
					.build();
			return wishlistRepository.save(myWish);
		}else {
			return null;
		}
	}

	public String removeProductFromWishlist(Integer userId, Integer productId, String header) {
		UserDao userD = userClientInWishlist.getOneUser(userId, header);
		ProductDao prod = productClientInWishlist.getProductById(productId);
		if(userD != null && prod != null) {
			wishlistRepository.deleteProductByProductId(userId, productId);
			return "Product has been removed from wishlist";
		}else {
			return null;
		}
	}
	
}
