package com.app.service.impl;


import com.app.dto.ProductResponse;
import com.app.entity.Product;

import com.app.repository.ProductRepository;
import com.app.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {


    @Autowired
    private ProductRepository repository;
    @Override
    public Product createProduct(Product product) {
        return repository.save(product);
    }

    @Override
    public ProductResponse getAllProducts(int pageNo, int pageSize, String
            sortBy, String sortDir) {

        //turn-ari operator like if else condition
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() :
                Sort.by(sortBy).descending();

        // INSTEAD OF DOING TURN-ARI OPERATOR OPERATION DO if else condition
// Sort sort=null;
// if(sortDir.equalsIgnoreCase("asc")){
// sort=Sort.by(sortBy).ascending();
// }else {
// sort= Sort.by(sortBy).descending();
// }

        //creating page object
        PageRequest pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Product> content = repository.findAll(pageable);

        //converting pages to list
        List<Product> products = content.getContent();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(products);
        productResponse.setPageNo(content.getNumber());
        productResponse.setPageSize(content.getSize());
        productResponse.setTotalPages(content.getTotalPages());
        productResponse.setTotalElements(content.getTotalElements());
        productResponse.setLast(content.isLast());
        return productResponse;

    }
}
