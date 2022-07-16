package com.example.ecommerce.service;

import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductService {
    private ProductRepository productRepository;



    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
