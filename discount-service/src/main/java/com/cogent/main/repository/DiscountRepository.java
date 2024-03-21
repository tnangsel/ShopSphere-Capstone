package com.cogent.main.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cogent.main.entity.DiscountEntity;

@Repository
public interface DiscountRepository extends JpaRepository<DiscountEntity, Integer>{
	@Query(value = "from DiscountEntity where discountCode= :discountCode")
	Optional<DiscountEntity> findByCode(String discountCode);

	

}
