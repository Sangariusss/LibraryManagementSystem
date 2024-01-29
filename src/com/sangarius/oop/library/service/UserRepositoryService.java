package com.sangarius.oop.library.service;

import com.sangarius.oop.library.persistence.entity.impl.User;
import com.sangarius.oop.library.persistence.repository.RepositoryFactory;
import com.sangarius.oop.library.persistence.repository.contracts.UserRepository;

import java.util.Set;

/**
 * Service class for processing users and interacting with the user repository.
 */
public class UserRepositoryService {

    private final UserRepository userRepository;

    /**
     * Constructs a new instance of UserRepositoryService with the provided user repository.
     *
     * @param userRepository The user repository to be used.
     */
    public UserRepositoryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new instance of UserRepositoryService using the provided RepositoryFactory.
     *
     * @param repositoryFactory The RepositoryFactory used to create the UserRepository instance.
     * @return A new instance of UserRepositoryService.
     */
    public static UserRepositoryService createUserRepositoryService(RepositoryFactory repositoryFactory) {
        UserRepository userRepository = repositoryFactory.getUserRepository();
        return new UserRepositoryService(userRepository);
    }

    /**
     * Processes the provided set of users by adding them to the user repository.
     *
     * @param users The set of users to be processed.
     */
    public void processUsersAndCommit(Set<User> users) {
        for (User user : users) {
            userRepository.add(user);
        }
    }

    // This method is private and does not require JavaDoc
    private void printAllUsers() {
        userRepository.findAll().forEach(System.out::println);
    }
}
