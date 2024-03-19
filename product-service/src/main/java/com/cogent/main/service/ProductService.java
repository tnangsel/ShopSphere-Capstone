package com.cogent.main.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cogent.main.dao.ProductDao;
import com.cogent.main.entity.ProductEntity;
import com.cogent.main.exception.ProductNotFoundException;
import com.cogent.main.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;


	public List<ProductEntity> fetchAllProducts() {
		return productRepository.findAll();
	}
	
	public ProductDao fetchAProduct(Integer productId) {
        Optional<ProductEntity> productEntity = productRepository.findById(productId); 
        if(productEntity.isPresent()) {
        	return ProductDao.builder()
        		.title(productEntity.get().getTitle())
        		.description(productEntity.get().getDescription())
        		.price(productEntity.get().getPrice())
        		.category(productEntity.get().getCategory())
        		.build();
        }else {
        	throw new ProductNotFoundException("Product does not exist");
        }
    }
	
	//list of products by category name
	public List<ProductEntity> getProductListByCategory(String categoryName) {
        return productRepository.findByCategory(categoryName);
    }
	
	
//-------------------------Admin methods implementations------------------
	
	//add product
	public ProductEntity insertProduct(ProductDao productDao) {
		
//		Optional<ProductEntity> pE = productRepository.findByTitle(productDao.getTitle());
//		System.out.println(pE);
//		String ptitle = productDao.getTitle();
//		if (productRepository.findByTitle(ptitle) != null) {
//	        throw new ProductNotFoundException("Product with title already exists");
//	    }
		
		ProductEntity productEntity = ProductEntity.builder()
				.title(productDao.getTitle())
				.description(productDao.getDescription())
				.price(productDao.getPrice())
				.category(productDao.getCategory())
				.build();
		 return productRepository.save(productEntity);	
	}

	
	//update product
	public ProductEntity productToUpdate(Integer productId, ProductDao productDao) {
		Optional<ProductEntity> existingProduct = productRepository.findById(productId);
		if (existingProduct.isPresent()) {
			existingProduct.get().setTitle(productDao.getTitle());
			existingProduct.get().setDescription(productDao.getDescription());
			existingProduct.get().setPrice(productDao.getPrice());
			existingProduct.get().setCategory(productDao.getCategory());

			return productRepository.save(existingProduct.get());
		} else {
			throw new ProductNotFoundException("Product does not exist");
		}
    }
	
	//delete product by product id
	public String deleteProduct(Integer productId) {
		Optional<ProductEntity> deleteProduct = productRepository.findById(productId);
		if(deleteProduct.isPresent()) {
			productRepository.delete(deleteProduct.get());
			return "Product with id : " + productId + " is deleted.";
		}else {
			return "Product with id : " + productId + " does not exist";
		}
	}

	public List<ProductDao> fetchAllProductDaos(Integer productId) {
		List<ProductEntity> productEntities = fetchAllProducts();
		List<ProductDao> productDaos = new ArrayList<>();
		Optional<ProductEntity> productEntity = productRepository.findById(productId);
		if (productEntity.isPresent()) {
			for (ProductEntity product : productEntities) {
				ProductDao productDao = ProductDao.builder().title(product.getTitle())
						.description(product.getDescription()).price(product.getPrice()).category(product.getCategory())
						.build();
				productDaos.add(productDao);
			}
			return productDaos;
		} else {
			throw new ProductNotFoundException("Product does not exists");
		}
	}

	
}
