package com.cogent.main.entity;

import jakarta.persistence.Embeddable;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AddressEntity {
	
	private String city;
	private String street;
	private String zipcode;
	private String phone;
}
