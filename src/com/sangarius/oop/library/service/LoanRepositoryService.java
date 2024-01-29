package com.sangarius.oop.library.service;

import com.sangarius.oop.library.persistence.entity.impl.Loan;
import com.sangarius.oop.library.persistence.repository.RepositoryFactory;
import com.sangarius.oop.library.persistence.repository.contracts.LoanRepository;

import java.util.Set;

/**
 * Service class for processing loans and interacting with the loan repository.
 */
public class LoanRepositoryService {

    private final LoanRepository loanRepository;

    /**
     * Constructs a new instance of LoanRepositoryService with the provided loan repository.
     *
     * @param loanRepository The loan repository to be used.
     */
    public LoanRepositoryService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    /**
     * Creates a new instance of LoanRepositoryService using the provided repository factory.
     *
     * @param repositoryFactory The repository factory to create the loan repository.
     * @return A new instance of LoanRepositoryService.
     */
    public static LoanRepositoryService createLoanRepositoryService(
        RepositoryFactory repositoryFactory) {
        LoanRepository loanRepository = repositoryFactory.getLoanRepository();
        return new LoanRepositoryService(loanRepository);
    }

    /**
     * Processes the provided set of loans by adding them to the loan repository.
     *
     * @param loans The set of loans to be processed.
     */
    public void processLoansAndCommit(Set<Loan> loans) {
        for (Loan loan : loans) {
            loanRepository.add(loan);
        }
    }

    // This method is private and does not require JavaDoc
    private void printAllLoans() {
        loanRepository.findAll().forEach(System.out::println);
    }
}