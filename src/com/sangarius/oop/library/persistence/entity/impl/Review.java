package com.sangarius.oop.library.persistence.entity.impl;

/**
 * Represents a review for a book in the library.
 */
public class Review {
    private int id;
    private String reviewText;
    private int rating;
    private int reviewerId;

    /**
     * Constructs a new Review with the specified details.
     *
     * @param id          The ID of the review.
     * @param reviewText  The text of the review.
     * @param rating      The rating given in the review.
     * @param reviewerId  The ID of the reviewer (user).
     */
    public Review(int id, String reviewText, int rating, int reviewerId) {
        this.id = id;
        this.reviewText = reviewText;
        this.rating = rating;
        this.reviewerId = reviewerId;
    }

    // Getters and setters for class fields

    /**
     * Gets the ID of the review.
     *
     * @return The ID of the review.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the review.
     *
     * @param id The new ID of the review.
     */
    public void setId(int id) {
        this.id = id;
    }

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
    }

    /**
     * Gets the ID of the reviewer (user).
     *
     * @return The ID of the reviewer (user).
     */
    public int getReviewerId() {
        return reviewerId;
    }

    /**
     * Sets the ID of the reviewer (user).
     *
     * @param reviewerId The new ID of the reviewer (user).
     */
    public void setReviewerId(int reviewerId) {
        this.reviewerId = reviewerId;
    }
}
