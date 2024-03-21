package com.cogent.main.dto;

import java.util.List;

import com.cogent.main.entity.ProductEntity;
//import com.cogent.main.entity.UserEntity;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDao {
	
	private String status;
	@Embedded
    private UserDao user;
	
    private List<ProductEntity> products;
	
}
