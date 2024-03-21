package com.cogent.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cogent.main.dto.OrderDao;
import com.cogent.main.entity.OrderEntity;
import com.google.common.base.Optional;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer>{
	
	@Query(value = "from OrderEntity where user.email= :email")
	List<OrderEntity> findAllByEmail(String email);
	
//	@Query(value = "from OrderEntity where user_id= : userId")
//	List<OrderEntity> findAllOrderByUserId(Integer userId);
	
//	@Query(value = "from OrderEntity as oe where oe.")
//	Optional<OrderEntity> findByUserId(Integer userId);

}