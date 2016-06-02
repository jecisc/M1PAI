package com.partinizer.data.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.util.*;

/**
 * TODO
 */
@Entity
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "idevent")
    @GenericGenerator(name = "UserIdGenerator", strategy = "sequence",
            parameters = {@Parameter(name = "sequence", value = "event_id_seq")})
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserIdGenerator")
    protected Long id;

    @Column(name = "name")
    protected String name;

    @OneToOne
    @JoinColumn(name = "iduser")
    protected User creator;

    @Column(name = "datebegin")
    protected Date dateBeginning;

    @Column(name = "dateend")
    protected Date dateEnd;

    @Column(name = "description")
    protected String description;

    @Column(name = "place")
    protected String localisation;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinColumn(name="event")
    protected List<Needed> neededs;

    public List<Needed> getNeededs() {
        return neededs;
    }

    public void setNeededs(List<Needed> neededs) {
        this.neededs = neededs;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateBeginning() {
        return dateBeginning;
    }

    public void setDateBeginning(Date dateBeginning) {
        this.dateBeginning = dateBeginning;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
}
