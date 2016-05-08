package com.partinizer.business.exceptions;

/**
 * I am an exception raised when a user is in the wrong state.
 */
public class WrongActivityException extends WrongInformationException {

    protected final static String MESSAGE = "Utilisateur déjà actif.";

    @Override
    public String getFrontMessage() {
        return WrongActivityException.MESSAGE;
    }
}
