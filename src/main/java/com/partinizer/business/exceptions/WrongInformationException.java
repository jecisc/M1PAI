package com.partinizer.business.exceptions;

/**
 * I am a meta exception to describe an exception that occur when a user have a wrong information.
 * I should be able to return a message to display on the front.
 */
public abstract class WrongInformationException extends Exception {

    /**
     * I return a message to display on the front of the application.
     * @return a message explaining the problem that occurred.
     */
    public abstract String getFrontMessage();

}
