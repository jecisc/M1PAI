package com.partinizer.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * Created by Cyril on 04/06/2016.
 */
@Embeddable
public class ParticipantKeyId implements Serializable{

    @OneToOne
    @JoinColumn(name = "event")
    @JsonIgnore
    protected Event event;

    @OneToOne
    @JoinColumn(name = "appliuser")
    protected User user;

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
