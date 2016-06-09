package com.partinizer.front.controller;

import com.partinizer.business.exceptions.EventDoesNotExistException;
import com.partinizer.business.exceptions.ParticipantDoesNotExistException;
import com.partinizer.business.exceptions.UserDoesNotExistException;
import com.partinizer.business.service.EventService;
import com.partinizer.business.service.ParticipantService;
import com.partinizer.business.service.ResourceService;
import com.partinizer.business.service.UserService;
import com.partinizer.data.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    protected UserService userService;
    protected ParticipantService participantService;

    @Autowired
    public EventRestController(EventService eventService,ResourceService resourceService,UserService userService,ParticipantService participantService) {
        this.eventService = eventService;
        this.resourceService=resourceService;
        this.userService=userService;
        this.participantService=participantService;
    }

    @CrossOrigin
    @RequestMapping(value = "/events", method=RequestMethod.GET)
    public ResponseEntity<List<Event>> getAllEvents() {

        return new ResponseEntity<>(eventService.getAllEvents(),HttpStatus.OK);

    }

    @CrossOrigin
    @RequestMapping(value = "/{idEvent}", method=RequestMethod.GET)
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

    @RequestMapping(value = "/myParticipations", method=RequestMethod.GET)
    public ResponseEntity<List<Event>> getMyParticipations(Authentication authentication) {

        try {
            return new ResponseEntity<>(eventService.getMyParticipations(getUserFromAuthentication(authentication)),HttpStatus.OK);
        } catch (EventDoesNotExistException | UserDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/myEventsInvitation", method=RequestMethod.GET)
    public ResponseEntity<List<Event>> getEventsInvitation(Authentication authentication) {

        try {
            return new ResponseEntity<>(eventService.getEventsInvitation(getUserFromAuthentication(authentication)),HttpStatus.OK);
        } catch (EventDoesNotExistException | UserDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/accept/{idEvent}", method=RequestMethod.GET)
    public ResponseEntity<String> accept(Authentication authentication,@PathVariable("idEvent") Long idEvent) {

        try {
            User user=getUserFromAuthentication(authentication);

            participantService.acceptEvent(idEvent,user.getId());
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (ParticipantDoesNotExistException | UserDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/deny/{idEvent}", method=RequestMethod.GET)
    public ResponseEntity<String> deny(Authentication authentication,@PathVariable("idEvent") Long idEvent) {

        try {
            User user=getUserFromAuthentication(authentication);

            participantService.denyEvent(idEvent,user.getId());
            return new ResponseEntity<>(HttpStatus.OK);

        } catch (ParticipantDoesNotExistException | UserDoesNotExistException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }


    @RequestMapping(value = "/getAllResources", method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getAllRessources(){

        return new ResponseEntity<>(resourceService.getAllResourcesByCategory(),HttpStatus.OK);
    }

    public User getUserFromAuthentication(Authentication authentication) throws UserDoesNotExistException {
        return userService.getUserByPseudo(((UserDetails) authentication.getPrincipal()).getUsername());
    }
}
