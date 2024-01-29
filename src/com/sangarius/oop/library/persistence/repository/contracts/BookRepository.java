package com.sangarius.oop.library.persistence.repository.contracts;

import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.repository.Repository;

import java.util.Set;

/**
 * The repository interface for managing {@link Book} entities.
 */
public interface BookRepository extends Repository<Book> {

    /**
     * Finds all books belonging to a specific category.
     *
     * @param categoryId the ID of the category to search for
     * @return a set of books belonging to the specified category
     */
    Set<Book> findAllByCategory(String categoryId);

    /**
     * Finds all books written by a specific author.
     *
     * @param authorId the ID of the author to search for
     * @return a set of books written by the specified author
     */
    Set<Book> findAllByAuthor(String authorId);

    /**
     * Finds all available books.
     *
     * @return a set of all available books
     */
    Set<Book> findAllAvailable();
}
