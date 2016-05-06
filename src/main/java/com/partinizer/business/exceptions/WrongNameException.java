package com.partinizer.business.exceptions;

/**
 * Created by Cyril on 06/05/2016.
 */
public class WrongNameException extends WrongInformationException {

    protected final static String MESSAGE = "Nom invalide.";

    @Override
    public String getFrontMessage() {
        return WrongNameException.MESSAGE;
    }
}
