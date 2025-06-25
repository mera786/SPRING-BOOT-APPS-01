package com.app.service;


import com.app.dto.ProductResponse;
import com.app.entity.Product;

public interface ProductService {
    Product createProduct(Product product);
    ProductResponse getAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);

}