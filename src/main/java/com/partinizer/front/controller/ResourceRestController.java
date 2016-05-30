package com.partinizer.front.controller;

import com.partinizer.business.exceptions.ResourceDoesNotExistException;
import com.partinizer.business.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 */
@RestController
@RequestMapping("/resource")
public class ResourceRestController {

    protected ResourceService resourceService;

    @Autowired
    public ResourceRestController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> update() {


        System.out.println("test");

        try {
            this.resourceService.getResourceById(Long.valueOf("1"));
        } catch (ResourceDoesNotExistException e) {
            e.printStackTrace();
        }

        return null;

    }


}
