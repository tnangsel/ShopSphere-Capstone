/**
 * @author: Tenzin 
 * @date : Feb 13, 2024
 */
package com.cogent.main.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TokenEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String token;
	private boolean expired;
	private boolean revoked;
	
	@Enumerated(EnumType.STRING)
	private TokenType tokenType;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_entity_id")
	private UserEntity userEntity;
	
}
