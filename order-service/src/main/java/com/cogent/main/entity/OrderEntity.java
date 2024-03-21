package com.cogent.main.entity;

import java.util.List;

import com.cogent.main.dto.UserDao;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String status;
	
	@Embedded
    private UserDao user;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER) 
    @JoinColumn(name = "order_id") 
    private List<ProductEntity> products;
}
