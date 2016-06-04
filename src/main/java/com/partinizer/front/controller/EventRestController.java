package com.partinizer.front.controller;

import com.partinizer.business.exceptions.EventDoesNotExistException;
import com.partinizer.business.service.EventService;
import com.partinizer.business.service.ResourceService;
import com.partinizer.data.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @CrossOrigin
    @RequestMapping(value = "/events", method=RequestMethod.GET)
    public ResponseEntity<List<Event>> getAllEvents() {

        return new ResponseEntity<>(eventService.getAllEvents(),HttpStatus.OK);

    }

    @CrossOrigin
    @RequestMapping(value = "/{ifEvent}", method=RequestMethod.GET)
    public ResponseEntity<Event> getEventOfId(@PathVariable("idEvent") Long idEvent) {

        try {
            return new ResponseEntity<>(eventService.getEventById(idEvent),HttpStatus.OK);
        } catch (EventDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/resources/{idEvent}", method=RequestMethod.GET)
    public ResponseEntity<List<Needed>> getResourceOf(@PathVariable("idEvent") Long idEvent) {

        try {
            return new ResponseEntity<>(eventService.getEventById(idEvent).getNeededs(),HttpStatus.OK);
        } catch (EventDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @CrossOrigin
    @RequestMapping(value = "/participants/{idEvent}", method=RequestMethod.GET)
    public ResponseEntity<List<Participant>> getParticipantsOf(@PathVariable("idEvent") Long idEvent) {

        try {
            return new ResponseEntity<>(eventService.getEventById(idEvent).getParticipants(),HttpStatus.OK);
        } catch (EventDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(value = "/getAllResources", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllRessources(){

        return new ResponseEntity<>(resourceService.getAllResourcesByCategory(),HttpStatus.OK);
    }


}
