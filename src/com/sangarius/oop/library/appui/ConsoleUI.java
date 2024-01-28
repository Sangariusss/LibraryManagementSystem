package com.sangarius.oop.library.appui;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.builder.ListPromptBuilder;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.fusesource.jansi.AnsiConsole;

public class ConsoleUI implements Renderable {

    public static void printWelcome() {
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

        for (int i = 0; i < art.length(); i++) {
            System.out.print(art.charAt(i));
            try {
                Thread.sleep(0, 1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void render() throws IOException {
        // Set up console output with ANSI support
        AnsiConsole.systemInstall();

        // Create an object for interactive actions in the console
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

    public static void addBook() {
        printBooks();
    }

    private void searchBook() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            printBooks();
            // Зчитайте дані про книги з файлу JSON
            JsonNode booksJson = objectMapper.readTree(new File("Data/books.json"));

            // Використайте існуючий об'єкт ConsolePrompt та його PromptBuilder для створення списку опцій
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            // Створіть список опцій для вибору критерію пошуку
            var selectedCriteriaResult = promptBuilder.createListPrompt()
                .name("search-criteria")
                .message("Choose search criteria:")
                .newItem("TITLE").text("Search by title").add()
                .newItem("AUTHOR").text("Search by author").add()
                .newItem("BACK").text("Back to main menu").add()
                .addPrompt();

            // Отримайте вибрану опцію
            var result = prompt.prompt(selectedCriteriaResult.build());
            String selectedCriteria = ((ListResult) result.get("search-criteria")).getSelectedId();

            // Обробіть вибрану опцію
            switch (selectedCriteria) {
                case "TITLE" -> searchByTitle(booksJson);
                case "AUTHOR" -> searchByAuthor(booksJson);
                case "BACK" -> {
                    render();
                    printBooks();
                }
                default -> System.out.println("Unknown search criteria.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void searchByTitle(JsonNode booksJson) {
        try {
            // Створіть список назв книг для відображення в консолі
            List<String> bookTitles = new ArrayList<>();
            for (JsonNode book : booksJson) {
                bookTitles.add(book.get("title").asText());
            }

            // Створіть об'єкт для інтерактивного введення користувача
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            // Створіть список опцій для вибору книги за назвою
            ListPromptBuilder listPromptBuilder = promptBuilder.createListPrompt()
                .name("book-titles")
                .message("Choose a book:");

            // Додайте кожну назву книги до списку опцій
            bookTitles.forEach(title -> listPromptBuilder.newItem(title).add());

            // Додайте підказку вибору книги та збудуйте підказку
            var selectedTitleResult = listPromptBuilder.addPrompt().build();

            // Отримайте вибрану назву книги
            var result = prompt.prompt(selectedTitleResult);
            String selectedTitle = ((ListResult) result.get("book-titles")).getSelectedId();

            // Знайдіть книгу за вибраною назвою
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

    private void searchByAuthor(JsonNode booksJson) {
        try {
            // Створіть список авторів книг для відображення в консолі
            List<String> bookAuthors = new ArrayList<>();
            for (JsonNode book : booksJson) {
                bookAuthors.add(book.get("author").asText());
            }

            // Створіть об'єкт для інтерактивного введення користувача
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            // Створіть список опцій для вибору книги за автором
            ListPromptBuilder listPromptBuilder = promptBuilder.createListPrompt()
                .name("book-authors")
                .message("Choose a book by author:");

            // Додайте кожного автора книги до списку опцій
            bookAuthors.forEach(author -> listPromptBuilder.newItem(author).add());

            // Додайте підказку вибору автора книги та збудуйте підказку
            var selectedAuthorResult = listPromptBuilder.addPrompt().build();

            // Отримайте вибраного автора книги
            var result = prompt.prompt(selectedAuthorResult);
            String selectedAuthor = ((ListResult) result.get("book-authors")).getSelectedId();

            // Знайдіть книги за вибраним автором
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

    private void displayBooks() {
        // Create an ObjectMapper object to work with JSON
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            printBooks();;
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

    private static void printBooks() {
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