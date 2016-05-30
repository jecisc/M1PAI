package com.partinizer.data.repository;

import com.partinizer.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vincent on 30/05/2016.
 */
public interface CategoryRepository extends JpaRepository<Category,Long> {
}
