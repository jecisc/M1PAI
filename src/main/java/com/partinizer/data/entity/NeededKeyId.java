package com.partinizer.data.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

/**
 * Created by Cyril on 02/06/2016.
 */
@Embeddable
public class NeededKeyId implements Serializable{

    @Column(name = "event")
    protected Long event;

    @OneToOne
    @JoinColumn(name = "ressource")
    protected Resource resource;

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Long getEvent() {
        return event;
    }

    public void setEvent(Long event) {
        this.event = event;
    }
}
