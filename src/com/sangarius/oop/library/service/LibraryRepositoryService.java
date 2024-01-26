package com.sangarius.oop.library.service;

import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.entity.impl.Library;
import com.sangarius.oop.library.persistence.entity.impl.Loan;
import com.sangarius.oop.library.persistence.entity.impl.User;
import com.sangarius.oop.library.persistence.repository.RepositoryFactory;
import com.sangarius.oop.library.persistence.repository.contracts.LibraryRepository;

import java.util.List;
import java.util.Set;

public class LibraryRepositoryService {
    private final LibraryRepository libraryRepository;

    public LibraryRepositoryService(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public static LibraryRepositoryService createLibraryRepositoryService(RepositoryFactory repositoryFactory) {
        LibraryRepository libraryRepository = repositoryFactory.getLibraryRepository();
        return new LibraryRepositoryService(libraryRepository);
    }

    public void processLibrariesAndCommit(Set<Library> libraries, Set<User> users, Set<Book> books, Set<Loan> loans) {
        for (Library library : libraries) {
            // Add books, users, and loans to the library
            library.getBooks().addAll(books);
            library.getUsers().addAll(users);
            library.getLoans().addAll(loans);

            // Add the library to the repository
            libraryRepository.add(library);
        }

        printAllLibraries();
    }

    private void printAllLibraries() {
        libraryRepository.findAll().forEach(System.out::println);
    }
}