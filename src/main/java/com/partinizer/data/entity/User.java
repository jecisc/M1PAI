package com.partinizer.data.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by vincent on 10/03/16.
 */

@Entity
@Table(name="appliuser")
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="iduser")
    @GenericGenerator(name="UserIdGenerator", strategy = "sequence",
            parameters = { @Parameter(name="sequence", value="user_id_seq") } )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserIdGenerator")
    private long id;

    @Column(name="name")
    private String name;

    @Column(name="firstname")
    private String firstName;

    @Column(name="passwd")
    private String password;

    @Column(name="email")
    private String mail;

    @Column(name="inscription")
    private Date registDate ;

    @Column(name="pseudo")
    private String pseudo;

    @Column(name="isactive")
    private boolean isActive;

    @Column(name="avatar")
    private String avatar;

    @ElementCollection
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="AREFRIEND")
    private List<User> friends;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistDate() {
        return registDate;
    }

    public void setRegistDate(Date registDate) {
        this.registDate = registDate;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


}
