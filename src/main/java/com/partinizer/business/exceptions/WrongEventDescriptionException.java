package com.partinizer.business.exceptions;

/**
 * I am an exception raised when an event has a wrong description.
 */
public class WrongEventDescriptionException extends WrongInformationException {

    protected final static String MESSAGE = "Description évenement invalide.";

    @Override
    public String getFrontMessage() {
        return WrongEventDescriptionException.MESSAGE;
    }
}