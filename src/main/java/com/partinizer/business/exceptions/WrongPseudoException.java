package com.partinizer.business.exceptions;

/**
 * Created by Cyril on 06/05/2016.
 */
public class WrongPseudoException extends WrongInformationException {

    protected final static String MESSAGE = "Pseudo invalide.";

    @Override
    public String getFrontMessage() {
        return WrongPseudoException.MESSAGE;
    }
}
