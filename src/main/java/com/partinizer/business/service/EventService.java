package com.partinizer.business.service;

import com.partinizer.business.exceptions.EventDoesNotExistException;
import com.partinizer.business.exceptions.WrongEventDescriptionException;
import com.partinizer.business.exceptions.WrongNameException;
import com.partinizer.business.manager.EventManager;
import com.partinizer.data.entity.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.*;

/**
 * TODO
 **/
@Service
public class EventService {

    protected EventManager eventManager;

    @Autowired
    public EventService(EventManager eventManager) {
        this.eventManager = eventManager;
    }

    /**
     * I return the event matching an id.
     *
     * @param id The id of the event.
     * @return The event matching the id.
     * @throws EventDoesNotExistException raised of there is no user wth this pseudo.
     */
    public Event getEventById(Long id) throws EventDoesNotExistException {
        return eventManager.getEventById(id);
    }

    public List<Event> getMyParticipations(User user) throws EventDoesNotExistException {
        return eventManager.getEventsByParticipantId(user);
    }

    public List<Event> getEventsInvitation(User user) throws EventDoesNotExistException {
        return eventManager.getEventsInvitation(user);
    }

    public List<Event> getEventsCreated(User user) throws EventDoesNotExistException {
        return eventManager.getEventsCreated(user);
    }

    public List<Event> getAllEvents() {
        return this.eventManager.getAllEvents();
    }

    public boolean createEvent(Event event) throws WrongNameException, WrongEventDescriptionException {


        return eventManager.createEvent(event);

    }
}
