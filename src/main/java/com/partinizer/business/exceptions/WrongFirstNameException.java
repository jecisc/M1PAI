package com.partinizer.business.exceptions;

/**
 * Created by Cyril on 06/05/2016.
 */
public class WrongFirstNameException extends WrongInformationException {

    protected final static String MESSAGE = "Prenom invalide.";

    @Override
    public String getFrontMessage() {
        return WrongFirstNameException.MESSAGE;
    }
}
