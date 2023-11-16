package com.wecp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wecp.entities.Product;
import com.wecp.repos.ProductRepository;
import com.wecp.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ProductRepository prodctRepository;
	
	
	@PostMapping
	public Product createOrUpdate(@RequestBody Product product)
	{
		
		return productService.createOrUpdate(product);
		
	}
	
	@GetMapping
	public List<Product> getAllProdcts()
	{
		
		return productService.fetchAll();
		
	}
	
	@GetMapping("/productCategory/{productCategory}")
	public ResponseEntity<List<Product>> findByProductCategory(@PathVariable String productCategory) {
	    List<Product> products = productService.findByProductCategory(productCategory);
	    if (products.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<>(products, HttpStatus.OK);
	}

	@GetMapping("/subCategory/{subCategory}")
	public ResponseEntity<List<Product>> findBySubCategory(@PathVariable String subCategory) {
	    List<Product> products = productService.findBySubCategory(subCategory);
	    if (products.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@DeleteMapping("/productId/{productId}")
	public void deleteByProductId(@PathVariable String productId)
	{
		productService.delete(productId);
	}
	
	
	
	
	
	
	
}
