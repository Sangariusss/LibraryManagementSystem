package com.sangarius.oop.library.persistence.repository.contracts;

import com.sangarius.oop.library.persistence.entity.impl.Review;
import com.sangarius.oop.library.persistence.repository.Repository;

import java.util.Set;
import java.util.UUID;

/**
 * Repository interface for managing reviews.
 */
public interface ReviewRepository extends Repository<Review> {

    /**
     * Finds reviews by the book's ID.
     *
     * @param bookId The ID of the book.
     * @return A set of reviews associated with the specified book.
     */
    Set<Review> findAllByBookId(UUID bookId);

    /**
     * Finds reviews by the reviewer's ID.
     *
     * @param reviewerId The ID of the reviewer.
     * @return A set of reviews associated with the specified reviewer.
     */
    Set<Review> findAllByReviewerId(int reviewerId);

    /**
     * Finds reviews with a specific rating.
     *
     * @param rating The rating to search for.
     * @return A set of reviews with the specified rating.
     */
    Set<Review> findAllByRating(int rating);
}