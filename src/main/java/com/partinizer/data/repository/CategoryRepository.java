package com.partinizer.data.repository;

import com.partinizer.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * I am a data access layers to manage the Category persistence.
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
