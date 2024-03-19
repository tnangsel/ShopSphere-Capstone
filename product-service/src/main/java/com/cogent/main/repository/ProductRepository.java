package com.cogent.main.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cogent.main.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Integer>{
	
    @Query(value = "from ProductEntity where category.name = :categoryName")
    List<ProductEntity> findByCategory(String categoryName);

    @Query(value = "from ProductEntity where title= :ptitle")
	Optional<ProductEntity> findByTitle(String ptitle);


}
