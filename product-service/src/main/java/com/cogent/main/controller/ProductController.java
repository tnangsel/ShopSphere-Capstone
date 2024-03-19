package com.cogent.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cogent.main.dao.ProductDao;
import com.cogent.main.entity.ProductEntity;
import com.cogent.main.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
//------------------------------Public end-points----------------------------	
	@GetMapping("/")
	public List<ProductEntity> getAllProducts() {
		return productService.fetchAllProducts();
	}
	
	@GetMapping("/{productId}")
	public ProductDao getProductById(@PathVariable("productId") Integer productId) {
		return productService.fetchAProduct(productId);
	}
	
	@GetMapping("/categories/{categoryName}")
	public List<ProductEntity> getProductsByCategory(@PathVariable("categoryName") String category){
		return productService.getProductListByCategory(category);
	}
	
//---------------------Admin calls the below method---------------------------
	@PostMapping("/products")
    public ProductEntity addProduct(@RequestBody ProductDao productDao ) {
        return productService.insertProduct(productDao);
    }

    @PutMapping("/products/{productId}")
    public ProductEntity updateProduct(@PathVariable("productId") Integer productId,
    										@RequestBody ProductDao productDao) {
        return productService.productToUpdate(productId, productDao);
    }

    @DeleteMapping("/products/{productId}")
    public String deleteProduct(@PathVariable("productId") Integer productId) {
        return productService.deleteProduct(productId);
    }
 
//----------------------------------Cart-service-----------------------------------
	
    @GetMapping("/getOneProduct/{productId}")
	public ProductDao getOneProductById(@PathVariable("productId") Integer productId) {
		return productService.fetchAProduct(productId);
	}
    
//    @GetMapping("/getAllProductDaos/{productId}")
//	public List<ProductDao> getAllProducts(@PathVariable("productId") Integer productId) {
//		return productService.fetchAllProductDaos(productId);
//	}
    
}
