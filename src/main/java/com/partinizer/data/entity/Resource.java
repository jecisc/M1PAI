package com.partinizer.data.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

/**
 * TODO
 */
@Entity
@Table(name = "ressource")
public class Resource {

    @Id
    @Column(name = "idressource")
    @GenericGenerator(name = "UserIdGenerator", strategy = "sequence",
            parameters = {@Parameter(name = "sequence", value = "ressource_id_seq")})
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserIdGenerator")
    protected long id;

    @Column(name = "name")
    protected String name;

    @Column(name = "icon")
    protected String icon;

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

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
