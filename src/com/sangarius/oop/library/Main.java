package com.sangarius.oop.library;

import static java.lang.System.out;

import com.github.javafaker.Faker;
import com.sangarius.oop.library.persistence.entity.impl.User;
import com.sangarius.oop.library.persistence.repository.RepositoryFactory;
import com.sangarius.oop.library.persistence.repository.contracts.UserRepository;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Main {

    public static void main(String[] args) {
        Set<User> users = generateUsers(4);

        RepositoryFactory jsonRepositoryFactory = RepositoryFactory
            .getRepositoryFactory(RepositoryFactory.JSON);
        UserRepository userRepository = jsonRepositoryFactory.getUserRepository();

        int i = 0;
        for (User user : users) {
            userRepository.add(user);
            if (i == 1) {
                userRepository.remove(user);
            }
            i++;
        }

        userRepository.findAll().forEach(out::println);

        jsonRepositoryFactory.commit();
    }

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