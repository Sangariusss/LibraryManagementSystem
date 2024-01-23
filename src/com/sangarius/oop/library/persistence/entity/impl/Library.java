package com.sangarius.oop.library.persistence.entity.impl;

import com.sangarius.oop.library.persistence.entity.impl.Loan;
import com.sangarius.oop.library.persistence.entity.impl.User;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a library that holds books and manages user activities.
 */
public class Library {
    private int id;
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
    public Library(int id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.loans = new ArrayList<>();
    }

    // Getters and setters for class fields

    /**
     * Gets the ID of the library.
     *
     * @return The ID of the library.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the library.
     *
     * @param id The new ID of the library.
     */
    public void setId(int id) {
        this.id = id;
    }

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
}
