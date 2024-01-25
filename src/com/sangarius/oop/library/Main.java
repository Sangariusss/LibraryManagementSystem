package com.sangarius.oop.library;

import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.entity.impl.Category;
import com.sangarius.oop.library.persistence.entity.impl.Library;
import com.sangarius.oop.library.persistence.entity.impl.Review;
import com.sangarius.oop.library.persistence.entity.impl.User;
import com.sangarius.oop.library.persistence.repository.RepositoryFactory;
import com.sangarius.oop.library.service.BookRepositoryService;
import com.sangarius.oop.library.service.CategoryRepositoryService;
import com.sangarius.oop.library.service.LibraryRepositoryService;
import com.sangarius.oop.library.service.LoanRepositoryService;
import com.sangarius.oop.library.service.ReviewRepositoryService;
import com.sangarius.oop.library.service.UserRepositoryService;
import com.sangarius.oop.library.service.generator.BookGenerator;
import com.sangarius.oop.library.service.generator.CategoryGenerator;
import com.sangarius.oop.library.service.generator.LibraryGenerator;
import com.sangarius.oop.library.service.generator.ReviewGenerator;
import com.sangarius.oop.library.service.generator.UserGenerator;
import com.sangarius.oop.library.service.generator.LoanGenerator;
import com.sangarius.oop.library.persistence.entity.impl.Loan;

import java.util.Set;

/**
 * Main class for executing library-related operations.
 */
public class Main {

    /**
     * Main method to demonstrate the functionality of the library application.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        // Create a JSON repository factory
        RepositoryFactory jsonRepositoryFactory = RepositoryFactory
            .getRepositoryFactory(RepositoryFactory.JSON);

        // User-related operations
        UserRepositoryService userService = new UserRepositoryService(jsonRepositoryFactory.getUserRepository());
        Set<User> users = UserGenerator.generateUsers(3);
        userService.processUsersAndCommit(users);

        // Review-related operations
        ReviewRepositoryService reviewService = new ReviewRepositoryService(jsonRepositoryFactory.getReviewRepository());
        Set<Review> reviews = ReviewGenerator.generateReviews(3, users);
        reviewService.processReviewsAndCommit(reviews);

        // Book-related operations
        BookRepositoryService bookService = new BookRepositoryService(jsonRepositoryFactory.getBookRepository());
        Set<Book> books = BookGenerator.generateBooks(3);
        bookService.processBooksAndCommit(books);

        // Loan-related operations
        LoanRepositoryService loanService = new LoanRepositoryService(jsonRepositoryFactory.getLoanRepository());
        Set<Loan> loans = LoanGenerator.generateLoans(3, books, users);
        loanService.processLoansAndCommit(loans);

        // Category-related operations
        CategoryRepositoryService categoryService = new CategoryRepositoryService(jsonRepositoryFactory.getCategoryRepository());
        Set<Category> categories = CategoryGenerator.generateCategories(3);
        categoryService.processCategoriesAndCommit(categories);

        // Library-related operations
        LibraryRepositoryService libraryService = new LibraryRepositoryService(jsonRepositoryFactory.getLibraryRepository());
        Set<Library> libraries = LibraryGenerator.generateLibraries(3);
        libraryService.processLibrariesAndCommit(libraries);


        // Commit changes for all repositories
        jsonRepositoryFactory.commit();
    }
}
