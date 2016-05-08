package com.partinizer.business.exceptions;

/**
 * I am an exception raised when an email is not valid.
 */
public class WrongMailException extends WrongInformationException {

    protected final static String MESSAGE = "Email invalide.";

    @Override
    public String getFrontMessage() {
        return WrongMailException.MESSAGE;
    }
}
