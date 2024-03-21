package com.cogent.main.service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.main.dto.DiscountDao;
import com.cogent.main.entity.DiscountEntity;
import com.cogent.main.feignclient.UserClientinDiscount;
import com.cogent.main.repository.DiscountRepository;

@Service
public class DiscountService {
	
	@Autowired
	private UserClientinDiscount userClientinDiscount;
	
	@Autowired
	private DiscountRepository discountRepository;
	
	public Integer fetchDiscount(String discountCode) {
		Optional<DiscountEntity> discountEntity = discountRepository.findByCode(discountCode);
		
//		System.out.println(discountEntity);
//		if(discountEntity.isPresent()) {
//			byte[] dCode = Base64.getDecoder().decode(discountEntity.get().getDiscountCode());
//			return  DiscountDao.builder()
//					.discountCode(dCode)
//					.build();
//			
//		}else {
//			return new DiscountDao();
//		}
		return (int)discountEntity.get().getDiscountPrice();
	}

	public DiscountDao addDiscount(DiscountDao discountDao, String header) {
		if (userClientinDiscount.authUserHeader(header)) {
			String discountCode = Base64.getEncoder().encodeToString(discountDao.getDiscountCode().getBytes());
			DiscountEntity discount = DiscountEntity.builder().discountPrice(discountDao.getDiscountPrice())
					.discountCode(discountCode).build();
			discount = discountRepository.save(discount);

			return DiscountDao.builder().discountPrice(discount.getDiscountPrice())
					.discountCode(discount.getDiscountCode()).build();
		} else {
			return new DiscountDao();
		}
	}

	public DiscountDao removeDiscount(Integer discountId, String header) {
		if (userClientinDiscount.authUserHeader(header)) {
			Optional<DiscountEntity> discountEntity = discountRepository.findById(discountId);
			discountRepository.delete(discountEntity.get());
			return DiscountDao.builder()
					.discountCode(discountEntity.get().getDiscountCode())
					.discountPrice(discountEntity.get().getDiscountPrice())
					.build();
		} else {
			return new DiscountDao();
		}
	}

	public List<DiscountEntity> getAllDiscounts() {
		return discountRepository.findAll();
	}

	
	
	
}
