package com.app.service.impl;

import com.app.entities.Product;
import com.app.repository.ProductRepository;
import com.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private  ProductRepository productRepository;


    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }


    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }


    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existingProduct =
                productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());

            existingProduct.setQuantity(updatedProduct.getQuantity());

            existingProduct.setSellerEmail(updatedProduct.getSellerEmail());


            existingProduct.setManufactureDate(updatedProduct.getManufactureDate());

            existingProduct.setExpiryDate(updatedProduct.getExpiryDate());
            existingProduct.setTags(updatedProduct.getTags());
            return productRepository.save(existingProduct);
        }
        return null;
    }


    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
