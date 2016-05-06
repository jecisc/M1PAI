package com.partinizer.business.exceptions;

/**
 * Created by Cyril on 06/05/2016.
 */
public class WrongPasswordException extends WrongInformationException {

    protected final static String MESSAGE = "Mot de passe invalide.";

    @Override
    public String getFrontMessage() {
        return WrongPasswordException.MESSAGE;
    }
}
