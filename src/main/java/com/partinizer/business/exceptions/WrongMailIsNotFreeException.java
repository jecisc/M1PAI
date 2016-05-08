package com.partinizer.business.exceptions;

/**
 * I am an exception raised when a user have an email that already exist in the database.
 * I am raised for example when we try to create a new user.
 */
public class WrongMailIsNotFreeException extends WrongInformationException {

    protected final static String MESSAGE = "Email déjà pris.";

    @Override
    public String getFrontMessage() {
        return WrongMailIsNotFreeException.MESSAGE;
    }
}
