package com.partinizer.business.manager;

import com.partinizer.business.exceptions.*;
import com.partinizer.data.entity.*;
import com.partinizer.data.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<Event> getEventsByParticipantId(User user) throws EventDoesNotExistException {
        List<Event> events=this.eventRepository.getEventsByParticipantId(user.getId());
        if(events==null){
            EventDoesNotExistException exception = new EventDoesNotExistException();
            exception.setId(user.getId());
            throw exception;
        }

        return events;
    }

    public List<Event> getEventsCreated(User user) throws EventDoesNotExistException {
        List<Event> events=this.eventRepository.getEventsCreated(user.getId());
        if(events==null){
            EventDoesNotExistException exception = new EventDoesNotExistException();
            exception.setId(user.getId());
            throw exception;
        }

        return events;
    }

    public List<Event> getEventsInvitation(User user) throws EventDoesNotExistException {
        List<Event> events=this.eventRepository.getEventsInvitation(user.getId());
        if(events==null){
            EventDoesNotExistException exception = new EventDoesNotExistException();
            exception.setId(user.getId());
            throw exception;
        }

        return events;
    }

    public List<Event> getAllEvents() {
        return this.eventRepository.findAll();
    }

    //TODO: improve function
    public boolean createEvent(Event event) throws WrongNameException, WrongEventDescriptionException {

        checkName(event.getName());
        checkDescription(event.getDescription());


        Event event_= new Event();
        event_.setCreator(event.getCreator());
        event_.setDescription(event.getDescription());
        event_.setName(event.getName());
        event_.setDateBeginning(event.getDateBeginning());
        event_.setDateEnd(event.getDateEnd());
        event_.setLocalisation(event.getLocalisation());

        event_= eventRepository.save(event_);

        if(event_!=null) {
            for (Needed needed : event.getNeededs()) {
                needed.setIdEvent(event_.getId());
            }

            for (Participant participant : event.getParticipants()) {
                participant.setIdEvent(event_.getId());
            }

            event_.setNeededs(event.getNeededs());
            event_.setParticipants(event.getParticipants());
            eventRepository.save(event_);

        }
        return event != null;
    }

    /**
     * I check that the event name is valid.
     *
     * @param name The name of the event.
     * @throws WrongNameException raised if the information is not valid.
     */
    public void checkName(String name) throws WrongNameException {
        if (name == null || name.length() < 3 || name.length() > 40) {
            throw new WrongNameException();
        }
    }

    /**
     * I check that the event description is valid.
     *
     * @param description The description of the event.
     * @throws WrongEventDescriptionException raised if the information is not valid.
     */
    public void checkDescription(String description) throws WrongEventDescriptionException {
        if (description == null ||  description.length() > 30) {
            throw new WrongEventDescriptionException();
        }
    }

    /**
     * I check that the event description is valid.
     *
     * @param localisation The description of the event.
     * @throws WrongEventDescriptionException raised if the information is not valid.
     */
    public void checkLocalisation(String localisation) throws WrongEventDescriptionException {
        if (localisation == null ||  localisation.length() > 30) {
            throw new WrongEventDescriptionException();
        }
    }


}
