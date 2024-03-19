package com.cogent.main.dto;

import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDao {
	
	@Embedded
	private NameDao name;
	
	private String email;
	
	@Embedded
	private AddressDao address;
	

}
