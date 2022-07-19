package com.example.ecommerce.service;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    public Category createCategory(Category category) {
        if(categoryWithNameExists(category.getCategoryName())){
            // wyslac powiadomienie w postaci exception ze juz istnieje
        }
        return categoryRepository.save(category);
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void removeCategoryById(String id) {
        Optional<Category> tempCategory = categoryRepository.findById(id);
        if(tempCategory.isEmpty()){
            throw new EntityNotFoundException();
        }
        categoryRepository.delete(tempCategory.get());
    }

    public Category getCategoryById(String id){
        Optional<Category> tempCategory = categoryRepository.findById(id);
        if(tempCategory.isEmpty()){
            throw new EntityNotFoundException();
        }
        return tempCategory.get();
    }

    public Category updateCategory(String id, Category category) {
        Optional<Category> tempCategory = categoryRepository.findById(id);

        if(tempCategory.isEmpty()){
            throw new EntityNotFoundException();
        }
        category.setId(id);
        return categoryRepository.save(category);
    }
}
