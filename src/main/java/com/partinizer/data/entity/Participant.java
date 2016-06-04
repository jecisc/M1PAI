package com.partinizer.data.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Cyril on 04/06/2016.
 */
@Entity
@Table(name = "participate")
public class Participant {


    @EmbeddedId
    protected ParticipantKeyId id;

    @Column(name = "accepted")
    protected Boolean accepted;

    public ParticipantKeyId getId() {
        return id;
    }

    public void setId(ParticipantKeyId id) {
        this.id = id;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
