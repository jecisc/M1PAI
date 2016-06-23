package com.partinizer.business.exceptions;

/**
 * I am an exception raised when a participant could not be found in the database.
 */
public class ParticipantDoesNotExistException extends Exception {

    protected Long idEvent;

    public ParticipantDoesNotExistException(String message){
        super(message);
    }

    public Long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(Long idEvent) {
        this.idEvent = idEvent;
    }

}
