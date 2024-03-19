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
public class UserDao {
	
	
	@Embedded
	private NameDao name;
	@Embedded
	private AddressDao address;

	
}
