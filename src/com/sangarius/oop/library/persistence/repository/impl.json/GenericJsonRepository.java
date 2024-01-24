package com.sangarius.oop.library.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.sangarius.oop.library.persistence.entity.Entity;
import com.sangarius.oop.library.persistence.exception.JsonFileIOException;
import com.sangarius.oop.library.persistence.repository.Repository;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Generic JSON implementation of the Repository interface.
 *
 * @param <E> The type of entities stored in the repository.
 */
public class GenericJsonRepository<E extends Entity> implements Repository<E> {

    protected final Set<E> entities;
    private final Gson gson;
    private final Path path;
    private final Type collectionType;

    /**
     * Constructs a new instance of GenericJsonRepository.
     *
     * @param gson           The Gson instance for JSON serialization/deserialization.
     * @param path           The path to the JSON file.
     * @param collectionType The type of the collection for Gson deserialization.
     */
    public GenericJsonRepository(Gson gson, Path path, Type collectionType) {
        this.gson = gson;
        this.path = path;
        this.collectionType = collectionType;
        entities = new HashSet<>(loadAll());
    }

    @Override
    public Optional<E> findById(UUID id) {
        return entities.stream().filter(e -> e.getId().equals(id)).findFirst();
    }

    @Override
    public Set<E> findAll() {
        return entities;
    }

    @Override
    public Set<E> findAll(Predicate<E> filter) {
        return entities.stream().filter(filter).collect(Collectors.toSet());
    }

    @Override
    public E add(E entity) {
        entities.remove(entity);
        entities.add(entity);
        return entity;
    }

    @Override
    public boolean remove(E entity) {
        return entities.remove(entity);
    }

    public Path getPath() {
        return path;
    }

    private Set<E> loadAll() {
        try {
            fileNotFound();
            var json = Files.readString(path);
            return isValidJson(json) ? gson.fromJson(json, collectionType) : new HashSet<>();
        } catch (IOException e) {
            throw new JsonFileIOException("Error working with the file %s."
                .formatted(path.getFileName()));
        }
    }

    /**
     * Checks the validity of the JSON data format.
     *
     * @param input JSON data as a string.
     * @return The result of the check.
     */
    private boolean isValidJson(String input) {
        try (JsonReader reader = new JsonReader(new StringReader(input))) {
            reader.skipValue();
            return reader.peek() == JsonToken.END_DOCUMENT;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * If the file does not exist, create it.
     *
     * @throws IOException Exception during input/output operations.
     */
    private void fileNotFound() throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }
}