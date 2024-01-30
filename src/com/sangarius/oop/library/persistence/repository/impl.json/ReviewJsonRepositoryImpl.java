package com.sangarius.oop.library.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.entity.impl.Review;
import com.sangarius.oop.library.persistence.entity.impl.User;
import com.sangarius.oop.library.persistence.repository.contracts.ReviewRepository;

import java.util.Set;
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
     * @param book The book.
     * @return A set of reviews associated with the specified book.
     */
    @Override
    public Set<Review> findAllByBook(Book book) {
        return entities.stream()
            .filter(review -> book.equals(review.getBook()))
            .collect(Collectors.toSet());
    }

    /**
     * Finds reviews by the reviewer.
     *
     * @param reviewer The reviewer (user).
     * @return A set of reviews associated with the specified reviewer.
     */
    @Override
    public Set<Review> findAllByReviewer(User reviewer) {
        return entities.stream()
            .filter(review -> reviewer == review.getReviewer())
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