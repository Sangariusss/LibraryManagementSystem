package com.sangarius.oop.library.service;

import com.sangarius.oop.library.persistence.entity.impl.Library;
import com.sangarius.oop.library.persistence.repository.RepositoryFactory;
import com.sangarius.oop.library.persistence.repository.contracts.LibraryRepository;

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

    public void processLibrariesAndCommit(Set<Library> libraries) {
        for (Library library : libraries) {
            libraryRepository.add(library);
        }

        printAllLibraries();
    }

    private void printAllLibraries() {
        libraryRepository.findAll().forEach(System.out::println);
    }
}