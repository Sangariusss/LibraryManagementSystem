package com.sangarius.oop.library.service;

import com.sangarius.oop.library.persistence.entity.impl.Category;
import com.sangarius.oop.library.persistence.repository.RepositoryFactory;
import com.sangarius.oop.library.persistence.repository.contracts.CategoryRepository;

import java.util.Set;

/**
 * Service class for processing categories and interacting with the category repository.
 */
public class CategoryRepositoryService {
    private final CategoryRepository categoryRepository;

    /**
     * Constructs a new instance of CategoryRepositoryService with the provided category repository.
     *
     * @param categoryRepository The category repository to be used.
     */
    public CategoryRepositoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Creates a new instance of CategoryRepositoryService using the provided repository factory.
     *
     * @param repositoryFactory The repository factory to create the category repository.
     * @return A new instance of CategoryRepositoryService.
     */
    public static CategoryRepositoryService createCategoryRepositoryService(RepositoryFactory repositoryFactory) {
        CategoryRepository categoryRepository = repositoryFactory.getCategoryRepository();
        return new CategoryRepositoryService(categoryRepository);
    }

    /**
     * Processes the provided set of categories by adding them to the category repository.
     *
     * @param categories The set of categories to be processed.
     */
    public void processCategoriesAndCommit(Set<Category> categories) {
        for (Category category : categories) {
            categoryRepository.add(category);
        }
    }

    // This method is private and does not require JavaDoc
    private void printAllCategories() {
        categoryRepository.findAll().forEach(System.out::println);
    }
}