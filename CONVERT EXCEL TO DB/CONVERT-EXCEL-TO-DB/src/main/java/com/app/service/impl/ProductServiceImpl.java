package com.app.service.impl;

import com.app.entities.Product;
import com.app.helper.ExcelHelper;
import com.app.repository.ProductRepository;
import com.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;
    @Override
    public void saveProduct(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        List<Product> list = ExcelHelper.convertExcelToList(inputStream);
        repository.saveAll(list);
    }

    @Override
    public List<Product> getProducts() {
        return repository.findAll();
    }
}
