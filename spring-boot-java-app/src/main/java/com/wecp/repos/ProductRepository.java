package com.wecp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wecp.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
	List<Product> findByProductCategory(String productCategory);
	
	List<Product> findBySubCategory(String subCategory);
	
//	List<Product> findByProductId1(String productId);
	
	List<Product> findAll();
	
	Product findByProductId(String productId);
	
	Product deleteByProductId(String ProductId);

}
