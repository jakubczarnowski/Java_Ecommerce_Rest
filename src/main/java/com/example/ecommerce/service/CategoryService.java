package com.example.ecommerce.service;

import com.example.ecommerce.Utils.CategoryMapper;
import com.example.ecommerce.dto.category.CategoryDto;
import com.example.ecommerce.dto.category.CategoryEditDto;
import com.example.ecommerce.exceptions.NotFoundException;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;
    CategoryMapper mapper;
    public boolean categoryWithNameExists(String categoryName) {
        Optional<Category> tempCategory = categoryRepository.findByCategoryName(categoryName);
        // do zmiany na wlasne
        return tempCategory.isPresent();
    }

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, CategoryMapper mapper) {
        super();
        this.categoryRepository = categoryRepository;
        this.mapper = mapper;
    }

    public Category createCategory(CategoryDto category) {
        if (categoryWithNameExists(category.getCategoryName())) {
            // wyslac powiadomienie w postaci exception ze juz istnieje
        }
        Category newCategory = createCategoryFromDto(category);
        newCategory.setId(0);
        return categoryRepository.save(newCategory);
    }

    public void removeCategoryById(int id) {
        if (id == 1) {
            throw new NotFoundException("Can't remove root category");
        }
        Optional<Category> tempCategory = categoryRepository.findById(id);
        if (tempCategory.isEmpty()) {
            throw new NotFoundException("Category with id " + id + " doesnt exist");
        }
        Integer parentId = tempCategory.get().getParentCategoryId();
        Optional<Category> parentTemp = categoryRepository.findById(parentId);
        parentTemp.get().deleteChildCategory(tempCategory.get());
        categoryRepository.save(parentTemp.get());
    }

    public Category getCategoryById(int id) {
        Optional<Category> tempCategory = categoryRepository.findById(id);
        if (tempCategory.isEmpty()) {
            throw new NotFoundException("Category with id " + id + " doesnt exist");
        }
        return tempCategory.get();
    }

    public Category updateCategory(Integer id, CategoryEditDto category) {
        Optional<Category> tempCategory = categoryRepository.findById(id);
        if (tempCategory.isEmpty()) {
            throw new NotFoundException("Category with id " + id + " doesnt exist");
        }
        mapper.updateProductFromDto(category, tempCategory.get());
        System.out.println(tempCategory.get().toString());
        return categoryRepository.save(tempCategory.get());
    }
    private Category createCategoryFromDto(CategoryDto categoryDto) {
        Category newCategory = new Category();
        newCategory.setCategoryName(categoryDto.getCategoryName());
        newCategory.setDescription(categoryDto.getDescription());
        newCategory.setParentCategoryId(categoryDto.getParentId());
        return newCategory;
    }

    public void addChildCategory(Category newCategory, Integer parentId) {

        Optional<Category> parentCategory = categoryRepository.findById(parentId);
        if (parentCategory.isPresent()) {
            parentCategory.get().addChildCategory(newCategory);
            categoryRepository.save(parentCategory.get());
        } else {
            throw new NotFoundException("Category with id " + parentId + " doesnt exist");
        }
    }
}
