package com.partinizer.business.exceptions;

/**
 * I am an exception raised when an event could not be found in the database.
 */
public class EventDoesNotExistException extends Exception {

    protected Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
