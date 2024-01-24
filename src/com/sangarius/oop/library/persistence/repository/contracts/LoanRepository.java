package com.sangarius.oop.library.persistence.repository.contracts;

import com.sangarius.oop.library.persistence.entity.impl.Loan;
import com.sangarius.oop.library.persistence.repository.Repository;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

/**
 * Repository interface for managing loans.
 */
public interface LoanRepository extends Repository<Loan> {

    /**
     * Finds loans by the borrower's ID.
     *
     * @param borrowerId The ID of the borrower.
     * @return A set of loans associated with the specified borrower.
     */
    Set<Loan> findAllByBorrowerId(int borrowerId);

    /**
     * Finds loans by the book's ID.
     *
     * @param bookId The ID of the book.
     * @return A set of loans associated with the specified book.
     */
    Set<Loan> findAllByBookId(UUID bookId);

    /**
     * Finds loans by the loan date.
     *
     * @param loanDate The date when the loans were made.
     * @return A set of loans made on the specified date.
     */
    Set<Loan> findAllByLoanDate(LocalDate loanDate);

    /**
     * Finds loans by the due date.
     *
     * @param dueDate The due date for returning the books.
     * @return A set of loans with the specified due date.
     */
    Set<Loan> findAllByDueDate(LocalDate dueDate);
}