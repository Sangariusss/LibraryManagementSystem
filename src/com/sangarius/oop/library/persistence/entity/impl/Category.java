package com.sangarius.oop.library.persistence.entity.impl;

/**
 * Represents a category of books in the library.
 */
public class Category {
    private String name;

    /**
     * Constructs a new Category with the specified name.
     *
     * @param name The name of the category.
     */
    public Category(String name) {
        this.name = name;
    }

    // Getter and setter for the name field

    /**
     * Gets the name of the category.
     *
     * @return The name of the category.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the category.
     *
     * @param name The new name of the category.
     */
    public void setName(String name) {
        this.name = name;
    }
}