package com.sangarius.oop.library.persistence.entity.impl;

import com.sangarius.oop.library.persistence.entity.Entity;
import com.sangarius.oop.library.persistence.entity.ErrorTemplates;
import com.sangarius.oop.library.persistence.exception.EntityArgumentException;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Represents a loan of a book in the library.
 */
public class Loan extends Entity {

    private LocalDate loanDate;
    private LocalDate dueDate;
    private int borrowerId;

    /**
     * Constructs a new Loan with the specified details.
     *
     * @param id          The ID of the loan.
     * @param loanDate    The date when the loan was made.
     * @param dueDate     The due date for returning the book.
     * @param borrowerId  The ID of the borrower (user).
     */
    public Loan(UUID id, LocalDate loanDate, LocalDate dueDate, int borrowerId) {
        super(id);
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.borrowerId = borrowerId;
        validateLoan();
    }

    // Getters and setters for class fields

    /**
     * Gets the date when the loan was made.
     *
     * @return The date when the loan was made.
     */
    public LocalDate getLoanDate() {
        return loanDate;
    }

    /**
     * Sets the date when the loan was made.
     *
     * @param loanDate The new date when the loan was made.
     */
    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
        validateLoanDate();
    }

    /**
     * Gets the due date for returning the book.
     *
     * @return The due date for returning the book.
     */
    public LocalDate getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date for returning the book.
     *
     * @param dueDate The new due date for returning the book.
     */
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        validateDueDate();
    }

    /**
     * Gets the ID of the borrower (user).
     *
     * @return The ID of the borrower (user).
     */
    public int getBorrowerId() {
        return borrowerId;
    }

    /**
     * Sets the ID of the borrower (user).
     *
     * @param borrowerId The new ID of the borrower (user).
     */
    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
        validateBorrowerId();
    }

    /**
     * Validates the loan entity and populates errors list if validation fails.
     */
    private void validateLoan() {
        validateLoanDate();
        validateDueDate();
        validateBorrowerId();

        if (!this.errors.isEmpty()) {
            throw new EntityArgumentException(errors);
        }
    }

    private void validateLoanDate() {
        final String templateName = "Loan Date";

        if (loanDate == null) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
    }

    private void validateDueDate() {
        final String templateName = "Due Date";

        if (dueDate == null) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
    }

    private void validateBorrowerId() {
        final String templateName = "Borrower ID";

        if (borrowerId <= 0) {
            errors.add(ErrorTemplates.REQUIRED.getTemplate().formatted(templateName));
        }
    }

    /**
     * Compares this loan to another object for equality.
     *
     * @param o The object to compare to.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Loan loan = (Loan) o;
        return Objects.equals(id, loan.id);
    }

    /**
     * Computes a hash code for this loan.
     *
     * @return A hash code value for this loan.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    /**
     * Returns a string representation of the loan.
     *
     * @return A string representation of the loan.
     */
    @Override
    public String toString() {
        return "Loan{" +
            "id=" + id +
            ", loanDate=" + loanDate +
            ", dueDate=" + dueDate +
            ", borrowerId=" + borrowerId +
            '}';
    }
}