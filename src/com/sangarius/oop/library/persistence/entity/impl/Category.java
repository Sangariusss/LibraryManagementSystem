package com.sangarius.oop.library.persistence.entity.impl;

import com.sangarius.oop.library.persistence.entity.Entity;
import com.sangarius.oop.library.persistence.entity.ErrorTemplates;
import com.sangarius.oop.library.persistence.exception.EntityArgumentException;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a category of books in the library.
 */
public class Category extends Entity {
    private String name;

    /**
     * Constructs a new Category with the specified ID and name.
     *
     * @param id   The unique identifier of the category.
     * @param name The name of the category.
     */
    public Category(UUID id, String name) {
        super(id);
        this.errors = new ArrayList<>();
        validateName(name);
        if (!errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }
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
        this.errors = new ArrayList<>();
        validateName(name);
        if (!errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }
        this.name = name;
    }

    /**
     * Validates the name of the category and adds errors to the list.
     *
     * @param name The name to be validated.
     */
    private void validateName(String name) {
        final String templateName = "Category";

        if (name == null || name.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (name != null && name.length() > 255) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 255));
        }
    }

    /**
     * Compares this category to another object for equality.
     *
     * @param o The object to compare to.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Category category = (Category) o;
        return Objects.equals(id, category.id);
    }

    /**
     * Computes a hash code for this category.
     *
     * @return A hash code value for this category.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of the category.
     *
     * @return A string representation of the category.
     */
    @Override
    public String toString() {
        return "Category{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
    }
}