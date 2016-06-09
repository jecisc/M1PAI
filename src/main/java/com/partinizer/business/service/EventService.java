package com.partinizer.business.service;

import com.partinizer.business.exceptions.EventDoesNotExistException;
import com.partinizer.business.manager.EventManager;
import com.partinizer.data.entity.Event;
import com.partinizer.data.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;

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

    public List<Event> getAllEvents() {
        return this.eventManager.getAllEvents();
    }
}
