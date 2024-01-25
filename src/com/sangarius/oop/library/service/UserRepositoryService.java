package com.sangarius.oop.library.service;

import com.sangarius.oop.library.persistence.entity.impl.User;
import com.sangarius.oop.library.persistence.repository.RepositoryFactory;
import com.sangarius.oop.library.persistence.repository.contracts.UserRepository;

import java.util.Set;

public class UserRepositoryService {
    private final UserRepository userRepository;

    public UserRepositoryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static UserRepositoryService createUserRepositoryService(
        RepositoryFactory repositoryFactory) {
        UserRepository userRepository = repositoryFactory.getUserRepository();
        return new UserRepositoryService(userRepository);
    }

    public void processUsersAndCommit(Set<User> users) {
        for (User user : users) {
            userRepository.add(user);
        }

        printAllUsers();
    }


    private void printAllUsers() {
        userRepository.findAll().forEach(System.out::println);
    }
}
