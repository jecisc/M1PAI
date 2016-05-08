package com.partinizer.business.exceptions;

/**
 * I am an exception raised when a last name is not valid.
 */
public class WrongNameException extends WrongInformationException {

    protected final static String MESSAGE = "Nom invalide.";

    @Override
    public String getFrontMessage() {
        return WrongNameException.MESSAGE;
    }
}
