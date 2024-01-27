package com.sangarius.oop.library.service;

import com.sangarius.oop.library.persistence.entity.impl.Category;
import com.sangarius.oop.library.persistence.repository.RepositoryFactory;
import com.sangarius.oop.library.persistence.repository.contracts.CategoryRepository;

import java.util.Set;

public class CategoryRepositoryService {
    private final CategoryRepository categoryRepository;

    public CategoryRepositoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public static CategoryRepositoryService createCategoryRepositoryService(RepositoryFactory repositoryFactory) {
        CategoryRepository categoryRepository = repositoryFactory.getCategoryRepository();
        return new CategoryRepositoryService(categoryRepository);
    }

    public void processCategoriesAndCommit(Set<Category> categories) {
        for (Category category : categories) {
            categoryRepository.add(category);
        }
    }

    private void printAllCategories() {
        categoryRepository.findAll().forEach(System.out::println);
    }
}