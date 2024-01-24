package com.sangarius.oop.library.persistence.exception;

import java.util.List;

/**
 * Exception thrown for invalid entity arguments.
 */
public class EntityArgumentException extends RuntimeException {
    private final List<String> errors;

    /**
     * Constructs a new EntityArgumentException with the specified list of errors.
     *
     * @param errors The list of errors.
     */
    public EntityArgumentException(List<String> errors) {
        super("Invalid entity arguments: " + String.join(", ", errors));
        this.errors = errors;
    }

    /**
     * Gets the list of errors associated with the exception.
     *
     * @return The list of errors.
     */
    public List<String> getErrors() {
        return errors;
    }
}
