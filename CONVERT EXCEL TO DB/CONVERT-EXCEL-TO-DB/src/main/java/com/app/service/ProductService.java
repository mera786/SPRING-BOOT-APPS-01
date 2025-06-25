package com.app.service;

import com.app.entities.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    void saveProduct(MultipartFile file) throws IOException;
    List<Product> getProducts();
}
