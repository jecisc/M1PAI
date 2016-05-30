package com.partinizer.business.manager;

import com.partinizer.business.exceptions.ResourceDoesNotExistException;
import com.partinizer.data.entity.Resource;
import com.partinizer.data.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO
 */
@Component
public class ResourceManager {

    protected ResourceRepository resourceRepository;

    @Autowired
    public ResourceManager(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    /**
     * I return the resource matching an id.
     *
     * @param id The id of the resource.
     * @return The resource matching the id.
     * @throws ResourceDoesNotExistException raised of there is no resource with this id.
     */
    public Resource getResourceById(Long id) throws ResourceDoesNotExistException {
        Resource resource = this.resourceRepository.findById(id);
        if (resource == null) {
            ResourceDoesNotExistException exception = new ResourceDoesNotExistException();
            exception.setId(id);
            throw exception;
        }
        return resource;
    }
}
