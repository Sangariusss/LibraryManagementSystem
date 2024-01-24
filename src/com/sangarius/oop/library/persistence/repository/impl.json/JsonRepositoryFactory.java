package com.sangarius.oop.library.persistence.repository.impl.json;

import com.sangarius.oop.library.persistence.entity.Entity;
import com.sangarius.oop.library.persistence.exception.JsonFileIOException;
import com.sangarius.oop.library.persistence.repository.RepositoryFactory;
import com.sangarius.oop.library.persistence.repository.contracts.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Set;

/**
 * Implementation of the UnitOfWork pattern and a factory with singleton.
 */
public class JsonRepositoryFactory extends RepositoryFactory {

    private final Gson gson;
    private BookJsonRepositoryImpl bookJsonRepositoryImpl;
    private CategoryJsonRepositoryImpl categoryJsonRepositoryImpl;
    private LibraryJsonRepositoryImpl libraryJsonRepositoryImpl;
    private LoanJsonRepositoryImpl loanJsonRepositoryImpl;
    private ReviewJsonRepositoryImpl reviewJsonRepositoryImpl;
    private UserJsonRepositoryImpl userJsonRepositoryImpl;

    private JsonRepositoryFactory() {
        gson = new GsonBuilder().setPrettyPrinting().create();

        bookJsonRepositoryImpl = new BookJsonRepositoryImpl(gson);
        categoryJsonRepositoryImpl = new CategoryJsonRepositoryImpl(gson);
        libraryJsonRepositoryImpl = new LibraryJsonRepositoryImpl(gson);
        loanJsonRepositoryImpl = new LoanJsonRepositoryImpl(gson);
        reviewJsonRepositoryImpl = new ReviewJsonRepositoryImpl(gson);
        userJsonRepositoryImpl = new UserJsonRepositoryImpl(gson);
    }

    public static JsonRepositoryFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public BookRepository getBookRepository() {
        return bookJsonRepositoryImpl;
    }

    @Override
    public CategoryRepository getCategoryRepository() {
        return categoryJsonRepositoryImpl;
    }

    @Override
    public LibraryRepository getLibraryRepository() {
        return libraryJsonRepositoryImpl;
    }

    @Override
    public LoanRepository getLoanRepository() {
        return loanJsonRepositoryImpl;
    }

    @Override
    public ReviewRepository getReviewRepository() {
        return reviewJsonRepositoryImpl;
    }

    @Override
    public UserRepository getUserRepository() {
        return userJsonRepositoryImpl;
    }

    public void commit() {
        serializeEntities(bookJsonRepositoryImpl.getPath(), bookJsonRepositoryImpl.findAll());
        serializeEntities(categoryJsonRepositoryImpl.getPath(), categoryJsonRepositoryImpl.findAll());
        serializeEntities(libraryJsonRepositoryImpl.getPath(), libraryJsonRepositoryImpl.findAll());
        serializeEntities(loanJsonRepositoryImpl.getPath(), loanJsonRepositoryImpl.findAll());
        serializeEntities(reviewJsonRepositoryImpl.getPath(), reviewJsonRepositoryImpl.findAll());
        serializeEntities(userJsonRepositoryImpl.getPath(), userJsonRepositoryImpl.findAll());
    }

    private <E extends Entity> void serializeEntities(Path path, Set<E> entities) {
        try (FileWriter writer = new FileWriter(path.toFile())) {
            // Clear the file content before saving!
            writer.write("[]");
            // Convert the collection of entities to JSON and write to the file
            gson.toJson(entities, writer);

        } catch (IOException e) {
            throw new JsonFileIOException("Failed to save data to the json file. Details: %s"
                .formatted(e.getMessage()));
        }
    }

    private static class InstanceHolder {
        public static final JsonRepositoryFactory INSTANCE = new JsonRepositoryFactory();
    }
}