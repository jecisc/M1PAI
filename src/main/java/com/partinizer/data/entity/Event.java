package com.partinizer.data.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

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
    protected long id;

    @Column(name = "name")
    protected String name;

    //TODO remove this and reference the user instead of keeping his email
    @Column(name = "emailcreator")
    protected String emailCreator;

    //TODO remove dateBeginning and hour beginning to use a localDateTime instead
    @Column(name = "dateBegin")
    protected Date dateBeginning;

    @Column(name = "hourbegin")
    protected LocalTime hourBeginning;

    //TODO remove dateBeginning and hour beginning to use a localDateTime instead
    @Column(name = "dateend")
    protected Date dateEnd;

    @Column(name = "hourend")
    protected LocalTime hourEnd;

    @Column(name = "description")
    protected String description;

    @Column(name = "place")
    protected String localisation;


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

    public String getEmailCreator() {
        return emailCreator;
    }

    public void setEmailCreator(String emailCreator) {
        this.emailCreator = emailCreator;
    }

    public Date getDateBeginning() {
        return dateBeginning;
    }

    public void setDateBeginning(Date dateBeginning) {
        this.dateBeginning = dateBeginning;
    }

    public LocalTime getHourBeginning() {
        return hourBeginning;
    }

    public void setHourBeginning(LocalTime hourBeginning) {
        this.hourBeginning = hourBeginning;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public LocalTime getHourEnd() {
        return hourEnd;
    }

    public void setHourEnd(LocalTime hourEnd) {
        this.hourEnd = hourEnd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }
}
