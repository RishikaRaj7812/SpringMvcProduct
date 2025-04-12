package com.rishika.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.rishika.entity.ProductEntity;
import com.rishika.model.ProductModel;
import com.rishika.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestParam;




@Controller
public class ProductController 
{
	@Autowired
	ProductService productService;
	
	@GetMapping("/productform")
	public String getProduct(Model model) 
	{
		ProductModel productModel = new ProductModel();
		productModel.setBrand("APPLE");
		productModel.setMadeIn("INDIA");
		productModel.setQuantity(1);
		
		model.addAttribute("productModel", productModel);
		
		return "product";
	}
	
	
	
	
	@PostMapping("/products")
	public String postMethodName(@Valid @ModelAttribute ProductModel productModel,BindingResult bindResult) 
	{
		if(bindResult.hasErrors())
		{
			return "product";
		}
		
		productService.saveProduct(productModel);
		return "success";
	}
	
	@GetMapping("/getAllProduct")
	public String getAllProduct(Model model) 
	{
		List<ProductEntity> products = productService.getAllProducts();
		model.addAttribute("products", products);
		return "product-list";
	}
	
	@GetMapping("/back")
	public String getback() 
	{
		return "redirect:/productform";
	}
	
	@GetMapping("/mainform")
	public String getMain() 
	{
		return "redirect:/productform";
	}
	
	 @GetMapping("/findproduct/{id}")
	 public String findProductById(@PathVariable Long id, Model model) 
	 {
		 try 
	     {
	         ProductEntity product = productService.getProductById(id);  
	         model.addAttribute("product", product); 
	         return "product-detail";  
	     } 
	     catch (RuntimeException e) 
	     {
	         model.addAttribute("message", e.getMessage()); 
	         return "hello";  
	     }   
	 }
	
		
		  @GetMapping("/deleteproduct/{id}") public String getProductById(@PathVariable Long id) 
		  { 
			  ProductEntity product = productService.deleteProductById(id);
			  return "redirect:/getAllProduct"; 
		  }
		  
		  

//	 @GetMapping("/editproduct/{id}")
//	 public String editProductById(@PathVariable Long id, Model model) {
//	     ProductEntity product = productService.getProductById(id);
//	     if (product == null) {
//	         return "redirect:/getAllProduct";
//	     } else {
//	         model.addAttribute("product", product); // Pass the product to the view
//	         model.addAttribute("id", id);
//	         return "edit-product"; // Thymeleaf template
//	     }
//	 }
		  
		  @GetMapping("/editproduct/{id}")
		  public String editProductById(@PathVariable Long id, Model model) {
		      ProductModel productModel = productService.getEditProduct(id);
		      
		      if (productModel == null) {
		          model.addAttribute("error", "Product not found.");
		          return "redirect:/getAllProduct";  // Optionally, you can show an error page or message here.
		      } else {
		    	  model.addAttribute("productModel",productModel);
                  
                  model.addAttribute("id", id);
		          return "edit"; // Thymeleaf template
		      }
		  }


	 @PostMapping("/updateProduct/{id}")
	 public String getupdateProduct(@PathVariable("id") Long id, ProductModel productModel) {
	     productService.updateProduct(id, productModel);
	     return "redirect:/getAllProduct";  // Redirect after the update
	 }

	
	 
	 
}
