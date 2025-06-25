package com.app.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Product name is required")
    private String name;
    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.01", message = "Price must be greater than or equal to 0.01")
    private Double price;
    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;
    @Email(message = "Invalid email format")
    private String sellerEmail;
    @FutureOrPresent(message = "Manufacture date must be in the future or the present")
    private LocalDate manufactureDate;
    @FutureOrPresent(message = "Expiry date must be in the future or the present")
    private LocalDate expiryDate;
    @Size(min = 2, max = 50, message = "Tags must have 2 to 50 characters")
    private String tags;
}
