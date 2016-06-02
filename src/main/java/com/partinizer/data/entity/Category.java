package com.partinizer.data.entity;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Created by vincent on 29/05/2016.
 */
@Entity
@Table(name="category")
public class Category {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="idcategory")
    @GenericGenerator(name="CategoryIdGenerator", strategy = "sequence",
            parameters = { @org.hibernate.annotations.Parameter(name="sequence", value="category_id_seq") } )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CategoryIdGenerator")
    private long idCategory;

    @Column(name="designation")
    private String designation;

    @Column(name = "icon")
    byte[] icon;

    @OneToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="category")
    protected List<Resource> resources;

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(long idCategory) {
        this.idCategory = idCategory;
    }

    public byte[] getIcon() {
        return icon;
    }

    public void setIcon(byte[] icon) {
        this.icon = icon;
    }
}
