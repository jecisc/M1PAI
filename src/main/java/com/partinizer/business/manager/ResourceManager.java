package com.partinizer.business.manager;


import com.partinizer.data.entity.Resource;
import com.partinizer.data.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * I am a manager which should contains the behavior of the Resources of the project.
 */
@Component
public class ResourceManager {

    protected ResourceRepository resourceRepository;

    @Autowired
    public ResourceManager(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> getAllResources(){
        return resourceRepository.findAll();
    }

}
