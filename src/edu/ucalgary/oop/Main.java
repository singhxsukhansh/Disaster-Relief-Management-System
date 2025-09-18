package edu.ucalgary.oop;

public class Main {
    public static void main(String[] args) {
        // Initialize the CLI user interface (internally sets up DB connection)
        UserInterface ui = new UserInterface();

        // CLI loop
        while (ui.isRunning()) {
            try {
                ui.displayMenu();
                String input = ui.getUserInput();
                ui.processSelection(input);
            } catch (Exception e) {
                System.out.println("An error occurred. Please try again.");
                ErrorLogger.logError("Main CLI loop error: " + e.getMessage());
            }
        }
    }
}
