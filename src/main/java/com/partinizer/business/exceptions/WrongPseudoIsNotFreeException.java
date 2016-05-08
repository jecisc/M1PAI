package com.partinizer.business.exceptions;

/**
 * I am an exception raised when a user have a pseudo that already exist in the database.
 * I am raised for example when we try to create a new user.
 */
public class WrongPseudoIsNotFreeException extends WrongInformationException {

    protected final static String MESSAGE = "Pseudo déjà pris.";

    @Override
    public String getFrontMessage() {
        return WrongPseudoIsNotFreeException.MESSAGE;
    }
}
