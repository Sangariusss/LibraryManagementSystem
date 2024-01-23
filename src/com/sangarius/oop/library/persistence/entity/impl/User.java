package com.sangarius.oop.library.persistence.entity.impl;

/**
 * Represents a user in the library system.
 */
public class User {
    private int id;
    private String email;
    private String name;

    /**
     * Constructs a new User with the specified details.
     *
     * @param id    The ID of the user.
     * @param email The email of the user.
     * @param name  The name of the user.
     */
    public User(int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    // Getters and setters for class fields

    /**
     * Gets the ID of the user.
     *
     * @return The ID of the user.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the user.
     *
     * @param id The new ID of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

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
    }

}
