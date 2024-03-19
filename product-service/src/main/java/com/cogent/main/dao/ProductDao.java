package com.cogent.main.dao;

import com.cogent.main.entity.CategoryEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDao {
	
	private String title;
	private String description;
	private double price;
	
	
    private CategoryEntity category;
	
}