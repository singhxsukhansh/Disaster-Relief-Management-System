package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class MedicalRecord {
    private Location location;
    private String treatmentDetails;
    private String dateOfTreatment;

    private static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";

    public MedicalRecord(Location location, String treatmentDetails, String dateOfTreatment) {
        setLocation(location);
        setTreatmentDetails(treatmentDetails);
        setDateOfTreatment(dateOfTreatment);
    }

    // --------------- Getters ---------------
    public Location getLocation() {
        return this.location;
    }

    public String getTreatmentDetails() {
        return this.treatmentDetails;
    }

    public String getDateOfTreatment() {
        return this.dateOfTreatment;
    }

    // --------------- Setters ---------------
    public void setLocation(Location location) {
        if (location == null) {
            throw new IllegalArgumentException("Location cannot be null.");
        }
        this.location = location;
    }

    public void setTreatmentDetails(String treatmentDetails) {
        if (treatmentDetails == null || treatmentDetails.trim().isEmpty()) {
            throw new IllegalArgumentException("Treatment details cannot be null or empty.");
        }
        this.treatmentDetails = treatmentDetails;
    }

    public void setDateOfTreatment(String dateOfTreatment) {
        if (dateOfTreatment == null || !Pattern.matches(DATE_PATTERN, dateOfTreatment)) {
            throw new IllegalArgumentException("Date must be in yyyy-MM-dd format.");
        }
        try {
            LocalDate parsedDate = LocalDate.parse(dateOfTreatment, DateTimeFormatter.ISO_LOCAL_DATE);
            if (parsedDate.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Date cannot be in the future.");
            }
            this.dateOfTreatment = dateOfTreatment;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format.");
        }
    }
}
