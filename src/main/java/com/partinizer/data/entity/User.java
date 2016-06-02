package com.partinizer.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by vincent on 10/03/16.
 */

@Entity
@Table(name="appliuser")
@NamedEntityGraphs({
        @NamedEntityGraph(name = "User.friends",
                attributeNodes = @NamedAttributeNode("friends")),
        @NamedEntityGraph(name = "User.friendRequest",
                attributeNodes = @NamedAttributeNode("friendRequest"))
})
public class User {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="iduser")
    @GenericGenerator(name="UserIdGenerator", strategy = "sequence",
            parameters = { @Parameter(name="sequence", value="user_id_seq") } )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UserIdGenerator")
    private long id;

    @Column(name="lastname")
    private String name;

    @Column(name="firstname")
    private String firstName;

    @JsonIgnore
    @Column(name="passwd")
    private String password;

    @Column(name="email")
    private String mail;

    @Column(name="inscription")
    private Date registDate ;

    @Column(name="pseudo")
    private String pseudo;

    @Column(name="isactive")
    protected Boolean isActive;

    @Column(name="avatar")
    private String avatar;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable(name="arefriend",joinColumns = @JoinColumn(name="friend1"),
            inverseJoinColumns = @JoinColumn(name = "friend2"))
    private List<User> friends;

    @OneToMany(fetch=FetchType.LAZY)
    @JoinTable(name="askfriend",joinColumns = @JoinColumn(name="friend"),
            inverseJoinColumns = @JoinColumn(name = "asker"))
    private List<User> friendRequest;

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

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @JsonProperty
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

    /**
     * I return the state of the user activity. I use a defensive strategy to initialize it.
     */
    public Boolean isActive() {
        if(this.isActive == null){
            this.isActive = false;
        }
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

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public List<User> getFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(List<User> friendRequest) {
        this.friendRequest = friendRequest;
    }
}
