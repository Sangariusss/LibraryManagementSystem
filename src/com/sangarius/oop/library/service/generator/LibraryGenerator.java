package com.sangarius.oop.library.service.generator;

import com.sangarius.oop.library.persistence.entity.impl.Library;
import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class LibraryGenerator {

    private static final Faker faker = new Faker();

    /**
     * Generates a set of sample libraries.
     *
     * @param count The number of libraries to generate.
     * @return A set of sample libraries.
     */
    public static Set<Library> generateLibraries(int count) {
        Set<Library> libraries = new HashSet<>();

        for (int i = 0; i < count; i++) {
            UUID libraryId = UUID.randomUUID();
            String libraryName = faker.company().name();
            String libraryAddress = faker.address().fullAddress();
            String libraryEmail = faker.internet().emailAddress();

            Library library = new Library(libraryId, libraryName, libraryAddress, libraryEmail);
            libraries.add(library);
        }

        return libraries;
    }
}