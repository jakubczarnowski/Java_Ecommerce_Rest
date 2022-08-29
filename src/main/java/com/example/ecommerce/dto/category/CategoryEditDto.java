package com.example.ecommerce.dto.category;

public class CategoryEditDto {
    private String categoryName;
    private String description;

    public CategoryEditDto(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    public CategoryEditDto() {
    }

    public CategoryEditDto(String categoryName, String description, String imageUrl, Integer parentId) {
        this.categoryName = categoryName;
        this.description = description;
    }

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

}
