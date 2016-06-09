package com.partinizer.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * Created by Cyril on 02/06/2016.
 */
@Embeddable
public class NeededKeyId implements Serializable{

    @OneToOne
    @JoinColumn(name = "event")
    @JsonIgnore
    protected Event event;

    @OneToOne
    @JoinColumn(name = "ressource")
    protected Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }
}
