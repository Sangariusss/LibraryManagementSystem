package com.sangarius.oop.library.service.generator;

import com.sangarius.oop.library.persistence.entity.impl.Category;
import com.github.javafaker.Faker;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class CategoryGenerator {

    private static final Faker faker = new Faker();

    public static Set<Category> generateCategories(int count) {
        Set<Category> categories = new HashSet<>();

        for (int i = 0; i < count; i++) {
            UUID categoryId = UUID.randomUUID();
            String categoryName = faker.book().genre();

            Category category = new Category(categoryId, categoryName);
            categories.add(category);
        }

        return categories;
    }
}