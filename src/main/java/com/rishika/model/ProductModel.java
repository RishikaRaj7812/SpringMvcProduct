package com.rishika.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel 
{
	@NotEmpty(message = "Product name cannot be empty")
    @Size(min = 5,max = 100, message = "Product name should be between 5 to 100 characters")
    private String name;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be positive")
    private double price;

    @Min(value = 0, message = "Quantity cannot be negative")
    private int quantity;

    @NotEmpty(message = "Brand cannot be empty")
    @Size(min = 5,max = 50, message = "Brand cannot be longer than 50 characters")
    private String brand;

    @NotEmpty(message = "Made in field cannot be empty")
    @Size(min = 5,max = 50, message = "Made in field cannot be longer than 50 characters")
    private String madeIn;
	
}
