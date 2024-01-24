package com.sangarius.oop.library.persistence.repository.contracts;

import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.repository.Repository;

import java.util.Set;

public interface BookRepository extends Repository<Book> {

    Set<Book> findAllByCategory(String categoryId);

    Set<Book> findAllByAuthor(String authorId);

    Set<Book> findAllAvailable();
}