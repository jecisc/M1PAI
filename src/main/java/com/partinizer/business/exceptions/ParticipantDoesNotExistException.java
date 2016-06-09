package com.partinizer.business.exceptions;

/**
 * Created by vincent on 08/06/2016.
 */
public class ParticipantDoesNotExistException extends Exception {

    public ParticipantDoesNotExistException(String message){

        super(message);
    }

    private long idEvent;

    private long idParticipant;

    public long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(long idEvent) {
        this.idEvent = idEvent;
    }

    public long getIdParticipant() {
        return idParticipant;
    }

    public void setIdParticipant(long idParticipant) {
        this.idParticipant = idParticipant;
    }
}
