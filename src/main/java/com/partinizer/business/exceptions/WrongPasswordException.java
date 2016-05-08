package com.partinizer.business.exceptions;

/**
 * I am an exception raised when a password is not valid.
 */
public class WrongPasswordException extends WrongInformationException {

    protected final static String MESSAGE = "Mot de passe invalide.";

    @Override
    public String getFrontMessage() {
        return WrongPasswordException.MESSAGE;
    }
}
