package com.cogent.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cogent.main.entity.WishlistEntity;

import jakarta.transaction.Transactional;

@Repository
public interface WishlistRepository extends JpaRepository<WishlistEntity, Integer>{

	@Query(value = "from WishlistEntity where userId= :userId")
	List<WishlistEntity> findAllByUserId(Integer userId);
	
	@Modifying
	@Transactional
	@Query(value =  "delete from WishlistEntity where userId= :userId and productId= :productId")
	void deleteProductByProductId(Integer userId, Integer productId);

}
