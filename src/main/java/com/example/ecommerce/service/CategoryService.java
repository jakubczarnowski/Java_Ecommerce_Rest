package com.example.ecommerce.service;

import com.example.ecommerce.dto.category.CategoryDto;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    CategoryRepository categoryRepository;

    public boolean categoryWithNameExists(String categoryName){
        Optional<Category> tempCategory = categoryRepository.findByCategoryName(categoryName);
        if(tempCategory.isEmpty()){
            // do zmiany na wlasne
            return false;
        }
        return true;
    }

    @Autowired
    public CategoryService(CategoryRepository categoryRepository){
        super();
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(CategoryDto category) {
        if(categoryWithNameExists(category.getCategoryName())){
            // wyslac powiadomienie w postaci exception ze juz istnieje
        }
        Category newCategory = createCategoryFromDto(category);
        newCategory.setId(0);
        return categoryRepository.save(newCategory);
    }

    public List<Category> getCategories(Integer parentId) {
        // powinno byc query ale na razie bedzie w ten sposob.
        List<Category> categories =  categoryRepository.findAll();
        return categories;
    }

    public void removeCategoryById(int id) {
        Optional<Category> tempCategory = categoryRepository.findById(id);
        if(tempCategory.isEmpty()){
            throw new EntityNotFoundException();
        }
        categoryRepository.delete(tempCategory.get());
    }

    public Category getCategoryById(int id){
        Optional<Category> tempCategory = categoryRepository.findById(id);
        if(tempCategory.isEmpty()){
            throw new EntityNotFoundException();
        }
        return tempCategory.get();
    }

    public Category updateCategory(Integer id, CategoryDto category) {
        Optional<Category> tempCategory = categoryRepository.findById(id);
        if(tempCategory.isEmpty()){
            throw new EntityNotFoundException();
        }
        Category newCategory = createCategoryFromDto(category);
        newCategory.setId(id);
        return categoryRepository.save(newCategory);
    }
    private Category createCategoryFromDto(CategoryDto categoryDto){
        Category newCategory = new Category();
        newCategory.setCategoryName(categoryDto.getCategoryName());
        newCategory.setDescription(categoryDto.getDescription());
        newCategory.setImageUrl(categoryDto.getImageUrl());
        return newCategory;
    }

    public void addChildCategory(Category newCategory, Integer parentId) {

        Optional<Category> parentCategory = categoryRepository.findById(parentId);
        if(parentCategory.isPresent()){
            parentCategory.get().addChildCategory(newCategory);
            categoryRepository.save(parentCategory.get());
        }
        else{
            throw new EntityNotFoundException();
        }
    }
}
