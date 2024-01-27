package com.sangarius.oop.library;

import com.sangarius.oop.library.appui.ConsoleUI;
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

import java.io.IOException;
import java.util.Set;

final class Startup {

    /**
     * Initializes the application.
     */
    public static void init() {
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
        Set<Book> books = BookGenerator.generateBooks(3, users, reviews);
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
        LibraryRepositoryService libraryService = LibraryRepositoryService.createLibraryRepositoryService(jsonRepositoryFactory);
        Set<Library> libraries = LibraryGenerator.generateLibraries(3, users, books, loans);
        libraryService.processLibrariesAndCommit(libraries, users, books, loans);

        // Commit changes for all repositories
        jsonRepositoryFactory.commit();

        // Call the render() method from the ConsoleUI class to display the console interface
        try {
            ConsoleUI consoleUI = new ConsoleUI();
            consoleUI.render();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
