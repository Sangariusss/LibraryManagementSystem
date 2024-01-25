package com.sangarius.oop.library.service.generator;

import com.sangarius.oop.library.persistence.entity.impl.Review;
import com.sangarius.oop.library.persistence.entity.impl.User;
import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

public class ReviewGenerator {

    public static Set<Review> generateReviews(int count, Set<User> users) {
        Set<Review> reviews = new HashSet<>();
        Faker faker = new Faker();

        for (int i = 0; i < count; i++) {
            UUID reviewId = UUID.randomUUID();
            String reviewText = faker.lorem().paragraph();
            int rating = faker.number().numberBetween(1, 5);

            // Choose an existing user as reviewer
            User randomUser = getRandomUser(users);
            UUID reviewerId = randomUser.getId();

            UUID bookId = UUID.randomUUID();

            Review review = new Review(reviewId, reviewText, rating, reviewerId, bookId);
            reviews.add(review);
        }

        return reviews;
    }

    private static User getRandomUser(Set<User> users) {
        // Convert Set<User> to array for random access
        User[] usersArray = users.toArray(new User[0]);

        // Get a random user from the array
        int randomIndex = new Random().nextInt(usersArray.length);
        return usersArray[randomIndex];
    }
}