package com.sangarius.oop.library.persistence.repository.impl.json;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sangarius.oop.library.persistence.entity.impl.Loan;
import com.sangarius.oop.library.persistence.repository.contracts.LoanRepository;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * JSON implementation of the Loan repository.
 */
public class LoanJsonRepositoryImpl extends GenericJsonRepository<Loan> implements LoanRepository {

    /**
     * Constructs a new instance of {@code LoanJsonRepositoryImpl}.
     *
     * @param gson The Gson instance for JSON serialization/deserialization.
     */
    public LoanJsonRepositoryImpl(Gson gson) {
        super(gson, JsonPathFactory.LOANS.getPath(), new TypeToken<Set<Loan>>() {}.getType());
    }

    /**
     * Finds all loans by borrower ID.
     *
     * @param borrowerId The ID of the borrower.
     * @return A set of loans associated with the specified borrower.
     */
    @Override
    public Set<Loan> findAllByBorrowerId(int borrowerId) {
        return entities.stream()
            .filter(loan -> borrowerId == loan.getBorrowerId().hashCode())
            .collect(Collectors.toSet());
    }

    /**
     * Finds all loans by loan date.
     *
     * @param loanDate The date when the loans were made.
     * @return A set of loans made on the specified date.
     */
    @Override
    public Set<Loan> findAllByLoanDate(LocalDate loanDate) {
        return entities.stream()
            .filter(loan -> loanDate.equals(loan.getLoanDate()))
            .collect(Collectors.toSet());
    }

    /**
     * Finds all loans by due date.
     *
     * @param dueDate The due date for returning the books.
     * @return A set of loans with the specified due date.
     */
    @Override
    public Set<Loan> findAllByDueDate(LocalDate dueDate) {
        return entities.stream()
            .filter(loan -> dueDate.equals(loan.getDueDate()))
            .collect(Collectors.toSet());
    }

    /**
     * Finds all loans associated with a specific book identified by its unique ID.
     *
     * @param bookId The unique identifier of the book.
     * @return A set of loans associated with the specified book ID.
     */
    @Override
    public Set<Loan> findAllByBookId(UUID bookId) {
        return entities.stream()
            .filter(loan -> bookId.equals(loan.getBorrowedBook().getId()))
            .collect(Collectors.toSet());
    }
}