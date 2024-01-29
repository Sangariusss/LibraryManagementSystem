package com.sangarius.oop.library.persistence.repository;

import com.sangarius.oop.library.persistence.entity.Entity;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.function.Predicate;

/**
 * The base interface for repositories that manage entities of type {@code E}.
 *
 * @param <E> the type of entity managed by the repository
 */
public interface Repository<E extends Entity> {

    /**
     * Retrieves an entity by its unique identifier.
     *
     * @param id the unique identifier of the entity to retrieve
     * @return an {@code Optional} containing the entity if found, or empty if not found
     */
    Optional<E> findById(UUID id);

    /**
     * Retrieves all entities managed by the repository.
     *
     * @return a set containing all entities managed by the repository
     */
    Set<E> findAll();

    /**
     * Retrieves all entities managed by the repository that match the given filter predicate.
     *
     * @param filter the predicate used to filter entities
     * @return a set containing all entities that match the filter predicate
     */
    Set<E> findAll(Predicate<E> filter);

    /**
     * Adds a new entity to the repository.
     *
     * @param entity the entity to add
     * @return the added entity
     */
    E add(E entity);

    /**
     * Removes the specified entity from the repository.
     *
     * @param entity the entity to remove
     * @return {@code true} if the entity was successfully removed, {@code false} otherwise
     */
    boolean remove(E entity);
}