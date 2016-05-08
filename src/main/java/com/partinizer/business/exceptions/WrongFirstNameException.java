package com.partinizer.business.exceptions;

/**
 * I am an exception raised when a first name is not valid.
 */
public class WrongFirstNameException extends WrongInformationException {

    protected final static String MESSAGE = "Prenom invalide.";

    @Override
    public String getFrontMessage() {
        return WrongFirstNameException.MESSAGE;
    }
}
