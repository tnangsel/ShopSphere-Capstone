package com.cogent.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cogent.main.entity.CartEntity;

import jakarta.transaction.Transactional;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer> {

	@Query(value = "from CartEntity where user_id= :userId")
	List<CartEntity> findAllByUserId(Integer userId);
	
	@Modifying
	@Transactional
	@Query(value = "delete from CartEntity where user_id= :userId and product_id= :productId")
	void deleteProduct(Integer userId, Integer productId);

	@Query(value = "from CartEntity where user_id= :userId")
	List<CartEntity> findByUserId(Integer userId);

	@Modifying
	@Transactional
	@Query(value = "DELETE FROM CartEntity WHERE user_id = :userId")
	void deleteAllProducts(Integer userId);
	
}
