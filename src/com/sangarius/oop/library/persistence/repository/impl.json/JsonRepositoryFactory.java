package com.sangarius.oop.library.persistence.repository.impl.json;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.sangarius.oop.library.persistence.entity.Entity;
import com.sangarius.oop.library.persistence.exception.JsonFileIOException;
import com.sangarius.oop.library.persistence.repository.RepositoryFactory;
import com.sangarius.oop.library.persistence.repository.contracts.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Set;

/**
 * Implementation of the UnitOfWork pattern and a factory with singleton.
 * This factory provides methods to access various repository implementations for managing entities in a JSON-based data storage.
 */
public class JsonRepositoryFactory extends RepositoryFactory {

    private final Gson gson;
    private BookJsonRepositoryImpl bookJsonRepositoryImpl;
    private CategoryJsonRepositoryImpl categoryJsonRepositoryImpl;
    private LibraryJsonRepositoryImpl libraryJsonRepositoryImpl;
    private LoanJsonRepositoryImpl loanJsonRepositoryImpl;
    private ReviewJsonRepositoryImpl reviewJsonRepositoryImpl;
    private UserJsonRepositoryImpl userJsonRepositoryImpl;

    /**
     * Constructs a new instance of {@code JsonRepositoryFactory}.
     * Initializes Gson with custom serializers and deserializers for LocalDateTime and LocalDate types.
     * Initializes repository implementations for each entity type.
     */
    private JsonRepositoryFactory() {
        GsonBuilder gsonBuilder = new GsonBuilder();

        // Register custom serializers and deserializers for LocalDateTime type
        gsonBuilder.registerTypeAdapter(LocalDateTime.class,
            (JsonSerializer<LocalDateTime>) (localDate, srcType, context) ->
                new JsonPrimitive(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm").format(localDate)));
        gsonBuilder.registerTypeAdapter(LocalDateTime.class,
            (JsonDeserializer<LocalDateTime>) (json, typeOfT, context) ->
                LocalDateTime.parse(json.getAsString(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
                        .withLocale(Locale.of("uk", "UA"))));

        // Register custom serializers and deserializers for LocalDate type
        gsonBuilder.registerTypeAdapter(LocalDate.class,
            (JsonSerializer<LocalDate>) (localDate, srcType, context) ->
                new JsonPrimitive(
                    DateTimeFormatter.ofPattern("dd-MM-yyyy").format(localDate)));
        gsonBuilder.registerTypeAdapter(LocalDate.class,
            (JsonDeserializer<LocalDate>) (json, typeOfT, context) ->
                LocalDate.parse(json.getAsString(),
                    DateTimeFormatter.ofPattern("dd-MM-yyyy")
                        .withLocale(Locale.of("uk", "UA"))));

        gson = gsonBuilder.setPrettyPrinting().create();

        // Initialize repository implementations for each entity type
        bookJsonRepositoryImpl = new BookJsonRepositoryImpl(gson);
        categoryJsonRepositoryImpl = new CategoryJsonRepositoryImpl(gson);
        libraryJsonRepositoryImpl = new LibraryJsonRepositoryImpl(gson);
        loanJsonRepositoryImpl = new LoanJsonRepositoryImpl(gson);
        reviewJsonRepositoryImpl = new ReviewJsonRepositoryImpl(gson);
        userJsonRepositoryImpl = new UserJsonRepositoryImpl(gson);
    }

    /**
     * Retrieves the singleton instance of {@code JsonRepositoryFactory}.
     *
     * @return the singleton instance of {@code JsonRepositoryFactory}
     */
    public static JsonRepositoryFactory getInstance() {
        return InstanceHolder.INSTANCE;
    }

    // Override methods to provide access to repository implementations

    /**
     * Retrieves the repository implementation for managing books.
     *
     * @return the repository implementation for managing books
     */
    @Override
    public BookRepository getBookRepository() {
        return bookJsonRepositoryImpl;
    }

    /**
     * Retrieves the repository implementation for managing categories.
     *
     * @return the repository implementation for managing categories
     */
    @Override
    public CategoryRepository getCategoryRepository() {
        return categoryJsonRepositoryImpl;
    }

    /**
     * Retrieves the repository implementation for managing libraries.
     *
     * @return the repository implementation for managing libraries
     */
    @Override
    public LibraryRepository getLibraryRepository() {
        return libraryJsonRepositoryImpl;
    }

    /**
     * Retrieves the repository implementation for managing loans.
     *
     * @return the repository implementation for managing loans
     */
    @Override
    public LoanRepository getLoanRepository() {
        return loanJsonRepositoryImpl;
    }

    /**
     * Retrieves the repository implementation for managing reviews.
     *
     * @return the repository implementation for managing reviews
     */
    @Override
    public ReviewRepository getReviewRepository() {
        return reviewJsonRepositoryImpl;
    }

    /**
     * Retrieves the repository implementation for managing users.
     *
     * @return the repository implementation for managing users
     */
    @Override
    public UserRepository getUserRepository() {
        return userJsonRepositoryImpl;
    }

    /**
     * Commits changes made to all repositories by serializing entities to their corresponding JSON files.
     * Clears the content of each file before saving the updated data.
     * Throws a {@code JsonFileIOException} if an error occurs during file writing.
     */
    public void commit() {
        serializeEntities(bookJsonRepositoryImpl.getPath(), bookJsonRepositoryImpl.findAll());
        serializeEntities(categoryJsonRepositoryImpl.getPath(), categoryJsonRepositoryImpl.findAll());
        serializeEntities(libraryJsonRepositoryImpl.getPath(), libraryJsonRepositoryImpl.findAll());
        serializeEntities(loanJsonRepositoryImpl.getPath(), loanJsonRepositoryImpl.findAll());
        serializeEntities(reviewJsonRepositoryImpl.getPath(), reviewJsonRepositoryImpl.findAll());
        serializeEntities(userJsonRepositoryImpl.getPath(), userJsonRepositoryImpl.findAll());
    }

    // Serialize entities to JSON and write to corresponding files

    /**
     * Serializes the collection of entities to JSON format and writes to the specified file.
     * Clears the content of the file before writing the updated data.
     * Throws a {@code JsonFileIOException} if an error occurs during file writing.
     *
     * @param path     the path to the file
     * @param entities the collection of entities to serialize and save
     * @param <E>      the type of entities
     */
    private <E extends Entity> void serializeEntities(Path path, Set<E> entities) {
        try (FileWriter writer = new FileWriter(path.toFile())) {
            // Clear the file content before saving!
            writer.write("");
            // Convert the collection of entities to JSON and write to the file
            gson.toJson(entities, writer);

        } catch (IOException e) {
            throw new JsonFileIOException("Failed to save data to the json file. Details: %s"
                .formatted(e.getMessage()));
        }
    }

    // Singleton holder for lazy initialization

    /**
     * Holder for lazy initialization of the singleton instance of {@code JsonRepositoryFactory}.
     */
    private static class InstanceHolder {

        /**
         * The singleton instance of {@code JsonRepositoryFactory}.
         */
        public static final JsonRepositoryFactory INSTANCE = new JsonRepositoryFactory();
    }
}
