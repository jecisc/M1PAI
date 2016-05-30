package com.partinizer.business.manager;

import com.partinizer.data.entity.Category;
import com.partinizer.data.entity.Resource;
import com.partinizer.data.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by vincent on 30/05/2016.
 */
@Component
public class CategoryManager {

    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryManager(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(long id){
        return categoryRepository.findOne(id);
    }
}
