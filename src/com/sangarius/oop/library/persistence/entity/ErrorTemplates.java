package com.sangarius.oop.library.persistence.entity;

/**
 * Enumeration of error templates for validation messages.
 * Each template corresponds to a specific validation error type.
 */
public enum ErrorTemplates {

    REQUIRED("Field %s is required."),
    MIN_LENGTH("Field %s cannot be less than %d characters."),
    MAX_LENGTH("Field %s cannot exceed %d characters."),
    YEAR_RANGE("Field %s must be a 4-digit number."),
    RATING_RANGE("Field %s must be a number between 1 and 5."),
    ONLY_LATIN("Field %s allows only Latin characters and _."),
    PASSWORD("Field %s requires at least one uppercase letter, one lowercase letter, and one digit.");

    private final String template;

    /**
     * Constructs an error template with the given format.
     *
     * @param template The format for the error message.
     */
    ErrorTemplates(String template) {
        this.template = template;
    }

    /**
     * Gets the template format for the error message.
     *
     * @return The template format.
     */
    public String getTemplate() {
        return template;
    }
}
