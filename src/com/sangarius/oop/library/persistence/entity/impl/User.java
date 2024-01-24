package com.sangarius.oop.library.persistence.entity.impl;

import com.sangarius.oop.library.persistence.entity.Entity;
import com.sangarius.oop.library.persistence.entity.ErrorTemplates;
import com.sangarius.oop.library.persistence.exception.EntityArgumentException;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a user in the library system.
 */
public class User extends Entity {

    private String email;
    private String name;

    /**
     * Constructs a new User with the specified details.
     *
     * @param id    The ID of the user.
     * @param email The email of the user.
     * @param name  The name of the user.
     */
    public User(UUID id, String email, String name) {
        super(id);
        this.email = email;
        this.name = name;
        validate();
    }

    // Getters and setters for class fields

    /**
     * Gets the email of the user.
     *
     * @return The email of the user.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The new email of the user.
     */
    public void setEmail(String email) {
        this.email = email;
        validateEmail();
    }

    /**
     * Gets the name of the user.
     *
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the user.
     *
     * @param name The new name of the user.
     */
    public void setName(String name) {
        this.name = name;
        validateName();
    }

    /**
     * Validates the user entity and populates errors list if validation fails.
     */
    private void validate() {
        validateEmail();
        validateName();

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }
    }

    private void validateEmail() {
        if (email == null || email.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted("Email"));
        }
    }

    private void validateName() {
        if (name == null || name.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted("Name"));
        }
        if (name.length() > 255) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted("Name", 255));
        }
    }

    /**
     * Compares this user to another object for equality.
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
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    /**
     * Computes a hash code for this user.
     *
     * @return A hash code value for this user.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of the user.
     *
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", email='" + email + '\'' +
            ", name='" + name + '\'' +
            '}';
    }
}