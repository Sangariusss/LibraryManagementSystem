package com.sangarius.oop.library.persistence.repository.contracts;

import com.sangarius.oop.library.persistence.entity.impl.Category;
import com.sangarius.oop.library.persistence.repository.Repository;

import java.util.Set;

/**
 * The repository interface for managing {@link Category} entities.
 */
public interface CategoryRepository extends Repository<Category> {

    /**
     * Finds all categories with the specified name.
     *
     * @param categoryName the name of the category to search for
     * @return a set of categories with the specified name
     */
    Set<Category> findAllByName(String categoryName);
}