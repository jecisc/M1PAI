package com.partinizer.business.service;

import com.partinizer.business.exceptions.ResourceDoesNotExistException;
import com.partinizer.business.manager.ResourceManager;
import com.partinizer.data.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TODO
 **/
@Service
public class ResourceService {

    protected ResourceManager resourceManager;

    @Autowired
    public ResourceService(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    /**
     * I return the resource matching an id.
     *
     * @param id The id of the resource.
     * @return The resource matching the id.
     * @throws ResourceDoesNotExistException raised of there is no resource wth this pseudo.
     */
    public Resource getResourceById(Long id) throws ResourceDoesNotExistException {
        return resourceManager.getResourceById(id);
    }
}
