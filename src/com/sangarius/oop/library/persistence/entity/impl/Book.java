package com.sangarius.oop.library.persistence.entity.impl;

import com.sangarius.oop.library.persistence.entity.Entity;

import com.sangarius.oop.library.persistence.entity.ErrorTemplates;
import com.sangarius.oop.library.persistence.exception.EntityArgumentException;
import java.util.ArrayList;
import java.util.List;
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
     * Gets the list of reviews for the book.
     *
     * @return The list of reviews for the book.
     */
    public List<Review> getReviews() {
        return reviews;
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
        if (title.length() > 255) {
            errors.add(ErrorTemplates.MAX_LENGTH.getTemplate().formatted(templateName, 255));
        }
    }

    private void validateAuthor() {
        final String templateName = "Author";

        if (author == null || author.isBlank()) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
        if (author.length() > 100) {
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
