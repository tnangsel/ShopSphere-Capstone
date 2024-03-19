package com.cogent.main.dto;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDao {
	
	private int id;
	private String title;
	private String description;
	private double price;
	
	@Embedded
	private CategoryEntity category;
	
}
