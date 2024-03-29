package com.example.ecommerce.controllers;

import com.example.ecommerce.config.ApiResponse;
import com.example.ecommerce.dto.category.CategoryDto;
import com.example.ecommerce.dto.category.CategoryEditDto;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Category> getCategories() {
        // returns root category containing first level categories in category tree
        return new ResponseEntity<>(categoryService.getCategoryById(1), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    // Authorized
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDto category) {
        Category newCategory = categoryService.createCategory(category);
        categoryService.addChildCategory(newCategory, category.getParentId());
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PatchMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryEditDto category) {
        return new ResponseEntity<>(categoryService.updateCategory(id, category), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> removeCategory(@PathVariable int id) {
        categoryService.removeCategoryById(id);
        return new ResponseEntity<>(new ApiResponse(true, "Deleted succesfully"), HttpStatus.OK);
    }

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
