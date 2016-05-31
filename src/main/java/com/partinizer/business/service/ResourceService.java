package com.partinizer.business.service;

import com.partinizer.business.exceptions.ResourceDoesNotExistException;
import com.partinizer.business.manager.CategoryManager;
import com.partinizer.business.manager.ResourceManager;
import com.partinizer.data.entity.Category;
import com.partinizer.data.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO
 **/
@Service
public class ResourceService {

    protected ResourceManager resourceManager;
    protected CategoryManager categoryManager;

    @Autowired
    public ResourceService(ResourceManager resourceManager,CategoryManager categoryManager) {
        this.resourceManager = resourceManager;
        this.categoryManager=categoryManager;
    }

    /**
     * I return all categories and their resources inside.
     *
     * @return The list of all category .
     * @throws ResourceDoesNotExistException raised of there is no resource wth this pseudo.
     */
    public List<Category> getAllResourcesByCategory(){
        return categoryManager.getAllCategory();
    }

    /**
     * I return the resource matching an id.
     *
     * @param id The id of the resource.
     * @return The resource matching the id.
     * @throws ResourceDoesNotExistException raised of there is no resource wth this pseudo.
     */
   /* public Resource getResourceById(Long id) throws ResourceDoesNotExistException {
        return resourceManager.getResourceById(id);
    }*/
}
