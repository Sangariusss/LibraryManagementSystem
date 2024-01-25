package com.sangarius.oop.library.service;

import com.sangarius.oop.library.persistence.entity.impl.Loan;
import com.sangarius.oop.library.persistence.repository.RepositoryFactory;
import com.sangarius.oop.library.persistence.repository.contracts.LoanRepository;

import java.util.Set;

public class LoanRepositoryService {

    private final LoanRepository loanRepository;

    public LoanRepositoryService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public static LoanRepositoryService createLoanRepositoryService(
        RepositoryFactory repositoryFactory) {
        LoanRepository loanRepository = repositoryFactory.getLoanRepository();
        return new LoanRepositoryService(loanRepository);
    }

    public void processLoansAndCommit(Set<Loan> loans) {
        for (Loan loan : loans) {
            loanRepository.add(loan);
        }

        printAllLoans();
    }

    private void printAllLoans() {
        loanRepository.findAll().forEach(System.out::println);
    }
}