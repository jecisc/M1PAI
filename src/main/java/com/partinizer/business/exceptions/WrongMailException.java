package com.partinizer.business.exceptions;

/**
 * Created by Cyril on 06/05/2016.
 */
public class WrongMailException extends WrongInformationException {

    protected final static String MESSAGE = "Email invalide.";

    @Override
    public String getFrontMessage() {
        return WrongMailException.MESSAGE;
    }
}
