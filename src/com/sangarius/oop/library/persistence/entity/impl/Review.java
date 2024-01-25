package com.sangarius.oop.library.persistence.entity.impl;

import com.sangarius.oop.library.persistence.entity.Entity;
import com.sangarius.oop.library.persistence.entity.ErrorTemplates;
import com.sangarius.oop.library.persistence.exception.EntityArgumentException;

import java.util.Objects;
import java.util.UUID;

/**
 * Represents a review for a book in the library.
 */
public class Review extends Entity {

    private String reviewText;
    private int rating;
    private UUID reviewerId;
    private UUID bookId;

    /**
     * Constructs a new Review with the specified details.
     *
     * @param id          The ID of the review.
     * @param reviewText  The text of the review.
     * @param rating      The rating given in the review.
     * @param reviewerId  The ID of the reviewer (user).
     * @param bookId      The ID of the associated book.
     */
    public Review(UUID id, String reviewText, int rating, UUID reviewerId, UUID bookId) {
        super(id);
        this.reviewText = reviewText;
        this.rating = rating;
        this.reviewerId = reviewerId;
        this.bookId = bookId;
        validateReview();
    }

    // Getters and setters for class fields

    /**
     * Gets the text of the review.
     *
     * @return The text of the review.
     */
    public String getReviewText() {
        return reviewText;
    }

    /**
     * Sets the text of the review.
     *
     * @param reviewText The new text of the review.
     */
    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
        validateReviewText();
    }

    /**
     * Gets the rating given in the review.
     *
     * @return The rating given in the review.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Sets the rating given in the review.
     *
     * @param rating The new rating given in the review.
     */
    public void setRating(int rating) {
        this.rating = rating;
        validateRating();
    }

    /**
     * Gets the ID of the reviewer (user).
     *
     * @return The ID of the reviewer (user).
     */
    public UUID getReviewerId() {
        return reviewerId;
    }

    /**
     * Sets the ID of the reviewer (user).
     *
     * @param reviewerId The new ID of the reviewer (user).
     */
    public void setReviewerId(UUID reviewerId) {
        this.reviewerId = reviewerId;
        validateReviewerId();
    }

    /**
     * Gets the ID of the associated book.
     *
     * @return The ID of the associated book.
     */
    public UUID getBookId() {
        return bookId;
    }

    /**
     * Sets the ID of the associated book.
     *
     * @param bookId The new ID of the associated book.
     */
    public void setBookId(UUID bookId) {
        this.bookId = bookId;
        validateBookId();
    }

    /**
     * Validates the review entity and populates errors list if validation fails.
     */
    private void validateReview() {
        validateReviewText();
        validateRating();
        validateReviewerId();
        validateBookId();

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }
    }

    private void validateReviewText() {
        final String templateName = "Review Text";

        if (reviewText == null || reviewText.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
    }

    private void validateRating() {
        final String templateName = "Rating";

        if (rating < 1 || rating > 5) {
            errors.add(ErrorTemplates.RATING_RANGE.getTemplate().formatted(templateName));
        }
    }

    private void validateReviewerId() {
        final String templateName = "Reviewer ID";

        if (reviewerId == null) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
    }

    private void validateBookId() {
        final String templateName = "Book ID";

        if (bookId == null) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
    }

    /**
     * Compares this review to another object for equality.
     *
     * @param o The object to compare to.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Review review = (Review) o;
        return Objects.equals(id, review.id);
    }

    /**
     * Computes a hash code for this review.
     *
     * @return A hash code value for this review.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of the review.
     *
     * @return A string representation of the review.
     */
    @Override
    public String toString() {
        return "Review{" +
            "id=" + id +
            ", reviewText='" + reviewText + '\'' +
            ", rating=" + rating +
            ", reviewerId=" + reviewerId +
            ", bookId=" + bookId +
            '}';
    }
}