package com.sangarius.oop.library.service;

import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.entity.impl.Library;
import com.sangarius.oop.library.persistence.entity.impl.Loan;
import com.sangarius.oop.library.persistence.entity.impl.User;
import com.sangarius.oop.library.persistence.repository.RepositoryFactory;
import com.sangarius.oop.library.persistence.repository.contracts.LibraryRepository;

import java.util.List;
import java.util.Set;

/**
 * Service class for processing libraries and interacting with the library repository.
 */
public class LibraryRepositoryService {
    private final LibraryRepository libraryRepository;

    /**
     * Constructs a new instance of LibraryRepositoryService with the provided library repository.
     *
     * @param libraryRepository The library repository to be used.
     */
    public LibraryRepositoryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    /**
     * Creates a new instance of LibraryRepositoryService using the provided repository factory.
     *
     * @param repositoryFactory The repository factory to create the library repository.
     * @return A new instance of LibraryRepositoryService.
     */
    public static LibraryRepositoryService createLibraryRepositoryService(RepositoryFactory repositoryFactory) {
        LibraryRepository libraryRepository = repositoryFactory.getLibraryRepository();
        return new LibraryRepositoryService(libraryRepository);
    }

    /**
     * Processes the provided set of libraries by adding them to the library repository.
     * Additionally, adds the associated users, books, and loans to each library.
     *
     * @param libraries The set of libraries to be processed.
     * @param users     The set of users associated with the libraries.
     * @param books     The set of books associated with the libraries.
     * @param loans     The set of loans associated with the libraries.
     */
    public void processLibrariesAndCommit(Set<Library> libraries, Set<User> users, Set<Book> books, Set<Loan> loans) {
        for (Library library : libraries) {
            // Add books, users, and loans to the library
            library.getBooks().addAll(books);
            library.getUsers().addAll(users);
            library.getLoans().addAll(loans);

            // Add the library to the repository
            libraryRepository.add(library);
        }
    }

    // This method is private and does not require JavaDoc
    private void printAllLibraries() {
        libraryRepository.findAll().forEach(System.out::println);
    }
}
