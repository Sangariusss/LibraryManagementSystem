package com.sangarius.oop.library.service.generator;

import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.entity.impl.Library;
import com.github.javafaker.Faker;

import com.sangarius.oop.library.persistence.entity.impl.Loan;
import com.sangarius.oop.library.persistence.entity.impl.User;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Utility class for generating sets of sample libraries.
 */
public class LibraryGenerator {
    private static final Faker faker = new Faker();

    /**
     * Generates a set of sample libraries.
     *
     * @param count The number of libraries to generate.
     * @param users A set of users to associate with the libraries.
     * @param books A set of books to associate with the libraries.
     * @param loans A set of loans to associate with the libraries.
     * @return A set of sample libraries.
     */
    public static Set<Library> generateLibraries(int count, Set<User> users, Set<Book> books, Set<Loan> loans) {
        Set<Library> libraries = new HashSet<>();

        for (int i = 0; i < count; i++) {
            UUID libraryId = UUID.randomUUID();
            String libraryName = faker.company().name();
            String libraryAddress = faker.address().fullAddress();
            String libraryEmail = faker.internet().emailAddress();

            Library library = new Library(libraryId, libraryName, libraryAddress, libraryEmail);

            // Add books, users, and loans to the library
            library.getBooks().addAll(books);
            library.getUsers().addAll(users);
            library.getLoans().addAll(loans);

            libraries.add(library);
        }

        return libraries;
    }
}