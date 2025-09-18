package edu.ucalgary.oop;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class Inquiry {
    private int inquiryID;
    private String inquiryDate;
    private String infoProvided;
    private Inquirer inquirer;
    private DisasterVictim victim;

    private static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";

    public Inquiry(int inquiryID, String inquiryDate, String infoProvided, Inquirer inquirer, DisasterVictim victim) {
        if (inquirer == null) {
            throw new IllegalArgumentException("Inquirer cannot be null.");
        }
        if (victim == null) {
            throw new IllegalArgumentException("Disaster victim cannot be null.");
        }
        if (infoProvided == null || infoProvided.trim().isEmpty()) {
            throw new IllegalArgumentException("Info provided cannot be null, empty or whitespace.");
        }
        if (!Pattern.matches(DATE_PATTERN, inquiryDate)) {
            throw new IllegalArgumentException("Inquiry date must be in yyyy-MM-dd format.");
        }

        this.inquiryID = inquiryID;
        this.inquiryDate = inquiryDate;
        this.infoProvided = infoProvided;
        this.inquirer = inquirer;
        this.victim = victim;
    }
    
    public Inquiry(DisasterVictim victim, String infoProvided) {
        if (victim == null) {
            throw new IllegalArgumentException("Disaster victim cannot be null.");
        }
        if (infoProvided == null || infoProvided.trim().isEmpty()) {
            throw new IllegalArgumentException("Info provided cannot be null, empty or whitespace.");
        }

        this.victim = victim;
        this.infoProvided = infoProvided;
        this.inquiryDate = LocalDate.now().toString(); // Set current date in yyyy-MM-dd
        this.inquirer = new Inquirer(1, "Default Inquirer");  // Dummy inquirer, override if needed
    }

    public int getInquiryID() {
        return this.inquiryID;
    }

    public String getInquiryDate() {
        return this.inquiryDate;
    }

    public String getInfoProvided() {
        return this.infoProvided;
    }

    public Inquirer getInquirer() {
        return this.inquirer;
    }

    public DisasterVictim getVictim() {
        return this.victim;
    }
}
