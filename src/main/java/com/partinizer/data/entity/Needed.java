package com.partinizer.data.entity;

import javax.persistence.*;

/**
 * TODO
 */
@Entity
@Table(name = "needed")
public class Needed {

    @EmbeddedId
    protected NeededKeyId id;

    @Column(name = "needed")
    protected Integer needed;

    public Integer getNeeded() {
        return needed;
    }

    public void setNeeded(Integer needed) {
        this.needed = needed;
    }

    public NeededKeyId getId() {
        return id;
    }

    public void setId(NeededKeyId id) {
        this.id = id;
    }

    public Resource getResource(){
        return this.getId().getResource();
    }
}
