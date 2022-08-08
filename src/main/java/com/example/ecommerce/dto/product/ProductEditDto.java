package com.example.ecommerce.dto.product;


import com.example.ecommerce.model.Product;

public class ProductEditDto {

    private String name;
    private double price;
    private String description;
    private Integer categoryId;

    public ProductEditDto(String name, double price, String description, Integer categoryId, Boolean isFavorite) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.categoryId = categoryId;
    }

    public ProductEditDto(Product product) {
        this.setName(product.getName());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setCategoryId(product.getCategory().getId());
    }

    public ProductEditDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
