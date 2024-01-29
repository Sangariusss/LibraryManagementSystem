package com.sangarius.oop.library.service;

import com.sangarius.oop.library.persistence.entity.impl.Review;
import com.sangarius.oop.library.persistence.repository.contracts.ReviewRepository;

import java.util.Set;

/**
 * Service class for processing reviews and interacting with the review repository.
 */
public class ReviewRepositoryService {

    private final ReviewRepository reviewRepository;

    /**
     * Constructs a new instance of ReviewRepositoryService with the provided review repository.
     *
     * @param reviewRepository The review repository to be used.
     */
    public ReviewRepositoryService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Processes the provided set of reviews by adding them to the review repository.
     *
     * @param reviews The set of reviews to be processed.
     */
    public void processReviewsAndCommit(Set<Review> reviews) {
        for (Review review : reviews) {
            reviewRepository.add(review);
        }
    }

    // This method is private and does not require JavaDoc
    private void printAllReviews() {
        reviewRepository.findAll().forEach(System.out::println);
    }
}