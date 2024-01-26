package com.sangarius.oop.library.persistence.entity.impl;

import com.sangarius.oop.library.persistence.entity.Entity;

import com.sangarius.oop.library.persistence.entity.ErrorTemplates;
import com.sangarius.oop.library.persistence.exception.EntityArgumentException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

/**
 * Represents a book in the library.
 */
public class Book extends Entity {

    private String title;
    private String author;
    private Category category;
    private int yearPublished;
    private List<Review> reviews;

    /**
     * Constructs a new Book with the specified details.
     *
     * @param id            The unique identifier of the book.
     * @param title         The title of the book.
     * @param author        The author of the book.
     * @param category      The category of the book.
     * @param yearPublished The year the book was published.
     */
    public Book(UUID id, String title, String author, Category category, int yearPublished) {
        super(id);
        this.title = title;
        this.author = author;
        this.category = category;
        this.yearPublished = yearPublished;
        this.reviews = new ArrayList<>();
        validateBook();
    }

    // Getters and setters for class fields

    /**
     * Gets the title of the book.
     *
     * @return The title of the book.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the book.
     *
     * @param title The new title of the book.
     */
    public void setTitle(String title) {
        this.title = title;
        validateTitle();
    }

    /**
     * Gets the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the book.
     *
     * @param author The new author of the book.
     */
    public void setAuthor(String author) {
        this.author = author;
        validateAuthor();
    }

    /**
     * Gets the category of the book.
     *
     * @return The category of the book.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the category of the book.
     *
     * @param category The new category of the book.
     */
    public void setCategory(Category category) {
        this.category = category;
        validateCategory();
    }

    /**
     * Gets the year the book was published.
     *
     * @return The year the book was published.
     */
    public int getYearPublished() {
        return yearPublished;
    }

    /**
     * Sets the year the book was published.
     *
     * @param yearPublished The new year the book was published.
     */
    public void setYearPublished(int yearPublished) {
        this.yearPublished = yearPublished;
        validateYearPublished();
    }

    /**
     * Adds a review to the list of reviews for the book.
     *
     * @param review The review to be added.
     */
    public void addReview(Review review) {
        reviews.add(review);
    }

    /**
     * Sets the set of reviews for the book.
     *
     * @param reviews The set of reviews to set.
     */
    public void setReviews(Set<Review> reviews) {
        this.reviews = new ArrayList<>(reviews);
    }

    /**
     * Checks whether the book is available for borrowing.
     *
     * @return {@code true} if the book is available, {@code false} otherwise.
     */
    public boolean isAvailable() {
        // In this example, let's assume a simple availability check based on reviews.
        // You can customize this logic based on your specific requirements.

        // If there are no reviews or all reviews have a positive rating, consider the book available.
        return reviews.isEmpty() || reviews.stream().allMatch(review -> review.getRating() > 0);
    }

    /**
     * Validates the book entity and populates errors list if validation fails.
     */
    private void validateBook() {
        validateTitle();
        validateAuthor();
        validateCategory();
        validateYearPublished();

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }
    }

    private void validateTitle() {
        final String templateName = "Title";

        if (title == null || title.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (title == null || title.length() > 255) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 255));
        }
    }

    private void validateAuthor() {
        final String templateName = "Author";

        if (author == null || author.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (author == null || author.length() > 100) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 100));
        }
    }

    private void validateCategory() {
        final String templateName = "Category";

        if (category == null) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (category != null && category.getName().length() > 255) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 255));
        }
    }

    private void validateYearPublished() {
        final String templateName = "YearPublished";

        if (yearPublished <= 0) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (yearPublished < 1000 || yearPublished > 9999) {
            errors.add(ErrorTemplates.YEAR_RANGE.getTemplate());
        }
    }

    /**
     * Compares this book to another object for equality.
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
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }

    /**
     * Computes a hash code for this book.
     *
     * @return A hash code value for this book.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Book{" +
            "id=" + id +
            ", title='" + title + '\'' +
            ", author='" + author + '\'' +
            ", category=" + category +
            ", yearPublished=" + yearPublished +
            ", reviews=" + reviews +
            '}';
    }
}
