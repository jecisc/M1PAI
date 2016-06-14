package com.partinizer.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * TODO
 */
@Entity
@Table(name = "needed")
@IdClass(NeededKeyId.class)
public class Needed {



    @Id
    @Column(name="ressource")
    protected long idResource;

    @Id
    @Column(name="event")
    protected long idEvent;

    @OneToOne
    @JoinColumn(name="ressource",insertable = false,updatable = false)
    protected Resource resource;

    @Column(name = "needed")
    protected Integer needed;

    @Column(name="isfacultative")
    protected boolean isFacultative;

    public Integer getNeeded() {
        return needed;
    }

    public void setNeeded(Integer needed) {
        this.needed = needed;
    }


    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    /*public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }*/

    public boolean isFacultative() {
        return isFacultative;
    }

    public void setFacultative(boolean facultative) {
        isFacultative = facultative;
    }

    /*public NeededKeyId getId() {
        return id;
    }

    public void setId(NeededKeyId id) {
        this.id = id;
    }*/

    public long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(long idEvent) {
        this.idEvent = idEvent;
    }

    public long getIdResource() {
        return idResource;
    }

    public void setIdResource(long idResource) {
        this.idResource = idResource;
    }
}
