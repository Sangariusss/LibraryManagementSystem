package com.sangarius.oop.library.service.generator;

import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.entity.impl.Category;
import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class BookGenerator {

    public static Set<Book> generateBooks(int count) {
        Set<Book> books = new HashSet<>();
        Faker faker = new Faker();

        for (int i = 0; i < count; i++) {
            UUID bookId = UUID.randomUUID();
            String title = generateUniqueTitle(books, faker.book().title());
            String author = faker.book().author();
            String categoryName = faker.book().genre();
            UUID categoryId = UUID.randomUUID();
            Category category = new Category(categoryId, categoryName);
            int yearPublished = faker.number().numberBetween(1800, 2022);

            Book book = new Book(bookId, title, author, category, yearPublished);
            books.add(book);
        }

        return books;
    }

    private static String generateUniqueTitle(Set<Book> existingBooks, String title) {
        final String[] uniqueTitle = {title};

        while (existingBooks.stream().anyMatch(book -> book.getTitle().equals(uniqueTitle[0]))) {
            uniqueTitle[0] = title + " " + UUID.randomUUID().toString().substring(0, 8);
        }

        return uniqueTitle[0];
    }
}