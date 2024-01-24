package com.sangarius.oop.library.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.repository.contracts.BookRepository;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * JSON implementation of the Book repository.
 */
public class BookJsonRepositoryImpl extends GenericJsonRepository<Book> implements BookRepository {

    /**
     * Constructs a new instance of {@code BookJsonRepositoryImpl}.
     *
     * @param gson The Gson instance for JSON serialization/deserialization.
     */
    public BookJsonRepositoryImpl(Gson gson) {
        super(gson, JsonPathFactory.BOOKS.getPath(), TypeToken
            .getParameterized(Set.class, Book.class)
            .getType());
    }

    /**
     * Finds all books in a specific category.
     *
     * @param categoryId The ID of the category.
     * @return A set of books in the specified category.
     */
    @Override
    public Set<Book> findAllByCategory(String categoryId) {
        return entities.stream()
            .filter(book -> categoryId.equals(book.getCategory().getId().toString()))
            .collect(Collectors.toSet());
    }

    /**
     * Finds all books by a specific author.
     *
     * @param authorId The ID of the author.
     * @return A set of books written by the specified author.
     */
    @Override
    public Set<Book> findAllByAuthor(String authorId) {
        return entities.stream()
            .filter(book -> authorId.equals(book.getAuthor()))
            .collect(Collectors.toSet());
    }

    /**
     * Finds all available books.
     *
     * @return A set of available books.
     */
    @Override
    public Set<Book> findAllAvailable() {
        return entities.stream()
            .filter(Book::isAvailable)
            .collect(Collectors.toSet());
    }
}