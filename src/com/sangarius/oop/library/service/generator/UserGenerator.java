package com.sangarius.oop.library.service.generator;

import com.sangarius.oop.library.persistence.entity.impl.User;
import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Utility class for generating sets of users.
 */
public class UserGenerator {

    /**
     * Generates a set of users.
     *
     * @param count The number of users to generate.
     * @return A set of generated users.
     */
    public static Set<User> generateUsers(int count) {
        Set<User> users = new HashSet<>();
        Faker faker = new Faker();

        for (int i = 0; i < count; i++) {
            UUID userId = UUID.randomUUID();
            String email = faker.internet().emailAddress();
            String name = faker.name().fullName();

            User user = new User(userId, email, name);
            users.add(user);
        }

        return users;
    }
}
