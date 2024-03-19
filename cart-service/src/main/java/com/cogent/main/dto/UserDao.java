package com.cogent.main.dto;

import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDao {
	
	@Embedded
	private NameDao name;
	
	private String email;
	
	private String pass;
	
	@Embedded
	private AddressDao address;
	
	@Enumerated(EnumType.STRING)
	private String roles;
}
