package com.sangarius.oop.library.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sangarius.oop.library.persistence.entity.impl.Library;
import com.sangarius.oop.library.persistence.repository.contracts.LibraryRepository;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * JSON implementation of the Library repository.
 */
public class LibraryJsonRepositoryImpl extends GenericJsonRepository<Library> implements LibraryRepository {

    /**
     * Constructs a new instance of {@code LibraryJsonRepositoryImpl}.
     *
     * @param gson The Gson instance for JSON serialization/deserialization.
     */
    public LibraryJsonRepositoryImpl(Gson gson) {
        super(gson, JsonPathFactory.LIBRARIES.getPath(), new TypeToken<Set<Library>>() {}.getType());
    }

    /**
     * Finds a library by its name.
     *
     * @param name The name of the library to find.
     * @return The library with the specified name, or null if not found.
     */
    public Library findByName(String name) {
        return entities.stream()
            .filter(library -> name.equals(library.getName()))
            .findFirst()
            .orElse(null);
    }

    /**
     * Finds all libraries at a specific address.
     *
     * @param address The address to filter libraries by.
     * @return A set of libraries at the specified address.
     */
    public Set<Library> findAllByAddress(String address) {
        return entities.stream()
            .filter(library -> address.equals(library.getAddress()))
            .collect(Collectors.toSet());
    }

    /**
     * Finds all libraries with loans.
     *
     * @return A set of libraries with loans.
     */
    public Set<Library> findAllByLoans() {
        return entities.stream()
            .filter(library -> !library.getLoans().isEmpty())
            .collect(Collectors.toSet());
    }

    /**
     * Finds a library by its email.
     *
     * @param email The email of the library to find.
     * @return The library with the specified email, or null if not found.
     */
    public Library findByEmail(String email) {
        return entities.stream()
            .filter(library -> email.equals(library.getEmail()))
            .findFirst()
            .orElse(null);
    }
}