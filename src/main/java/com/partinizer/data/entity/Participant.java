package com.partinizer.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by Cyril on 04/06/2016.
 */
@Entity
@Table(name = "participate")
public class Participant {


    @EmbeddedId
    @JsonIgnore
    protected ParticipantKeyId id;

    @OneToOne
    @MapsId("idUser")
    @JoinColumn(name = "appliuser")
    protected User user;

    @Column(name = "accepted")
    protected Boolean accepted;

    @JsonIgnore
    public ParticipantKeyId getId() {
        return id;
    }

    @JsonProperty
    public void setId(ParticipantKeyId id) {
        this.id = id;
    }

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


}
