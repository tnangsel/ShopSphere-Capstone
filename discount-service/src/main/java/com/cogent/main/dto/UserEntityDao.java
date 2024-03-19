package com.cogent.main.dto;



import jakarta.persistence.Embedded;

public class UserEntityDao {

	@Embedded
	private NameDao name;
	
	private String email;
	
	private String pass;
	
	@Embedded
	private AddressDao address;
}
