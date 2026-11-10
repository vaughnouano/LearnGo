package com.csit321g6.gilaspilipinas.exception;

/** Thrown when a unique value (e.g. course code) already exists -> HTTP 409. */
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
