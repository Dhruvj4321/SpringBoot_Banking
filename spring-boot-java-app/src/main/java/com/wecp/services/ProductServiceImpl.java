package com.wecp.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wecp.entities.Product;
import com.wecp.repos.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	ProductRepository repository;
	
	public Product createOrUpdate(Product product)
	{
		Product existingProduct = repository.findByProductId(product.getProductId());
		
		if(existingProduct != null)
		{
			existingProduct.setColor(product.getColor());
			existingProduct.setUnit(product.getUnit());
			existingProduct.setPrice(product.getPrice());
			existingProduct.setProductCategory(product.getProductCategory());
			existingProduct.setSubCategory(product.getSubCategory());
			
			return repository.save(existingProduct);
		}
		else
		{
			return repository.save(product);
		}
	}

	@Override
	public List<Product> fetchAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public void delete(String productId) {
		// TODO Auto-generated method stub
		repository.deleteByProductId(productId);
	}

	@Override
	public List<Product> findByProductCategory(String productCategory) {
	    return repository.findByProductCategory(productCategory);
	}

	@Override
	public List<Product> findBySubCategory(String subCategory) {
	    return repository.findBySubCategory(subCategory);
	}
	

}
