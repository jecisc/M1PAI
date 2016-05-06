package com.partinizer.business.exceptions;

/**
 * Created by Cyril on 06/05/2016.
 */
public class WrongMailIsNotFreeException extends WrongInformationException {

    protected final static String MESSAGE = "Email déjà pris.";

    @Override
    public String getFrontMessage() {
        return WrongMailIsNotFreeException.MESSAGE;
    }
}
