package com.partinizer.business.manager;


import com.partinizer.data.entity.Resource;
import com.partinizer.data.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by vincent on 30/05/2016.
 */
@Component
public class ResourceManager {

    private ResourceRepository resourceRepository;

    @Autowired
    public ResourceManager(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> getAllResources(){
        return resourceRepository.findAll();
    }



    public ResourceRepository getResourceRepository() {
        return resourceRepository;
    }

    public void setResourceRepository(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }
}
