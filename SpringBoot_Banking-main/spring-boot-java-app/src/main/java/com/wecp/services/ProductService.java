package com.wecp.services;

import java.util.List;

import com.wecp.entities.Product;

public interface ProductService {
	
	public Product createOrUpdate(Product product);
	
	List<Product> fetchAll();
	
	public void delete(String productId);
	
	List<Product> findByProductCategory(String productCategory);
	
	List<Product> findBySubCategory(String SubCategory);

}
