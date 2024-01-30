package com.sangarius.oop.library.persistence.repository.contracts;

import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.entity.impl.Review;
import com.sangarius.oop.library.persistence.entity.impl.User;
import com.sangarius.oop.library.persistence.repository.Repository;

import java.util.Set;

/**
 * Repository interface for managing reviews.
 */
public interface ReviewRepository extends Repository<Review> {

    /**
     * Finds reviews by the book's ID.
     *
     * @param book The book.
     * @return A set of reviews associated with the specified book.
     */
    Set<Review> findAllByBook(Book book);

    /**
     * Finds reviews by the reviewer.
     *
     * @param reviewer The reviewer (user).
     * @return A set of reviews associated with the specified reviewer.
     */
    Set<Review> findAllByReviewer(User reviewer);

    /**
     * Finds reviews with a specific rating.
     *
     * @param rating The rating to search for.
     * @return A set of reviews with the specified rating.
     */
    Set<Review> findAllByRating(int rating);
}