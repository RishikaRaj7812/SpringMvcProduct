package com.rishika.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rishika.entity.ProductEntity;
import com.rishika.model.ProductModel;
import com.rishika.repository.ProductRepository;

@Service
public class ProductService 
{
	@Autowired
	ProductRepository productRepository;
	
	public void saveProduct(ProductModel productModel)
	{
		double price = productModel.getPrice();
		double quantity = productModel.getQuantity();
		double amount = price * quantity;
		double taxAmount = amount * 0.18;
		double totalAmount = amount + taxAmount;
		
		ProductEntity productEntity = new ProductEntity();
		
		productEntity.setName(productModel.getName());
		productEntity.setPrice(productModel.getPrice());
		productEntity.setTaxAmount(taxAmount);
		productEntity.setTotalAmount(totalAmount);
		productEntity.setQuantity(productModel.getQuantity());
		productEntity.setCreateAt(LocalDateTime.now());
		productEntity.setCreateBy(System.getProperty("user.name"));
		productEntity.setBrand(productModel.getBrand());
		productEntity.setMadeIn(productModel.getMadeIn());
		
		// save the Entity class in database
		
		productRepository.save(productEntity);
	}


	public List<ProductEntity> getAllProducts() 
	{
		List<ProductEntity> products = productRepository.findAll();
		return products;
	}


	public ProductEntity getProductById(Long id) 
	{
        Optional<ProductEntity> product = productRepository.findById(id);
        
        if (product.isPresent()) 
        {
            return product.get();  // return the product if found
        } else 
        {
            throw new RuntimeException("Product not found with ID: " + id);
        }
    }

	public ProductEntity deleteProductById(Long id) 
	{
	    // Check if product exists before deleting it
	    Optional<ProductEntity> productOptional = productRepository.findById(id);
	    
	    if (productOptional.isPresent()) 
	    {
	        productRepository.deleteById(id);
	    } else 
	    {
	        throw new RuntimeException("Product with ID " + id + " not found.");
	    }
		return null;
	}

	public ProductModel getEditProduct(Long id)
	{
		ProductEntity productEntity = productRepository.findById(id).get();
		
		ProductModel productModel = new ProductModel();
		
		productModel.setName(productEntity.getName());
		productModel.setPrice(productEntity.getPrice());
		productModel.setQuantity(productEntity.getQuantity());
		productModel.setBrand(productEntity.getBrand());
		productModel.setMadeIn(productEntity.getMadeIn());
		
		return productModel;
	}
	
	
	  public void updateProduct(Long id, ProductModel productModel) 
	  { 
	  ProductEntity existingProduct = productRepository.findById(id).orElse(null);
	  
	  	double price = productModel.getPrice();
		double quantity = productModel.getQuantity();
		double amount = price * quantity;
		double taxAmount = amount * 0.18;
		double totalAmount = amount + taxAmount;
	  
	  existingProduct.setName(productModel.getName());
	  existingProduct.setBrand(productModel.getBrand());
	  existingProduct.setQuantity(productModel.getQuantity());
	  existingProduct.setMadeIn(productModel.getMadeIn());
	  existingProduct.setPrice(productModel.getPrice());
	  existingProduct.setTaxAmount(taxAmount);
	  existingProduct.setTotalAmount(totalAmount);
		
	  productRepository.save(existingProduct);
	  }
	 

	}
