package com.cogent.main.dto;

import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AddressDao {
	
	private String city;
	private String street;
	private String zipcode;
	private String phone;
}
