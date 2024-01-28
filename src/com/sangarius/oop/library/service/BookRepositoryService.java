package com.sangarius.oop.library.service;

import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.repository.contracts.BookRepository;

import java.util.Set;

public class BookRepositoryService {

    private final BookRepository bookRepository;

    public BookRepositoryService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void processBooksAndCommit(Set<Book> books) {
        for (Book book : books) {
            bookRepository.add(book);
        }
    }

    private void printAllBooks() {
        bookRepository.findAll().forEach(System.out::println);
    }
}