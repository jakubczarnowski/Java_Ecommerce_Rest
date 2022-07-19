package com.example.ecommerce.service;

import com.example.ecommerce.dto.product.ProductDto;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    // Get all
    public List<Product> getProducts(int page, int size, String search){

        List<Product> productsList = productRepository.findAll();
        return productsList;
    }

    // Post, creating product
    public Product createProduct(ProductDto product) {
        Optional<Category> tempCategory = categoryRepository.findById(product.getCategoryId());

        if(tempCategory.isEmpty()){
            throw new EntityNotFoundException();
        }

        Product newProduct = createProductFromDto(product, tempCategory.get());
        return productRepository.save(newProduct);
    }
    // Delete, {id}
    public void removeProductById(String id) {
        Optional<Product> tempCategory = productRepository.findById(id);
        if(tempCategory.isEmpty()){
            throw new EntityNotFoundException();
        }
        productRepository.delete(tempCategory.get());
    }

    // Get, {id}
    public Product getProductById(String id){
        Optional<Product> tempCategory = productRepository.findById(id);
        if(tempCategory.isEmpty()){
            throw new EntityNotFoundException();
        }
        return tempCategory.get();
    }

    public Product updateProduct(String id, ProductDto product) {
        Optional<Product> tempProduct = productRepository.findById(id);
        Optional<Category> tempCategory = categoryRepository.findById(product.getCategoryId());

        if(tempProduct.isEmpty()){
            throw new EntityNotFoundException();
        }
        if(tempCategory.isEmpty()){
            throw new EntityNotFoundException();
        }
        Product updatedProduct = createProductFromDto(product, tempCategory.get());
        return productRepository.save(updatedProduct);
    }

    public Product createProductFromDto(ProductDto productDto, Category category){
        Product updatedProduct = new Product();
        updatedProduct.setName(productDto.getName());
        updatedProduct.setDescription(productDto.getDescription());
        updatedProduct.setImagesUrl(productDto.getImagesUrl());
        updatedProduct.setPrice(productDto.getPrice());
        updatedProduct.setCategory(category);
        return updatedProduct;
    }
}
