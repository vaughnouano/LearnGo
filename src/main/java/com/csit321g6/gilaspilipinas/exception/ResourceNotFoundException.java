package com.csit321g6.gilaspilipinas.exception;

/** Thrown when a record with the requested id does not exist -> HTTP 404. */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
