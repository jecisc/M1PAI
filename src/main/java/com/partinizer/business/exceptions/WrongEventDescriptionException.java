package com.partinizer.business.exceptions;

/**
 * Created by vincent on 09/06/2016.
 */
public class WrongEventDescriptionException extends WrongInformationException {

    protected final static String MESSAGE = "Description évenement invalide.";

    @Override
    public String getFrontMessage() {
        return WrongEventDescriptionException.MESSAGE;
    }
}