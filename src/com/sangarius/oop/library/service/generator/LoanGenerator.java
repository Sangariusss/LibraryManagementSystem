package com.sangarius.oop.library.service.generator;

import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.entity.impl.Loan;
import com.sangarius.oop.library.persistence.entity.impl.User;
import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Utility class for generating sets of loans.
 */
public class LoanGenerator {
    private static final Faker faker = new Faker();

    /**
     * Generates a set of loans.
     *
     * @param count          The number of loans to generate.
     * @param availableBooks A set of available books from which loans can be generated.
     * @param users          A set of users who can borrow books.
     * @return A set of generated loans.
     */
    public static Set<Loan> generateLoans(int count, Set<Book> availableBooks, Set<User> users) {
        Set<Loan> loans = new HashSet<>();

        for (int i = 0; i < count; i++) {
            UUID loanId = UUID.randomUUID();
            LocalDate loanDate = LocalDate.now().minusDays(faker.number().numberBetween(1, 30));
            LocalDate dueDate = loanDate.plusDays(faker.number().numberBetween(7, 30));
            User borrower = getRandomUser(users);
            Book borrowedBook = getRandomAvailableBook(availableBooks);

            Loan loan = new Loan(loanId, loanDate, dueDate, borrower, borrowedBook);
            loans.add(loan);
        }

        return loans;
    }

    /**
     * Retrieves a random user from the given set of users.
     *
     * @param users A set of users.
     * @return A randomly selected user.
     */
    private static User getRandomUser(Set<User> users) {
        List<User> userList = new ArrayList<>(users);
        int randomIndex = faker.number().numberBetween(0, users.size());
        return userList.get(randomIndex);
    }

    /**
     * Retrieves a random available book from the given set of available books.
     *
     * @param availableBooks A set of available books.
     * @return A randomly selected available book.
     */
    private static Book getRandomAvailableBook(Set<Book> availableBooks) {
        List<Book> availableBookList = new ArrayList<>(availableBooks);
        int randomIndex = faker.number().numberBetween(0, availableBooks.size());
        return availableBookList.get(randomIndex);
    }
}
