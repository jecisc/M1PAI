package com.partinizer.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by Cyril on 04/06/2016.
 */
@Entity
@Table(name = "participate")
@IdClass(ParticipantKeyId.class)
public class Participant {



    @OneToOne
    @JoinColumn(name="appliuser",insertable = false,updatable = false)
    protected User user;

    @Id
    @Column(name="appliuser")
    protected long idUser;

    @Id
    @Column(name="event")
    protected long idEvent;

    @Column(name = "accepted")
    protected Boolean accepted;


    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public long getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(long idEvent) {
        this.idEvent = idEvent;
    }

    public long getIdUser() {
        return idUser;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }
}
