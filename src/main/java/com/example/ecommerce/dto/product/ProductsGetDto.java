package com.example.ecommerce.dto.product;

import com.example.ecommerce.model.Product;

import javax.validation.constraints.NotNull;
import java.util.List;

public class ProductsGetDto {
    private @NotNull Integer id;
    private @NotNull String name;
    private @NotNull List<String> imagesUrl;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Integer categoryId;
    private @NotNull String categoryName;
    private @NotNull Boolean isFavorite;
    // ProductDto from product
    public ProductsGetDto(Product product, Boolean isFavorite) {
        this.id = product.getId();
        this.name = product.getName();
        this.imagesUrl = product.getImagesUrl();
        this.price = product.getPrice();
        this.description = product.getDescription();
        this.categoryId = product.getCategory().getId();
        this.categoryName = product.getCategory().getCategoryName();
        this.isFavorite = isFavorite;
    }

    public ProductsGetDto(Integer id, String name, List<String> imagesUrl, double price, String description, Integer categoryId,String categoryName, Boolean isFavorite) {
        this.id = id;
        this.name = name;
        this.imagesUrl = imagesUrl;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
        this.isFavorite = isFavorite;
        this.categoryName = categoryName;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getFavorite() {
        return isFavorite;
    }

    public void setFavorite(Boolean favorite) {
        isFavorite = favorite;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
