package com.example.ecommerce.service;

import com.example.ecommerce.dto.product.ProductDto;
import com.example.ecommerce.dto.product.ProductsGetDto;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product createProduct(Product product) {
        product.setId(0);
        return productRepository.save(product);
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
    // Get, {id}
    public Product getProductById(Integer id){
        Optional<Product> tempCategory = productRepository.findById(id);
        if(tempCategory.isEmpty()){
            throw new EntityNotFoundException();
        }
        return tempCategory.get();
    }
    // Get all
    public List<ProductsGetDto> getProducts(int page, int size, String search, Integer categoryId){
        List<Product> productsList = productRepository.findAll();
        List<ProductsGetDto> newProductList = new ArrayList<>();
        // my own pagination solution, don't try this
        // cant really figure it out the right way
        for(Product product : productsList){
            // category search
            if((product.toString().contains(search.toLowerCase())) && (categoryChildrenContainCategory(categoryId, product.getCategory()))){
                newProductList.add(new ProductsGetDto(product));
            }
        }
        int productsSize = newProductList.size();
        int listStart = (page-1)* productsSize;
        int listEnd = Math.min(page * productsSize, productsSize); // productsSize < page * productsSize : returns products size
        return newProductList.subList(listStart, listEnd);
    }
    private Boolean categoryChildrenContainCategory(Integer categoryId, Category category){
        if(categoryId==null){
            return true;
        }
        List<Category> categoryChildren = category.getCategoryChildren();
        if(category.getId() == categoryId){
            return true;
        }
        if(categoryChildren.isEmpty()){
            return false;
        }
        for(Category childCateogry : categoryChildren){
            // recursive search
            if(categoryChildrenContainCategory(categoryId, childCateogry)){
                return true;
            }
        }
        return false;
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
    public void removeProductById(Integer id) {
        Optional<Product> tempCategory = productRepository.findById(id);
        if(tempCategory.isEmpty()){
            throw new EntityNotFoundException();
        }
        productRepository.delete(tempCategory.get());
    }

    public Product updateProduct(Integer id, ProductDto product) {
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
}
