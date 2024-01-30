package com.sangarius.oop.library.persistence.repository.contracts;

import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.entity.impl.Loan;
import com.sangarius.oop.library.persistence.entity.impl.User;
import com.sangarius.oop.library.persistence.repository.Repository;

import java.time.LocalDate;
import java.util.Set;

/**
 * Repository interface for managing loans.
 */
public interface LoanRepository extends Repository<Loan> {

    /**
     * Finds loans by the borrower.
     *
     * @param borrower The borrower.
     * @return A set of loans associated with the specified borrower.
     */
    Set<Loan> findAllByBorrower(User borrower);

    /**
     * Finds loans by the book.
     *
     * @param book The book.
     * @return A set of loans associated with the specified book.
     */
    Set<Loan> findAllByBook(Book book);

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