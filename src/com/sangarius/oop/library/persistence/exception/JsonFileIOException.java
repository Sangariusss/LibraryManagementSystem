package com.sangarius.oop.library.persistence.exception;

/**
 * Exception representing errors related to JSON file operations.
 */
public class JsonFileIOException extends RuntimeException {

    /**
     * Constructs a new {@code JsonFileIOException} with the specified detail message.
     *
     * @param message the detail message.
     */
    public JsonFileIOException(String message) {
        super(message);
    }
}