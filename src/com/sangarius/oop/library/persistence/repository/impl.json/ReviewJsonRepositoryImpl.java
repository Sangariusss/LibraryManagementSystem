package com.sangarius.oop.library.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sangarius.oop.library.persistence.entity.impl.Review;
import com.sangarius.oop.library.persistence.repository.contracts.ReviewRepository;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * JSON implementation of the Review repository.
 */
public class ReviewJsonRepositoryImpl extends GenericJsonRepository<Review> implements ReviewRepository {

    /**
     * Constructs a new instance of {@code ReviewJsonRepositoryImpl}.
     *
     * @param gson The Gson instance for JSON serialization/deserialization.
     */
    public ReviewJsonRepositoryImpl(Gson gson) {
        super(gson, JsonPathFactory.REVIEWS.getPath(), new TypeToken<Set<Review>>() {}.getType());
    }

    /**
     * Finds reviews by the book's ID.
     *
     * @param bookId The ID of the book.
     * @return A set of reviews associated with the specified book.
     */
    @Override
    public Set<Review> findAllByBookId(UUID bookId) {
        return entities.stream()
            .filter(review -> bookId.equals(review.getBookId()))
            .collect(Collectors.toSet());
    }

    /**
     * Finds reviews by the reviewer's ID.
     *
     * @param reviewerId The ID of the reviewer.
     * @return A set of reviews associated with the specified reviewer.
     */
    @Override
    public Set<Review> findAllByReviewerId(int reviewerId) {
        return entities.stream()
            .filter(review -> reviewerId == review.getReviewerId())
            .collect(Collectors.toSet());
    }

    /**
     * Finds reviews with a specific rating.
     *
     * @param rating The rating to search for.
     * @return A set of reviews with the specified rating.
     */
    @Override
    public Set<Review> findAllByRating(int rating) {
        return entities.stream()
            .filter(review -> rating == review.getRating())
            .collect(Collectors.toSet());
    }
}