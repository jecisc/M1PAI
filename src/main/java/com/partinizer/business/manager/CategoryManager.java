package com.partinizer.business.manager;

import com.partinizer.data.entity.Category;
import com.partinizer.data.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * I am a manager which should contains the behavior of the Categories of the project.
 */
@Component
public class CategoryManager {

    protected CategoryRepository categoryRepository;

    @Autowired
    public CategoryManager(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(long id) {
        return categoryRepository.findOne(id);
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}
