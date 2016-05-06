package com.partinizer.business.exceptions;

/**
 * Created by Cyril on 06/05/2016.
 */
public class WrongActivityException extends WrongInformationException {

    protected final static String MESSAGE = "Utilisateur déjà actif.";

    @Override
    public String getFrontMessage() {
        return WrongActivityException.MESSAGE;
    }
}
