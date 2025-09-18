package edu.ucalgary.oop;

import java.util.regex.Pattern;

public class ReliefService {
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private String dateOfInquiry;
    private String infoProvided;
    private Location lastKnownLocation;

    private static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";

    public ReliefService(Inquirer inquirer, DisasterVictim missingPerson, String dateOfInquiry, String infoProvided, Location lastKnownLocation) {
        this.setInquirer(inquirer);
        this.setMissingPerson(missingPerson);
        this.setDateOfInquiry(dateOfInquiry);
        this.setInfoProvided(infoProvided);
        this.setLastKnownLocation(lastKnownLocation);
    }

    // ---------------- Getters ----------------
    public Inquirer getInquirer() {
        return this.inquirer;
    }

    public DisasterVictim getMissingPerson() {
        return this.missingPerson;
    }

    public String getDateOfInquiry() {
        return this.dateOfInquiry;
    }

    public String getInfoProvided() {
        return this.infoProvided;
    }

    public Location getLastKnownLocation() {
        return this.lastKnownLocation;
    }

    // ---------------- Setters ----------------
    public void setInquirer(Inquirer inquirer) {
        if (inquirer == null) {
            throw new IllegalArgumentException("Inquirer cannot be null.");
        }
        this.inquirer = inquirer;
    }

    public void setMissingPerson(DisasterVictim missingPerson) {
        if (missingPerson == null) {
            throw new IllegalArgumentException("Missing person cannot be null.");
        }
        this.missingPerson = missingPerson;
    }

    public void setDateOfInquiry(String dateOfInquiry) {
        if (dateOfInquiry == null || !Pattern.matches(DATE_PATTERN, dateOfInquiry)) {
            throw new IllegalArgumentException("Date must be in yyyy-MM-dd format.");
        }
        this.dateOfInquiry = dateOfInquiry;
    }

    public void setInfoProvided(String infoProvided) {
        if (infoProvided == null || infoProvided.trim().isEmpty()) {
            throw new IllegalArgumentException("Info provided cannot be null or empty.");
        }
        this.infoProvided = infoProvided;
    }

    public void setLastKnownLocation(Location lastKnownLocation) {
        if (lastKnownLocation == null) {
            throw new IllegalArgumentException("Location cannot be null.");
        }
        this.lastKnownLocation = lastKnownLocation;
    }

    // ---------------- Other Methods ----------------
    public String getLogDetails() {
        return "Inquirer: " + inquirer.getFirstName() + " " + inquirer.getLastName()
             + ", Missing Person: " + missingPerson.getFirstName() + " " + missingPerson.getLastName()
             + ", Date of Inquiry: " + dateOfInquiry
             + ", Info Provided: " + infoProvided
             + ", Last Known Location: " + lastKnownLocation.getName();
    }
}
