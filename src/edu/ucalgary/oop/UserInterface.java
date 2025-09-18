package edu.ucalgary.oop;

import java.util.List;
import java.util.Scanner;

public class UserInterface {
    private Scanner scanner;
    private boolean isRunning;
    private LanguageManager languageManager;
    private DatabaseManager db;

    // Main constructor for CLI use
    public UserInterface() {
        this.languageManager = new LanguageManager("en-CA");
        this.languageManager.loadLanguageFile();
        this.scanner = new Scanner(System.in);
        this.isRunning = true;
        this.db = new DatabaseManager(
            "jdbc:postgresql://localhost:5432/ensf380project",
            "oop",
            "ucalgary"
        );
    }

    // Test constructor with custom Scanner input
    public UserInterface(Scanner testScanner) {
        this.languageManager = new LanguageManager("en-CA");
        this.languageManager.loadLanguageFile();
        this.scanner = testScanner;
        this.isRunning = true;
        this.db = new DatabaseManager(
            "jdbc:postgresql://localhost:5432/ensf380project",
            "oop",
            "ucalgary"
        );
    }

    public void displayMenu() {
        System.out.println("===== " + languageManager.getTranslation("mainMenu") + " =====");
        System.out.println("1. " + languageManager.getTranslation("createInquiry"));
        System.out.println("2. " + languageManager.getTranslation("viewVictims"));
        System.out.println("3. " + languageManager.getTranslation("manageSupplies"));
        System.out.println("4. " + languageManager.getTranslation("exitOption"));
        System.out.print(languageManager.getTranslation("promptChoice") + ": ");
    }

    public String getUserInput() {
        return scanner.nextLine();
    }

    public void processSelection(String input) {
        switch (input) {
            case "1":
                handleCreateInquiry();
                break;
            case "2":
                handleViewVictims();
                break;
            case "3":
                handleManageSupplies();
                break;
            case "4":
            case "exit":
                exit();
                break;
            default:
                System.out.println(languageManager.getTranslation("invalidChoice"));
                System.out.println("Please enter a valid option (1-4):");
        }
    }

    private void handleCreateInquiry() {
        try {
            System.out.print("Enter victim ID: ");
            int victimId = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter inquiry message: ");
            String message = scanner.nextLine();

            boolean success = db.saveInquiry(victimId, message);
            if (success) {
                System.out.println("Inquiry saved successfully.");
            } else {
                System.out.println("Failed to save inquiry.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid victim ID.");
        } catch (Exception e) {
            System.out.println("Error saving inquiry.");
            ErrorLogger.logError("Error in handleCreateInquiry: " + e.getMessage());
        }
    }

    private void handleViewVictims() {
        List<String> victims = db.loadVictimNames();
        if (victims.isEmpty()) {
            System.out.println("No victims found.");
        } else {
            System.out.println("Viewing disaster victims...");
            for (String name : victims) {
                System.out.println("- " + name);
            }
        }
    }

    private void handleManageSupplies() {
        try {
            System.out.println("1. View Supplies");
            System.out.println("2. Add/Update Supply");
            System.out.print("Choose an option: ");
            String subChoice = scanner.nextLine();

            if (subChoice.equals("1")) {
                List<String> supplies = db.getAllSupplies();
                if (supplies.isEmpty()) {
                    System.out.println("No supplies found.");
                } else {
                    for (String s : supplies) {
                        System.out.println(s);
                    }
                }
            } else if (subChoice.equals("2")) {
                System.out.print("Enter supply ID: ");
                int id = Integer.parseInt(scanner.nextLine());

                System.out.print("Enter supply type (e.g., Water, Blanket): ");
                String type = scanner.nextLine();

                System.out.print("Enter quantity: ");
                int qty = Integer.parseInt(scanner.nextLine());

                db.saveSupply(id, type, qty);
                System.out.println("Supply saved/updated.");
            } else {
                System.out.println("Invalid option.");
            }
        } catch (Exception e) {
            System.out.println("Error managing supplies.");
            ErrorLogger.logError("Error in manage supplies: " + e.getMessage());
        }
    }

    public void exit() {
        System.out.println(languageManager.getTranslation("goodbye"));
        isRunning = false;
        db.closeConnection();
    }

    public boolean isRunning() {
        return isRunning;
    }
}
