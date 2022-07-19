package com.example.ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="categories")
public class Category extends BaseEntity
{
    @Column(name="category_name", unique = true)
    private @NotNull String categoryName;
    private @NotNull String description;
    private String imageUrl;

    public Category(){

    }
    public Category(String categoryName, String description, String imageUrl) {
        super();
        this.categoryName = categoryName;
        this.description = description;
        this.imageUrl = imageUrl;
    }
    public Category(String categoryName, String description) {
        super();
        this.categoryName = categoryName;
        this.description = description;
    }
    @Override
    public String toString() {
        return "User {category id=" + id + ", category name='" + categoryName + "', description='" + description + "'}";
    }
    // getters setters
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
