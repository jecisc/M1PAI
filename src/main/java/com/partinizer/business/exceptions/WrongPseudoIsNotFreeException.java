package com.partinizer.business.exceptions;

/**
 * Created by Cyril on 06/05/2016.
 */
public class WrongPseudoIsNotFreeException extends WrongInformationException {

    protected final static String MESSAGE = "Pseudo déjà pris.";

    @Override
    public String getFrontMessage() {
        return WrongPseudoIsNotFreeException.MESSAGE;
    }
}
