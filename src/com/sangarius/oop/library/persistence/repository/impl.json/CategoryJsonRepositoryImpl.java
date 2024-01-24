package com.sangarius.oop.library.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sangarius.oop.library.persistence.entity.impl.Category;
import com.sangarius.oop.library.persistence.repository.contracts.CategoryRepository;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * JSON implementation of the Category repository.
 */
public class CategoryJsonRepositoryImpl extends GenericJsonRepository<Category> implements CategoryRepository {

    /**
     * Constructs a new instance of {@code CategoryJsonRepositoryImpl}.
     *
     * @param gson The Gson instance for JSON serialization/deserialization.
     */
    public CategoryJsonRepositoryImpl(Gson gson) {
        super(gson, JsonPathFactory.CATEGORIES.getPath(), new TypeToken<Set<Category>>() {}.getType());
    }

    /**
     * Finds all categories by a specific name.
     *
     * @param categoryName The name of the category.
     * @return A set of categories with the specified name.
     */
    @Override
    public Set<Category> findAllByName(String categoryName) {
        return entities.stream()
            .filter(category -> categoryName.equals(category.getName()))
            .collect(Collectors.toSet());
    }
}