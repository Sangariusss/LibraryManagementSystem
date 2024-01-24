package com.sangarius.oop.library.persistence.repository.contracts;

import com.sangarius.oop.library.persistence.entity.impl.Category;
import com.sangarius.oop.library.persistence.repository.Repository;

import java.util.Set;

public interface CategoryRepository extends Repository<Category> {

    Set<Category> findAllByName(String categoryName);
}