package com.partinizer.business.exceptions;

/**
 * I am an exception raised when a user could not be found in the database.
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
