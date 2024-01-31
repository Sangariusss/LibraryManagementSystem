package com.sangarius.oop.library.appui;

import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.sangarius.oop.library.persistence.entity.impl.Book;
import com.sangarius.oop.library.persistence.entity.impl.Category;
import com.sangarius.oop.library.persistence.repository.RepositoryFactory;
import com.sangarius.oop.library.service.BookRepositoryService;
import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.InputResult;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.builder.ListPromptBuilder;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import java.util.Set;
import java.util.UUID;
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
            .newItem("EDIT_BOOK").text("Edit a book").add()
            .newItem("DISPLAY_BOOKS").text("Display the list of books").add()
            .newItem("EXIT").text("Exit").add()
            .addPrompt();

        // Get the user's choice result
        var result = prompt.prompt(promptBuilder.build());
        ListResult resultItem = (ListResult) result.get("library-menu");
        String selectedItem = resultItem.getSelectedId();

        // Handle the user's choice
        switch (selectedItem) {
            case "ADD_BOOK" ->
                // Call the method to add a book
                addBook();
            case "SEARCH_BOOK" ->
                // Call the method to search for a book
                searchBook();
            case "EDIT_BOOK" ->
                // Call the method to edit a book
                editBook();
            case "DISPLAY_BOOKS" ->
                // Call the method to display the list of books
                displayBooks();
            case "EXIT" ->
                // Call the method to exit the application
                System.exit(0);
            default ->
                // Perform actions in case of an unknown choice
                System.out.println("Unknown choice. Please try again.");
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

        // Prompt the user for options
        var selectedItemResult = promptBuilder.createListPrompt()
            .name("add-menu")
            .message("Add menu:")
            .newItem("ADD").text("Add a new book").add()
            .newItem("BACK").text("Back to main menu").add()
            .addPrompt();

        var result = prompt.prompt(selectedItemResult.build());
        String selectedItem = ((ListResult) result.get("add-menu")).getSelectedId();

        // Handle the user's choice
        switch (selectedItem) {
            case "ADD" -> addNewBook();
            case "BACK" -> render();
            default -> System.out.println("Unknown option.");
        }
    }

    /**
     * Adds a new book to the library.
     *
     * @throws IOException if an I/O error occurs.
     */
    private void addNewBook() throws IOException {
        // Create a JSON repository factory
        RepositoryFactory jsonRepositoryFactory = RepositoryFactory
            .getRepositoryFactory(RepositoryFactory.JSON);

        // Create a console prompt for user interaction
        ConsolePrompt prompt = new ConsolePrompt();
        PromptBuilder promptBuilder = prompt.getPromptBuilder();

        // Prompt the user for book details
        promptBuilder.createInputPrompt()
            .name("title")
            .message("Enter the title of the book: ")
            .addPrompt();
        promptBuilder.createInputPrompt()
            .name("author")
            .message("Enter the author of the book: ")
            .addPrompt();
        promptBuilder.createInputPrompt()
            .name("category")
            .message("Enter the category of the book: ")
            .addPrompt();
        promptBuilder.createInputPrompt()
            .name("year")
            .message("Enter the year the book was published: ")
            .addPrompt();

        // Combine all prompts into a single prompt sequence
        var allPrompts = promptBuilder.build();

        // Prompt the user for input using all prompts
        var results = prompt.prompt(allPrompts);

        // Extract user input
        String title = ((InputResult)results.get("title")).getInput().trim();
        String author = ((InputResult)results.get("author")).getInput().trim();
        String categoryName = ((InputResult)results.get("category")).getInput().trim();
        String yearInput = ((InputResult)results.get("year")).getInput().trim();

        // Check if the input is a valid integer
        int yearPublished;
        try {
            yearPublished = Integer.parseInt(yearInput);
        } catch (NumberFormatException e) {
            // Handle the case when input cannot be parsed to an integer
            System.out.println("Invalid input for year published. Please enter a valid integer.");
            return; // Exit the method or handle the error as appropriate
        }

        // Створюємо об'єкт UUID для нової книги
        UUID bookId = UUID.randomUUID();

        // Створюємо об'єкт Category
        Category category = new Category(UUID.randomUUID(), categoryName); // Генеруємо новий UUID для категорії

        // Створюємо новий об'єкт Book з отриманими даними
        Book newBook = new Book(bookId, title, author, category, yearPublished);

        // Створення екземпляру BookRepositoryService з використанням відповідного BookRepository
        BookRepositoryService bookService = new BookRepositoryService(jsonRepositoryFactory.getBookRepository());

        // Додавання нової книги за допомогою BookRepositoryService
        bookService.processBooksAndCommit(Set.of(newBook));

        jsonRepositoryFactory.commit();

        System.out.println("Book added successfully!");
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
     * Edits a specific book in the library.
     */

    private void editBook() {
        try {
            // Print the list of books
            printBooks();

            // Create a console prompt for user interaction
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            // Prompt the user to enter the title of the book to edit
            promptBuilder.createInputPrompt()
                .name("title")
                .message("Enter the title of the book to edit: ")
                .addPrompt()
                .build();

            var titleInput = promptBuilder.build();
            var result = prompt.prompt(titleInput);

            // Get the title entered by the user
            String bookTitle = ((InputResult)result.get("title")).getInput().trim();

            // Read book data from the JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode booksJson = objectMapper.readTree(new File("Data/books.json"));

            // Search for the book by title
            boolean bookFound = false;
            for (JsonNode book : booksJson) {
                String title = book.get("title").asText();
                if (title.equalsIgnoreCase(bookTitle)) {
                    bookFound = true;

                    // Book found, prompt the user to select an attribute to edit
                    var selectedAttributeResult = promptBuilder.createListPrompt()
                        .name("edit-attribute")
                        .message("Choose the attribute to edit:")
                        .newItem("TITLE").text("Edit title").add()
                        .newItem("AUTHOR").text("Edit author").add()
                        .newItem("YEAR").text("Edit publication year").add()
                        .newItem("CATEGORY").text("Edit category").add()
                        .newItem("BACK").text("Back to main menu").add()
                        .addPrompt()
                        .build();

                    // Get the selected attribute
                    var attributeResult = prompt.prompt(selectedAttributeResult);
                    String selectedAttribute = ((ListResult) attributeResult.get("edit-attribute")).getSelectedId();

                    // Handle the selected attribute
                    switch (selectedAttribute) {
                        case "TITLE" -> editTitle(book);
                        case "AUTHOR" -> editAuthor(book);
                        case "YEAR" -> editYear(book);
                        case "CATEGORY" -> editCategory(book);
                        case "BACK" -> render();
                        default -> System.out.println("Unknown option.");
                    }
                    break;
                }
            }

            // If the book is not found, print a message
            if (!bookFound) {
                System.out.println("Book not found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editTitle(JsonNode book) {
        try {
            // Get the current title of the book
            String currentTitle = book.get("title").asText();

            // Prompt the user to enter the new title
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            promptBuilder.createInputPrompt()
                .name("new-title")
                .message("Enter the new title for the book: ")
                .addPrompt()
                .build();

            var titleInput = promptBuilder.build();
            var result = prompt.prompt(titleInput);

            // Get the new title entered by the user
            String newTitle = ((InputResult)result.get("new-title")).getInput().trim();

            // Read the entire book data from the JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File("Data/books.json");
            JsonNode booksJson = objectMapper.readTree(jsonFile);

            // Update the title of the specific book in the JSON data
            if (booksJson.isArray()) {
                ArrayNode updatedBooksJson = objectMapper.createArrayNode();
                for (JsonNode node : booksJson) {
                    ObjectNode mutableBook = (ObjectNode) node;
                    if (mutableBook.get("title").asText().equalsIgnoreCase(currentTitle)) {
                        mutableBook.put("title", newTitle);
                    }
                    updatedBooksJson.add(mutableBook);
                }

                // Write the updated book data back to the JSON file
                objectMapper.writeValue(jsonFile, updatedBooksJson);

                // Format the JSON file for readability
                formatJsonFile(jsonFile);

                System.out.println("Title updated successfully from '" + currentTitle + "' to '" + newTitle + "'.");
            } else {
                System.out.println("Error: Unable to read book data from JSON file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editAuthor(JsonNode book) {
        try {
            // Get the current author of the book
            String currentAuthor = book.get("author").asText();

            // Prompt the user to enter the new author
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            promptBuilder.createInputPrompt()
                .name("new-author")
                .message("Enter the new author for the book: ")
                .addPrompt()
                .build();

            var authorInput = promptBuilder.build();
            var result = prompt.prompt(authorInput);

            // Get the new author entered by the user
            String newAuthor = ((InputResult)result.get("new-author")).getInput().trim();

            // Update the author in the JSON data
            ((ObjectNode) book).put("author", newAuthor);

            // Read the entire book data from the JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File("Data/books.json");
            JsonNode booksJson = objectMapper.readTree(jsonFile);

            // Update the author of the specific book in the JSON data
            if (booksJson.isArray()) {
                ArrayNode updatedBooksJson = objectMapper.createArrayNode();
                for (JsonNode node : booksJson) {
                    ObjectNode mutableBook = (ObjectNode) node;
                    if (mutableBook.get("title").asText().equalsIgnoreCase(book.get("title").asText())) {
                        mutableBook.put("author", newAuthor);
                    }
                    updatedBooksJson.add(mutableBook);
                }

                // Write the updated book data back to the JSON file
                objectMapper.writeValue(jsonFile, updatedBooksJson);

                // Format the JSON file for readability
                formatJsonFile(jsonFile);

                System.out.println("Author updated successfully from '" + currentAuthor + "' to '" + newAuthor + "'.");
            } else {
                System.out.println("Error: Unable to read book data from JSON file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editYear(JsonNode book) {
        try {
            // Get the current year of publication of the book
            int currentYear = book.get("yearPublished").asInt();

            // Prompt the user to enter the new year of publication
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            promptBuilder.createInputPrompt()
                .name("new-year")
                .message("Enter the new year of publication for the book: ")
                .addPrompt()
                .build();

            var yearInput = promptBuilder.build();
            var result = prompt.prompt(yearInput);

            // Get the new year entered by the user
            String newYearInput = ((InputResult)result.get("new-year")).getInput().trim();

            // Check if the input is a valid integer
            int newYear;
            try {
                newYear = Integer.parseInt(newYearInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for year. Please enter a valid integer.");
                return;
            }

            // Update the year of publication in the JSON data
            ((ObjectNode) book).put("yearPublished", newYear);

            // Read the entire book data from the JSON file
            ObjectMapper objectMapper = new ObjectMapper();
            File jsonFile = new File("Data/books.json");
            JsonNode booksJson = objectMapper.readTree(jsonFile);

            // Update the year of publication of the specific book in the JSON data
            if (booksJson.isArray()) {
                ArrayNode updatedBooksJson = objectMapper.createArrayNode();
                for (JsonNode node : booksJson) {
                    ObjectNode mutableBook = (ObjectNode) node;
                    if (mutableBook.get("title").asText().equalsIgnoreCase(book.get("title").asText())) {
                        mutableBook.put("yearPublished", newYear);
                    }
                    updatedBooksJson.add(mutableBook);
                }

                // Write the updated book data back to the JSON file
                objectMapper.writeValue(jsonFile, updatedBooksJson);

                // Format the JSON file for readability
                formatJsonFile(jsonFile);

                System.out.println("Year of publication updated successfully from '" + currentYear + "' to '" + newYear + "'.");
            } else {
                System.out.println("Error: Unable to read book data from JSON file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void editCategory(JsonNode book) {
        try {
            // Get the current category of the book
            String currentCategory = book.get("category").get("name").asText();

            // Prompt the user to enter the new category
            ConsolePrompt prompt = new ConsolePrompt();
            PromptBuilder promptBuilder = prompt.getPromptBuilder();

            promptBuilder.createInputPrompt()
                .name("new-category")
                .message("Enter the new category for the book: ")
                .addPrompt()
                .build();

            var categoryInput = promptBuilder.build();
            var result = prompt.prompt(categoryInput);

            // Get the new category entered by the user
            String newCategory = ((InputResult)result.get("new-category")).getInput().trim();

            // Convert the book to a mutable ObjectNode to allow modifications
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode mutableBook = (ObjectNode) book.deepCopy();

            // Update the category in the mutable book data
            ((ObjectNode) mutableBook.get("category")).set("name", new TextNode(newCategory));

            // Read the entire book data from the JSON file
            File jsonFile = new File("Data/books.json");
            JsonNode booksJson = objectMapper.readTree(jsonFile);

            // Update the category of the specific book in the JSON data
            if (booksJson.isArray()) {
                ArrayNode updatedBooksJson = objectMapper.createArrayNode();
                for (JsonNode node : booksJson) {
                    ObjectNode currentBook = (ObjectNode) node.deepCopy();
                    if (currentBook.get("title").asText().equalsIgnoreCase(book.get("title").asText())) {
                        currentBook.set("category", mutableBook.get("category"));
                    }
                    updatedBooksJson.add(currentBook);
                }

                // Write the updated book data back to the JSON file
                objectMapper.writeValue(jsonFile, updatedBooksJson);

                // Format the JSON file for readability
                formatJsonFile(jsonFile);

                System.out.println("Category updated successfully from '" + currentCategory + "' to '" + newCategory + "'.");
            } else {
                System.out.println("Error: Unable to read book data from JSON file.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void formatJsonFile(File jsonFile) {
        try {
            // Read the JSON data from the file
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonData = objectMapper.readTree(jsonFile);

            // Configure the writer with desired formatting
            DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
            printer.indentArraysWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
            printer.indentObjectsWith(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);
            printer.withArrayIndenter(DefaultIndenter.SYSTEM_LINEFEED_INSTANCE);

            // Write the JSON data back to the file with the specified formatting
            objectMapper.writer(printer).writeValue(jsonFile, jsonData);
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