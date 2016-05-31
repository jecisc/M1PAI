package com.partinizer.business.manager;

import com.partinizer.business.exceptions.EventDoesNotExistException;
import com.partinizer.data.entity.Event;
import com.partinizer.data.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * TODO
 */
@Component
public class EventManager {

    protected EventRepository eventRepository;

    @Autowired
    public EventManager(EventRepository userRepository) {
        this.eventRepository = userRepository;
    }

    /**
     * I return the event matching an id.
     *
     * @param id The id of the event.
     * @return The event matching the id.
     * @throws EventDoesNotExistException raised of there is no event with this id.
     */
    public Event getEventById(Long id) throws EventDoesNotExistException {
        Event event = this.eventRepository.findOne(id);
        if (event == null) {
            EventDoesNotExistException exception = new EventDoesNotExistException();
            exception.setId(id);
            throw exception;
        }
        return event;
    }
}
