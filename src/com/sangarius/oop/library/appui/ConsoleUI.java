package com.sangarius.oop.library.appui;

import de.codeshelf.consoleui.prompt.ConsolePrompt;
import de.codeshelf.consoleui.prompt.ListResult;
import de.codeshelf.consoleui.prompt.builder.PromptBuilder;
import org.fusesource.jansi.AnsiConsole;

import java.io.IOException;

public class ConsoleUI implements Renderable {

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
                // addBook();
                break;
            case "SEARCH_BOOK":
                // Call the method to search for a book
                // searchBook();
                break;
            case "DISPLAY_BOOKS":
                // Call the method to display the list of books
                // displayBooks();
                break;
            case "EXIT":
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
}