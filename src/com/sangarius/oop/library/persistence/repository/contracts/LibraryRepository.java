package com.sangarius.oop.library.persistence.repository.contracts;

import com.sangarius.oop.library.persistence.entity.impl.Library;

import com.sangarius.oop.library.persistence.repository.Repository;
import java.util.Set;

/**
 * Repository interface for managing libraries.
 */
public interface LibraryRepository extends Repository<Library> {

    /**
     * Finds all libraries with loans.
     *
     * @return A set of libraries with loans.
     */
    Set<Library> findAllByLoans();

    /**
     * Finds all libraries by address.
     *
     * @param address The address to search for.
     * @return A set of libraries with the specified address.
     */
    Set<Library> findAllByAddress(String address);

    /**
     * Finds a library by email.
     *
     * @param email The email to search for.
     * @return The library with the specified email, or null if not found.
     */
    Library findByEmail(String email);
}