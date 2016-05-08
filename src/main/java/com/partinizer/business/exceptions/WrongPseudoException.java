package com.partinizer.business.exceptions;

/**
 * I am an exception raised when a pseudo is not valid.
 */
public class WrongPseudoException extends WrongInformationException {

    protected final static String MESSAGE = "Pseudo invalide.";

    @Override
    public String getFrontMessage() {
        return WrongPseudoException.MESSAGE;
    }
}
