package com.partinizer.data.entity;

import javax.persistence.*;

/**
 * Created by vincent on 29/05/2016.
 */
@Entity
@Table(name="ressource")
public class Resource {

    @Id
    @Column(name = "idressource")
    long idRessource;

    @Column(name = "name")
    String name;

    @Column(name = "icon")
    byte[] icon;

    @Column(name = "category")
    long idCategory;

    public long getIdRessource() {
        return idRessource;
    }

    public void setIdRessource(int idRessource) {
        this.idRessource = idRessource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
