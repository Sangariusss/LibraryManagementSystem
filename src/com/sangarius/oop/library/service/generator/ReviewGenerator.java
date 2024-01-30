package com.sangarius.oop.library.service.generator;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.entity.impl.Review;
import com.sangarius.oop.library.persistence.entity.impl.User;
import com.github.javafaker.Faker;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Utility class for generating sets of reviews.
 */
public class ReviewGenerator {

    private static final Faker faker = new Faker();

    /**
     * Generates a set of reviews.
     *
     * @param count         The number of reviews to generate.
     * @param users         A set of users who will be the reviewers.
     * @param jsonFilePath  The file path to the JSON containing book data.
     * @return              A set of generated reviews.
     */
    public static Set<Review> generateReviews(int count, Set<User> users, String jsonFilePath) {
        Set<Review> reviews = new HashSet<>();

        for (int i = 0; i < count; i++) {
            UUID reviewId = UUID.randomUUID();
            String reviewText = faker.lorem().paragraph();
            int rating = faker.number().numberBetween(1, 5);

            // Choose an existing user as reviewer
            User reviewer = getRandomUser(users);

            // Choose a random available book
            Book book = getRandomBookFromJson(jsonFilePath);

            Review review = new Review(reviewId, reviewText, rating, reviewer, book);
            reviews.add(review);
        }

        return reviews;
    }

    /**
     * Retrieves a random user from the given set of users.
     *
     * @param users A set of users.
     * @return      A randomly selected user.
     */
    private static User getRandomUser(Set<User> users) {
        List<User> userList = new ArrayList<>(users);
        int randomIndex = faker.number().numberBetween(0, users.size());
        return userList.get(randomIndex);
    }

    /**
     * Retrieves a random available book from the given JSON file.
     *
     * @param jsonFilePath  The file path to the JSON containing book data.
     * @return              A randomly selected available book.
     */
    private static Book getRandomBookFromJson(String jsonFilePath) {
        // Read data from the JSON file and create a set of books
        Set<Book> books = readBooksFromJson(jsonFilePath);

        // Select a random book from the set
        List<Book> availableBookList = new ArrayList<>(books);
        int randomIndex = faker.number().numberBetween(0, books.size());
        return availableBookList.get(randomIndex);
    }

    /**
     * Reads book data from a JSON file and returns a set of books.
     *
     * @param jsonFilePath  The file path to the JSON containing book data.
     * @return              A set of books parsed from the JSON file.
     */
    private static Set<Book> readBooksFromJson(String jsonFilePath) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(jsonFilePath)) {
            Set<Book> books = gson.fromJson(reader, new TypeToken<Set<Book>>(){}.getType());
            if (books.isEmpty()) {
                throw new IllegalArgumentException("No books found in the JSON file: " + jsonFilePath);
            }
            return books;
        } catch (IOException e) {
            System.err.println("Error reading JSON file: " + e.getMessage());
        }
        return new HashSet<>();
    }
}