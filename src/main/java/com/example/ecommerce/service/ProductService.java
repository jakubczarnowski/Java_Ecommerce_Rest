package com.example.ecommerce.service;

import com.example.ecommerce.Utils.ProductMapper;
import com.example.ecommerce.dto.product.ProductDto;
import com.example.ecommerce.dto.product.ProductEditDto;
import com.example.ecommerce.dto.product.ProductsGetDto;
import com.example.ecommerce.exceptions.NotFoundException;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.CategoryRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ProductMapper mapper;
    @Autowired
    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public Product createProductFromDto(ProductDto productDto, Category category) {
        Product updatedProduct = new Product();
        updatedProduct.setName(productDto.getName());
        updatedProduct.setDescription(productDto.getDescription());
        updatedProduct.setPrice(productDto.getPrice());
        updatedProduct.setImagesUrl(productDto.getImagesUrl());
        updatedProduct.setCategory(category);
        return updatedProduct;
    }

    // Get, {id}
    public Product getProductById(Integer id) {
        Optional<Product> tempCategory = productRepository.findById(id);
        if (tempCategory.isEmpty()) {
            throw new NotFoundException("Product with id " + id + " doesnt exist");
        }
        return tempCategory.get();
    }

    // Get all
    public List<ProductsGetDto> getProducts(int page, int size, String search, Integer categoryId) {
        List<Product> productsList = productRepository.findAll();
        List<ProductsGetDto> newProductList = new ArrayList<>();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        // my own pagination solution, don't try this
        // cant really figure it out the right way
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isEmpty()) {
            throw new NotFoundException("Category with id " + categoryId + " doesnt exist");
        }
        Set<Integer> categoriesId = getInnerCategoriesId(category.get(), new HashSet<Integer>());
        for (Product product : productsList) {
            Boolean isFavorite = product.getUserFavorite().stream().anyMatch(user -> Objects.equals(user.getUsername(), username));
            // category search
            if ((product.toString().contains(search.toLowerCase())) && (categoriesId.contains(product.getCategory().getId()))) {
                newProductList.add(new ProductsGetDto(product, isFavorite));
            }
        }
        int productsSize = newProductList.size();
        int listStart = (page - 1) * size;
        int listEnd = Math.min(page * size, productsSize); // productsSize < page * productsSize : returns products size
        return newProductList.subList(listStart, listEnd);
    }

    private Set<Integer> getInnerCategoriesId(Category category, Set<Integer> categories) {
        categories.add(category.getId());
        Set<Category> categoryChildren = category.getCategoryChildren();
        if (categoryChildren == null) {
            return categories;
        }
        for (Category cat : categoryChildren) {
            categories.add(cat.getId());
            Set<Integer> innerCategories = getInnerCategoriesId(cat, new HashSet<Integer>());
            categories.addAll(innerCategories);
        }
        return categories;
    }

    // Post, creating product
    public Product createProduct(ProductDto product) {
        Optional<Category> tempCategory = categoryRepository.findById(product.getCategoryId());

        if (tempCategory.isEmpty()) {
            throw new NotFoundException("Category with id " + product.getCategoryId() + " doesnt exist");
        }

        Product newProduct = createProductFromDto(product, tempCategory.get());
        return productRepository.save(newProduct);
    }

    // Delete, {id}
    public void removeProductById(Integer id) {
        Optional<Product> tempProduct = productRepository.findById(id);
        if (tempProduct.isEmpty()) {
            throw new NotFoundException("Product with id " + id + " doesnt exist");
        }
        tempProduct.get().getUserFavorite().forEach(user->user.deleteFavorite(tempProduct.get()));
        productRepository.delete(tempProduct.get());
    }

    public Product updateProduct(Integer id, ProductEditDto product) {
        Optional<Product> tempProduct = productRepository.findById(id);

        if (tempProduct.isEmpty()) {
            throw new NotFoundException("Product with id " + id + " doesnt exist");
        }
        mapper.updateProductFromDto(product, tempProduct.get());
        if(product.getCategoryId() != null){
            Optional<Category> tempCategory = categoryRepository.findById(product.getCategoryId());
            if(tempCategory.isEmpty()){
                throw new NotFoundException("Category with id " + id + " doesnt exist");
            }
            tempProduct.get().setCategory(tempCategory.get());
        }
        return productRepository.save(tempProduct.get());
    }
}
