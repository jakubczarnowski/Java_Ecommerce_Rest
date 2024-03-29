package com.example.ecommerce.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {
    @Column(name = "category_name", unique = true)
    private @NotNull String categoryName;
    private @NotNull String description;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Category> categoryChildren = new HashSet<>();

    private @NotNull Integer parentCategoryId;

    public Category() {

    }

    public Category(@NotBlank String categoryName, @NotBlank String description, @NotBlank Integer parentCategoryId) {
        super();
        this.categoryName = categoryName;
        this.description = description;
        this.parentCategoryId = parentCategoryId;
    }

    public Category(@NotBlank String categoryName, @NotBlank String description) {
        super();
        this.categoryName = categoryName;
        this.description = description;
    }

    @Override
    public String toString() {
        return "User {category id=" + id + ", category name='" + categoryName + "', description='" + description + "'}";
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addChildCategory(Category category) {
        this.categoryChildren.add(category);
    }

    public void deleteChildCategory(Category category) {
        this.categoryChildren.remove(category);
    }

    public Set<Category> getCategoryChildren() {
        return categoryChildren;
    }

    public void setCategoryChildren(Set<Category> categoryChildren) {
        this.categoryChildren = categoryChildren;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}
