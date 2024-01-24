package com.sangarius.oop.library.persistence.repository.contracts;

import com.sangarius.oop.library.persistence.entity.impl.User;
import com.sangarius.oop.library.persistence.repository.Repository;

import java.util.Optional;
import java.util.Set;

/**
 * Repository interface for managing users.
 */
public interface UserRepository extends Repository<User> {

    /**
     * Finds a user by email.
     *
     * @param email The email to search for.
     * @return An optional containing the user with the specified email, or empty if not found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Finds users by name.
     *
     * @param name The name to search for.
     * @return A set of users with the specified name.
     */
    Set<User> findAllByName(String name);
}