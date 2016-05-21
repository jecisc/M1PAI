package com.partinizer.front.controller;

import com.partinizer.business.exceptions.EventDoesNotExistException;
import com.partinizer.business.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 */
@RestController
@RequestMapping("/event")
public class EventRestController {

    protected EventService eventService;

    @Autowired
    public EventRestController(EventService eventService) {
        this.eventService = eventService;
    }
    
    @CrossOrigin
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


}
