package com.sangarius.oop.library.persistence.repository;

import com.sangarius.oop.library.persistence.entity.impl.Category;
import com.sangarius.oop.library.persistence.repository.contracts.CategoryRepository;
import com.sangarius.oop.library.persistence.repository.contracts.LoanRepository;
import com.sangarius.oop.library.persistence.repository.contracts.BookRepository;
import com.sangarius.oop.library.persistence.repository.contracts.ReviewRepository;
import com.sangarius.oop.library.persistence.repository.contracts.UserRepository;
import com.sangarius.oop.library.persistence.repository.contracts.LibraryRepository;
import com.sangarius.oop.library.persistence.repository.impl.json.JsonRepositoryFactory;
import org.apache.commons.lang3.NotImplementedException;

/**
 * An abstract factory for creating repository instances based on the specified type.
 */
public abstract class RepositoryFactory {

    /**
     * Represents the JSON repository type.
     */
    public static final int JSON = 1;

    /**
     * Represents the XML repository type.
     */
    public static final int XML = 2;

    /**
     * Represents the PostgreSQL repository type.
     */
    public static final int POSTGRESQL = 3;

    /**
     * Gets a repository factory instance based on the specified type.
     *
     * @param whichFactory The type of repository factory to create.
     * @return A repository factory instance.
     * @throws IllegalArgumentException If an invalid factory type is provided.
     */
    public static RepositoryFactory getRepositoryFactory(int whichFactory) {
        return switch (whichFactory) {
            case JSON -> JsonRepositoryFactory.getInstance();
            case XML -> throw new NotImplementedException("Working with XML files is not implemented.");
            case POSTGRESQL -> throw new NotImplementedException(
                "Working with PostgreSQL databases is not implemented.");
            default -> throw new IllegalArgumentException(
                "Invalid repository factory type.");
        };
    }

    /**
     * Gets a loan repository instance.
     *
     * @return A loan repository instance.
     */
    public abstract LoanRepository getLoanRepository();

    /**
     * Gets a book repository instance.
     *
     * @return A book repository instance.
     */
    public abstract BookRepository getBookRepository();

    /**
     * Gets a review repository instance.
     *
     * @return A review repository instance.
     */
    public abstract ReviewRepository getReviewRepository();

    /**
     * Gets a user repository instance.
     *
     * @return A user repository instance.
     */
    public abstract UserRepository getUserRepository();

    /**
     * Gets a library repository instance.
     *
     * @return A library repository instance.
     */
    public abstract LibraryRepository getLibraryRepository();

    /**
     * Gets a category repository instance.
     *
     * @return A category repository instance.
     */
    public abstract CategoryRepository getCategoryRepository();

    /**
     * Commits any pending changes to the repositories.
     */
    public abstract void commit();
}