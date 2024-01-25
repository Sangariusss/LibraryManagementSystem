package com.sangarius.oop.library.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sangarius.oop.library.persistence.entity.impl.User;
import com.sangarius.oop.library.persistence.repository.contracts.UserRepository;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * JSON implementation of the User repository.
 */
public class UserJsonRepositoryImpl extends GenericJsonRepository<User> implements UserRepository {

    /**
     * Constructs a new instance of {@code UserJsonRepositoryImpl}.
     *
     * @param gson The Gson instance for JSON serialization/deserialization.
     */
    public UserJsonRepositoryImpl(Gson gson) {
        super(gson, JsonPathFactory.USERS.getPath(), TypeToken
            .getParameterized(Set.class, User.class)
            .getType());
    }

    /**
     * Finds a user by email.
     *
     * @param email The email to search for.
     * @return An optional containing the user with the specified email, or empty if not found.
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return entities.stream()
            .filter(user -> email.equals(user.getEmail()))
            .findFirst();
    }

    /**
     * Finds users by name.
     *
     * @param name The name to search for.
     * @return A set of users with the specified name.
     */
    @Override
    public Set<User> findAllByName(String name) {
        return entities.stream()
            .filter(user -> name.equals(user.getName()))
            .collect(Collectors.toSet());
    }
}