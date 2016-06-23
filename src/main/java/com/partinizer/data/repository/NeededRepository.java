package com.partinizer.data.repository;

import com.partinizer.data.entity.Needed;
import com.partinizer.data.entity.NeededKeyId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * I am a data access layers to manage the Needed persistence.
 */
public interface NeededRepository extends JpaRepository<Needed, NeededKeyId> {

}
