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
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isEmpty()){
            throw new EntityNotFoundException();
        }
        List<Integer> categoriesId = getInnerCategoriesId(category.get(), new ArrayList<Integer>());
        for(Product product : productsList){
            // category search
            if((product.toString().contains(search.toLowerCase())) && (categoriesId.contains(product.getCategory().getId()))){
                newProductList.add(new ProductsGetDto(product));
            }
        }
        int productsSize = newProductList.size();
        int listStart = (page-1)* size;
        int listEnd = Math.min(page * size, productsSize); // productsSize < page * productsSize : returns products size
        return newProductList.subList(listStart, listEnd);
    }
    private List<Integer> getInnerCategoriesId(Category category, List<Integer> categories){
        categories.add(category.getId());
        List<Category> categoryChildren = category.getCategoryChildren();
        if(categoryChildren == null){
            return categories;
        }
        for(Category cat : categoryChildren){
            categories.add(cat.getId());
            List<Integer> innerCategories = getInnerCategoriesId(cat, new ArrayList<Integer>());
            categories.addAll(innerCategories);
        }
        return categories;
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
