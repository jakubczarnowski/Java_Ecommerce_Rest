package com.example.ecommerce.controllers;

import com.example.ecommerce.config.ApiResponse;
import com.example.ecommerce.dto.category.CategoryDto;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories(
        @RequestParam(required = false, name = "parentId") Integer parentId
    ){
        return new ResponseEntity<>(categoryService.getCategories(parentId), HttpStatus.FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategory(@PathVariable int id){
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDto category){
        Category newCategory = categoryService.createCategory(category);
        if(category.getParentId()!=null){
            categoryService.addChildCategory(newCategory, category.getParentId());
        }
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDto category){
        return new ResponseEntity<>(categoryService.updateCategory(id, category), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ApiResponse> removeCategory(@PathVariable int id){
        categoryService.removeCategoryById(id);
        return new ResponseEntity<>(new ApiResponse(true, "Deleted succesfully"), HttpStatus.OK);
    }

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
}
