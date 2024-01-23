package com.sangarius.oop.library.persistence.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * Abstract base class for entities in the library management system.
 * Provides common functionality and properties for all entities.
 */
public abstract class Entity {

    /**
     * Unique identifier for the entity.
     */
    protected final UUID id;

    /**
     * List of validation errors associated with the entity.
     */
    protected List<String> errors;

    /**
     * Flag indicating whether the entity is considered valid.
     */
    protected boolean isValid;

    /**
     * Constructs a new entity with the given ID.
     *
     * @param id The unique identifier for the entity.
     */
    protected Entity(UUID id) {
        errors = new ArrayList<>();
        this.id = id;
    }

    /**
     * Gets the unique identifier for the entity.
     *
     * @return The entity's ID.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Checks if the entity is considered valid.
     *
     * @return {@code true} if the entity is valid, {@code false} otherwise.
     */
    public boolean isValid() {
        return errors.isEmpty();
    }

    /**
     * Gets the list of validation errors associated with the entity.
     *
     * @return The list of validation errors.
     */
    public List<String> getErrors() {
        return errors;
    }

    /**
     * Checks if two entities are equal based on their IDs.
     *
     * @param o The object to compare with.
     * @return {@code true} if the entities have the same ID, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entity entity = (Entity) o;
        return Objects.equals(id, entity.id);
    }

    /**
     * Generates a hash code for the entity based on its ID.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}