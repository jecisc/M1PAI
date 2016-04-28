package com.partinizer.data.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by vincent on 09/04/16.
 */
@Entity
@Table(name="askfriend")
public class AskFriend implements Serializable {

    @Id
    @Column(name="asker")
    private long asker;


    @Column(name="friend")
    private long friend;

    @OneToOne
    @JoinColumn(name="asker", insertable = false,updatable = false)
    private User userAsker;


    public long getAsker() {
        return asker;
    }

    public void setAsker(long asker) {
        this.asker = asker;
    }


    public User getUserAsker() {
        return userAsker;
    }

    public void setUserAsker(User userAsker) {
        this.userAsker = userAsker;
    }
}
