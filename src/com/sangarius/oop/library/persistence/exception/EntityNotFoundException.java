package com.sangarius.oop.library.persistence.exception;

/**
 * Exception thrown when an entity is not found.
 */
public class EntityNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code EntityNotFoundException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public EntityNotFoundException(String message) {
        super(message);
    }
}