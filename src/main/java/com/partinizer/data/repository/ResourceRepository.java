package com.partinizer.data.repository;

import com.partinizer.data.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TODO
 */
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    Resource findById(Long id);

}
