package com.sangarius.oop.library.persistence.entity.impl;

import com.sangarius.oop.library.persistence.entity.Entity;
import com.sangarius.oop.library.persistence.entity.ErrorTemplates;
import com.sangarius.oop.library.persistence.exception.EntityArgumentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a library that holds books and manages user activities.
 */
public class Library extends Entity {

    private String name;
    private String address;
    private String email;
    private List<Book> books;
    private List<User> users;
    private List<Loan> loans;

    /**
     * Constructs a new Library with the specified details.
     *
     * @param id      The ID of the library.
     * @param name    The name of the library.
     * @param address The address of the library.
     * @param email   The email of the library.
     */
    public Library(UUID id, String name, String address, String email) {
        super(id);
        this.name = name;
        this.address = address;
        this.email = email;
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.loans = new ArrayList<>();
        validateLibrary();
    }

    // Getters and setters for class fields

    /**
     * Gets the name of the library.
     *
     * @return The name of the library.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the library.
     *
     * @param name The new name of the library.
     */
    public void setName(String name) {
        this.name = name;
        validateName();
    }

    /**
     * Gets the address of the library.
     *
     * @return The address of the library.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the library.
     *
     * @param address The new address of the library.
     */
    public void setAddress(String address) {
        this.address = address;
        validateAddress();
    }

    /**
     * Gets the email of the library.
     *
     * @return The email of the library.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the library.
     *
     * @param email The new email of the library.
     */
    public void setEmail(String email) {
        this.email = email;
        validateEmail();
    }

    /**
     * Gets the list of books in the library.
     *
     * @return The list of books in the library.
     */
    public List<Book> getBooks() {
        return books;
    }

    /**
     * Gets the list of users registered in the library.
     *
     * @return The list of users registered in the library.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Gets the list of loans in the library.
     *
     * @return The list of loans in the library.
     */
    public List<Loan> getLoans() {
        return loans;
    }

    /**
     * Validates the library entity and populates errors list if validation fails.
     */
    private void validateLibrary() {
        validateName();
        validateAddress();
        validateEmail();

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }
    }

    private void validateName() {
        final String templateName = "Library Name";

        if (name == null || name.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (name == null || name.length() > 255) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 255));
        }
    }

    private void validateAddress() {
        final String templateName = "Library Address";

        if (address == null || address.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (address == null || address.length() > 255) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 255));
        }
    }

    private void validateEmail() {
        final String templateName = "Library Email";

        if (email == null || email.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (email == null || email.length() > 255) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 255));
        }
    }

    /**
     * Compares this library to another object for equality.
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
        Library library = (Library) o;
        return Objects.equals(id, library.id);
    }

    /**
     * Computes a hash code for this library.
     *
     * @return A hash code value for this library.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of the library.
     *
     * @return A string representation of the library.
     */
    @Override
    public String toString() {
        return "Library{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", email='" + email + '\'' +
            ", books=" + books +
            ", users=" + users +
            ", loans=" + loans +
            '}';
    }
}