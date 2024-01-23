package com.sangarius.oop.library.persistence.entity.impl;

import java.time.LocalDate;

/**
 * Represents a loan of a book in the library.
 */
public class Loan {
    private int id;
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
    public Loan(int id, LocalDate loanDate, LocalDate dueDate, int borrowerId) {
        this.id = id;
        this.loanDate = loanDate;
        this.dueDate = dueDate;
        this.borrowerId = borrowerId;
    }

    // Getters and setters for class fields

    /**
     * Gets the ID of the loan.
     *
     * @return The ID of the loan.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the loan.
     *
     * @param id The new ID of the loan.
     */
    public void setId(int id) {
        this.id = id;
    }

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
    }
}
