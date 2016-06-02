package com.partinizer.business.service;

import com.partinizer.business.exceptions.EventDoesNotExistException;
import com.partinizer.business.manager.EventManager;
import com.partinizer.data.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
