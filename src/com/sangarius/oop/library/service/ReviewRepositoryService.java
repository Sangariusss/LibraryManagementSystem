package com.sangarius.oop.library.service;

import com.sangarius.oop.library.persistence.entity.impl.Review;
import com.sangarius.oop.library.persistence.repository.contracts.ReviewRepository;

import java.util.Set;

public class ReviewRepositoryService {

    private final ReviewRepository reviewRepository;

    public ReviewRepositoryService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void processReviewsAndCommit(Set<Review> reviews) {
        for (Review review : reviews) {
            reviewRepository.add(review);
        }

        printAllReviews();
    }

    private void printAllReviews() {
        reviewRepository.findAll().forEach(System.out::println);
    }
}