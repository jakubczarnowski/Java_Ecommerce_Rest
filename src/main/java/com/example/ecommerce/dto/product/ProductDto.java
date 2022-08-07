package com.example.ecommerce.dto.product;


import com.example.ecommerce.model.BaseEntity;
import com.example.ecommerce.model.Product;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ProductDto extends BaseEntity {

    private @NotNull String name;
    private @NotNull List<String> imagesUrl;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Integer categoryId;

    public ProductDto(@NotNull String name, @NotNull List<String> imagesURL, @NotNull double price, @NotNull String description, @NotNull Integer categoryId, @NotNull Boolean isFavorite) {
        this.name = name;
        this.imagesUrl = imagesURL;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
    }

    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setImagesUrl(product.getImagesUrl());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setCategoryId(product.getCategory().getId());
    }

    public ProductDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(List<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

}
