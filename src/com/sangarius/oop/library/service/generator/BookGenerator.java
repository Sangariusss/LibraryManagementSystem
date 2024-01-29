package com.sangarius.oop.library.service.generator;

import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.entity.impl.Category;
import com.sangarius.oop.library.persistence.entity.impl.Review;
import com.github.javafaker.Faker;

import com.sangarius.oop.library.persistence.entity.impl.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Utility class for generating books with associated reviews.
 */
public class BookGenerator {

    private static final Faker faker = new Faker();

    /**
     * Generates a set of books with associated reviews.
     *
     * @param count  The number of books to generate.
     * @param users  The set of users associated with the reviews.
     * @param reviews The set of reviews to associate with the books.
     * @return A set of generated books.
     */
    public static Set<Book> generateBooks(int count, Set<User> users, Set<Review> reviews) {
        Set<Book> books = new HashSet<>();

        for (int i = 0; i < count; i++) {
            UUID bookId = UUID.randomUUID();
            String title = generateUniqueTitle(books, faker.book().title());
            String author = faker.book().author();
            String categoryName = faker.book().genre();
            UUID categoryId = UUID.randomUUID();
            Category category = new Category(categoryId, categoryName);
            int yearPublished = faker.number().numberBetween(1800, 2022);
            Review randomReview = getRandomReview(reviews);

            Book book = new Book(bookId, title, author, category, yearPublished);

            book.addReview(randomReview);

            books.add(book);
        }

        return books;
    }

    /**
     * Generates a unique title based on the provided title and existing books.
     *
     * @param existingBooks A set of existing books to check for uniqueness.
     * @param title         The base title to generate a unique title from.
     * @return A unique title that does not exist in the set of existing books.
     */
    private static String generateUniqueTitle(Set<Book> existingBooks, String title) {
        final String[] uniqueTitle = {title};

        while (existingBooks.stream().anyMatch(book -> book.getTitle().equals(uniqueTitle[0]))) {
            uniqueTitle[0] = title + " " + UUID.randomUUID().toString().substring(0, 8);
        }

        return uniqueTitle[0];
    }

    /**
     * Retrieves a random review from the provided set of reviews.
     *
     * @param reviews The set of reviews to choose from.
     * @return A randomly selected review from the set.
     */
    private static Review getRandomReview(Set<Review> reviews) {
        List<Review> reviewList = new ArrayList<>(reviews);
        int randomIndex = faker.number().numberBetween(0, reviews.size());
        return reviewList.get(randomIndex);
    }
}