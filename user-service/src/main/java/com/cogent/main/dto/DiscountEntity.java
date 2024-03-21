package com.cogent.main.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountEntity {

	private int id;
	private double discountPrice;
	private String discountCode;
}
