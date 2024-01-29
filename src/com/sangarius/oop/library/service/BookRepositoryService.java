package com.sangarius.oop.library.service;

import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.repository.contracts.BookRepository;

import java.util.Set;

/**
 * Service class for processing books and interacting with the book repository.
 */
public class BookRepositoryService {

    private final BookRepository bookRepository;

    /**
     * Constructs a new instance of BookRepositoryService with the provided book repository.
     *
     * @param bookRepository The book repository to be used.
     */
    public BookRepositoryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Processes the provided set of books by adding them to the book repository.
     *
     * @param books The set of books to be processed.
     */
    public void processBooksAndCommit(Set<Book> books) {
        for (Book book : books) {
            bookRepository.add(book);
        }
    }

    // This method is private and does not require JavaDoc
    private void printAllBooks() {
        bookRepository.findAll().forEach(System.out::println);
    }
}
