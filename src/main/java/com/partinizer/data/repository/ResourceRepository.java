package com.partinizer.data.repository;

import com.partinizer.data.entity.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * I am a data access layers to manage the Ressource persistence.
 */
public interface ResourceRepository extends JpaRepository<Resource, Long> {

}
