package com.partinizer.data.repository;

import com.partinizer.data.entity.Needed;
import com.partinizer.data.entity.NeededKeyId;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by vincent on 19/06/2016.
 */
public interface NeededRepository extends JpaRepository<Needed,NeededKeyId> {


}
