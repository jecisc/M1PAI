package com.partinizer.business.exceptions;

/**
 * Created by Cyril on 06/05/2016.
 */
public class UserDoesNotExistException extends Exception {

    protected String pseudo;

    protected String mail;

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}
