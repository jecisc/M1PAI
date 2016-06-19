package com.partinizer.business.service;

import com.partinizer.business.exceptions.EventDoesNotExistException;
import com.partinizer.business.exceptions.ParticipantDoesNotExistException;
import com.partinizer.business.exceptions.WrongEventDescriptionException;
import com.partinizer.business.exceptions.WrongNameException;
import com.partinizer.business.manager.EventManager;
import com.partinizer.business.manager.NeededManager;
import com.partinizer.business.manager.ParticipantManager;
import com.partinizer.data.entity.*;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;

import java.util.*;

/**
 * TODO
 **/
@Service
public class EventService {

    protected EventManager eventManager;
    protected ParticipantManager participantManager;
    protected NeededManager neededManager;

    @Autowired
    public EventService(EventManager eventManager,ParticipantManager participantManager,NeededManager neededManager) {
        this.eventManager = eventManager;
        this.participantManager = participantManager;
        this.neededManager = neededManager;
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

    @Transactional
    public boolean deleteEvent(long idEvent,User user) throws EventDoesNotExistException {

        Event event=eventManager.getEventById(idEvent);
        if(event!=null){
            for(Participant participant:event.getParticipants()){
                participantManager.delete(participant);
            }
            for(Needed needed:event.getNeededs()){
                neededManager.delete(needed);
            }

        }
        return eventManager.deleteEvent(idEvent,user);
    }
}
