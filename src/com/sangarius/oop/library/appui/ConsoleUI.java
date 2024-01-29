package com.sangarius.oop.library.appui;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.builder.ListPromptBuilder;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import org.fusesource.jansi.AnsiConsole;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ConsoleUI class handles rendering the library application interface in the console.
 */
public class ConsoleUI implements Renderable {

    /**
     * Prints the welcome message with ASCII art.
     */
    public static void printWelcome() {
        // ASCII art for the welcome message
        String art = " _____                                                _____ \n"
            + "( ___ )----------------------------------------------( ___ )\n"
            + " |   |                                                |   | \n"
            + " |   |    ╦ ╦┌─┐┬  ┌─┐┌─┐┌┬┐┌─┐  ┌┬┐┌─┐  ┌┬┐┬ ┬┌─┐    |   | \n"
            + " |   |    ║║║├┤ │  │  │ ││││├┤    │ │ │   │ ├─┤├┤     |   | \n"
            + " |   |    ╚╩╝└─┘┴─┘└─┘└─┘┴ ┴└─┘   ┴ └─┘   ┴ ┴ ┴└─┘    |   | \n"
            + " |   |        ┌┐ ┌─┐┌─┐┬┌─  ┬  ┬┌┐ ┬─┐┌─┐┬─┐┬ ┬       |   | \n"
            + " |   |        ├┴┐│ ││ │├┴┐  │  │├┴┐├┬┘├─┤├┬┘└┬┘       |   | \n"
            + " |   |        └─┘└─┘└─┘┴ ┴  ┴─┘┴└─┘┴└─┴ ┴┴└─ ┴        |   | \n"
            + " |___|                                                |___| \n"
            + "(_____)----------------------------------------------(_____)\n";

        // Print each character of the ASCII art with a slight delay for visual effect
        for (int i = 0; i < art.length(); i++) {
            System.out.print(art.charAt(i));
            try {
                Thread.sleep(0, 1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Renders the library application interface in the console.
     *
     * @throws IOException if an I/O error occurs.
     */
    @Override
    public void render() throws IOException {
        // Set up console output with ANSI support
        AnsiConsole.systemInstall();

        // Create a console prompt for user interaction
        ConsolePrompt prompt = new ConsolePrompt();
        PromptBuilder promptBuilder = prompt.getPromptBuilder();

        // Display welcome message and prompt the user to choose an option from the menu
        promptBuilder.createListPrompt()
            .name("library-menu")
            .message("Welcome to the book library")
            .newItem("ADD_BOOK").text("Add a book").add()
            .newItem("SEARCH_BOOK").text("Search for a book").add()
            .newItem("DISPLAY_BOOKS").text("Display the list of books").add()
            .newItem("EXIT").text("Exit").add()
            .addPrompt();

        // Get the user's choice result
        var result = prompt.prompt(promptBuilder.build());
        ListResult resultItem = (ListResult) result.get("library-menu");
        String selectedItem = resultItem.getSelectedId();

        // Handle the user's choice
        switch (selectedItem) {
            case "ADD_BOOK":
                // Call the method to add a book
                addBook();
                break;
            case "SEARCH_BOOK":
                // Call the method to search for a book
                searchBook();
                break;
            case "DISPLAY_BOOKS":
                // Call the method to display the list of books
                displayBooks();
                break;
            case "EXIT":
                // Call the method to exit the application
                System.exit(0);
                break;
            default:
                // Perform actions in case of an unknown choice
                System.out.println("Unknown choice. Please try again.");
                break;
        }

        // Restore the standard console output
        AnsiConsole.systemUninstall();
    }

    /**
     * Adds a book to the library.
     *
     * @throws IOException if an I/O error occurs.
     */
    private void addBook() throws IOException {
        // Print the list of books
        printBooks();

        // Create a console prompt for user interaction
        ConsolePrompt prompt = new ConsolePrompt();
        PromptBuilder promptBuilder = prompt.getPromptBuilder();

        // Prompt the user to go back to the main menu
        var selectedItemResult = promptBuilder.createListPrompt()
            .newItem("BACK").text("Back to main menu").add()
            .addPrompt();

        var result = prompt.prompt(selectedItemResult.build());
        String selectedItem = ((ListResult) result.get("search-criteria")).getSelectedId();

        // Handle the user's choice
        switch (selectedItem) {
            case "BACK" -> render();
            default -> System.out.println("Unknown search criteria.");
        }
    }

    /**
     * Searches for a book in the library.
     */
    private void searchBook() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Print the list of books
            printBooks();
            // Read book data from the JSON file
            JsonNode booksJson = objectMapper.readTree(new File("Data/books.json"));

            // Create a console prompt for user interaction
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            // Create a list of options for choosing search criteria
            var selectedCriteriaResult = promptBuilder.createListPrompt()
                .name("search-criteria")
                .message("Choose search criteria:")
                .newItem("TITLE").text("Search by title").add()
                .newItem("AUTHOR").text("Search by author").add()
                .newItem("BACK").text("Back to main menu").add()
                .addPrompt();

            // Get the selected option
            var result = prompt.prompt(selectedCriteriaResult.build());
            String selectedCriteria = ((ListResult) result.get("search-criteria")).getSelectedId();

            // Handle the selected option
            switch (selectedCriteria) {
                case "TITLE" -> searchByTitle(booksJson);
                case "AUTHOR" -> searchByAuthor(booksJson);
                case "BACK" -> render();
                default -> System.out.println("Unknown search criteria.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches for a book by title.
     *
     * @param booksJson the JSON node containing book data.
     */
    private void searchByTitle(JsonNode booksJson) {
        try {
            // Create a list of book titles for display
            List<String> bookTitles = new ArrayList<>();
            for (JsonNode book : booksJson) {
                bookTitles.add(book.get("title").asText());
            }

            // Create a console prompt for user interaction
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            // Create a list of options for choosing a book title
            ListPromptBuilder listPromptBuilder = promptBuilder.createListPrompt()
                .name("book-titles")
                .message("Choose a book:");

            // Add each book title to the list of options
            bookTitles.forEach(title -> listPromptBuilder.newItem(title).add());

            // Build the prompt with a selection hint
            var selectedTitleResult = listPromptBuilder.addPrompt().build();

            // Get the selected book title
            var result = prompt.prompt(selectedTitleResult);
            String selectedTitle = ((ListResult) result.get("book-titles")).getSelectedId();

            // Find the book by the selected title
            for (JsonNode book : booksJson) {
                String title = book.get("title").asText();
                if (title.equalsIgnoreCase(selectedTitle)) {
                    System.out.println("Book Found:");
                    System.out.println("Title: " + title);
                    System.out.println("Author: " + book.get("author").asText());
                    searchBook();
                    return;
                }
            }
            System.out.println("Book not found.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Searches for a book by author.
     *
     * @param booksJson the JSON node containing book data.
     */
    private void searchByAuthor(JsonNode booksJson) {
        try {
            // Create a list of book authors for display
            List<String> bookAuthors = new ArrayList<>();
            for (JsonNode book : booksJson) {
                bookAuthors.add(book.get("author").asText());
            }

            // Create a console prompt for user interaction
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            // Create a list of options for choosing a book by author
            ListPromptBuilder listPromptBuilder = promptBuilder.createListPrompt()
                .name("book-authors")
                .message("Choose a book by author:");

            // Add each book author to the list of options
            bookAuthors.forEach(author -> listPromptBuilder.newItem(author).add());

            // Build the prompt with a selection hint
            var selectedAuthorResult = listPromptBuilder.addPrompt().build();

            // Get the selected book author
            var result = prompt.prompt(selectedAuthorResult);
            String selectedAuthor = ((ListResult) result.get("book-authors")).getSelectedId();

            // Find books by the selected author
            boolean found = false;
            for (JsonNode book : booksJson) {
                String author = book.get("author").asText();
                if (author.equalsIgnoreCase(selectedAuthor)) {
                    if (!found) {
                        System.out.println("Books Found by " + selectedAuthor + ":");
                        found = true;
                    }
                    System.out.println("Title: " + book.get("title").asText());
                    searchBook();
                }
            }
            if (!found) {
                System.out.println("No books found by " + selectedAuthor + ".");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the list of books in the library.
     */
    private void displayBooks() {
        // Create an ObjectMapper object to work with JSON
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            printBooks();
            // Load JSON from file into a JsonNode
            JsonNode booksJson = objectMapper.readTree(new File("Data/books.json"));

            // Iterate over all books in the JSON array
            for (JsonNode bookJson : booksJson) {
                // Get book data from the JsonNode
                String title = bookJson.get("title").asText();
                String author = bookJson.get("author").asText();
                int yearPublished = bookJson.get("yearPublished").asInt();
                String categoryName = bookJson.get("category").get("name").asText();

                // Print book data
                System.out.println("Title: " + title);
                System.out.println("Author: " + author);
                System.out.println("Year Published: " + yearPublished);
                System.out.println("Category: " + categoryName);
                System.out.println();
            }
            render();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints ASCII art for the list of books.
     */
    private static void printBooks() {
        // ASCII art for the list of books
        String art = "       .--.                   .---.\n"
            + "   .---|__|           .-.     |~~~|\n"
            + ".--|===|--|_          |_|     |~~~|--.\n"
            + "|  |===|  |'\\     .---!~|  .--|   |--|\n"
            + "|%%|   |  |.'\\    |===| |--|%%|   |  |\n"
            + "|%%|   |  |\\.'\\   |   | |__|  |   |  |\n"
            + "|  |   |  | \\  \\  |===| |==|  |   |  |\n"
            + "|  |   |__|  \\.'\\ |   |_|__|  |~~~|__|\n"
            + "|  |===|--|   \\.'\\|===|~|--|%%|~~~|--|\n"
            + "^--^---'--^    `-'`---^-^--^--^---'--'\n";

        // Print each character of the ASCII art with a slight delay for visual effect
        for (int i = 0; i < art.length(); i++) {
            System.out.print(art.charAt(i));
            try {
                Thread.sleep(0, 1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
