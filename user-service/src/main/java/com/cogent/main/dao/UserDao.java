package com.cogent.main.dao;

import com.cogent.main.entity.AddressEntity;
import com.cogent.main.entity.NameEntity;
import com.cogent.main.entity.Role;

import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
	private NameEntity name;
	
	private String email;
	
	private String pass;
	
	@Embedded
	private AddressEntity address;
	
	@Enumerated(EnumType.STRING)
	private Role roles;

}
