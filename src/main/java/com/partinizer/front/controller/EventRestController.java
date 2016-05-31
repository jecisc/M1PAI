package com.partinizer.front.controller;

import com.partinizer.business.exceptions.EventDoesNotExistException;
import com.partinizer.business.service.EventService;
import com.partinizer.business.service.ResourceService;
import com.partinizer.data.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * TODO
 */
@RestController
@RequestMapping("/event")
public class EventRestController {

    protected EventService eventService;
    protected ResourceService resourceService;

    @Autowired
    public EventRestController(EventService eventService,ResourceService resourceService) {
        this.eventService = eventService;
        this.resourceService=resourceService;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public ResponseEntity<String> update() {


        System.out.println("test");

        try {
            this.eventService.getEventById(Long.valueOf("1"));
        } catch (EventDoesNotExistException e) {
            e.printStackTrace();
        }

        return null;

    }

    @RequestMapping(value = "/getAllResources", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllRessources(){

        return new ResponseEntity<>(resourceService.getAllResourcesByCategory(),HttpStatus.OK);
    }


}
