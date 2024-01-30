package com.sangarius.oop.library.persistence.repository.impl.json;

import java.nio.file.Path;

/**
 * Factory for Path objects for DAO.
 */
public enum JsonPathFactory {

    /**
     * Path to the users file.
     */
    USERS("users.json"),

    /**
     * Path to the loans file.
     */
    LOANS("loans.json"),

    /**
     * Path to the books file.
     */
    BOOKS("books.json"),

    /**
     * Path to the reviews file.
     */
    REVIEWS("reviews.json"),

    /**
     * Path to the libraries file.
     */
    LIBRARIES("libraries.json"),

    /**
     * Path to the categories file.
     */
    CATEGORIES("categories.json");

    private static final String DATA_DIRECTORY = "data";
    private final String fileName;

    /**
     * Constructor for enum elements.
     *
     * @param fileName The name of the file.
     */
    JsonPathFactory(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Get the path to the file.
     *
     * @return The path to the file.
     */
    public Path getPath() {
        return Path.of(DATA_DIRECTORY, this.fileName);
    }
}