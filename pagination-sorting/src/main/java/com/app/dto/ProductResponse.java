package com.app.dto;

import com.app.entity.Product;
import lombok.Data;

import java.util.List;

@Data
public class ProductResponse {
    private List<Product> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean isLast;

}