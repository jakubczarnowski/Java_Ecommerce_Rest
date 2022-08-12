package com.example.ecommerce.dto.category;

import com.example.ecommerce.model.BaseEntity;

import javax.validation.constraints.NotNull;

public class CategoryDto extends BaseEntity {
    private @NotNull String categoryName;
    private @NotNull String description;
    private Integer parentId = 1; // if not provided, automatically add to a root category

    public CategoryDto(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    public CategoryDto() {
    }

    public CategoryDto(String categoryName, String description, String imageUrl, Integer parentId) {
        this.categoryName = categoryName;
        this.description = description;
        this.parentId = parentId;
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


    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
}
