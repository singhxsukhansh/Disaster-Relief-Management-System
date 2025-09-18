package edu.ucalgary.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class DisasterVictim {
    private static int nextSocialID = 1;

    private final int assignedSocialID;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String gender;
    private String entryDate;
    private String comments;
    private FamilyGroup familyGroup;
    private Location location;
    private final List<MedicalRecord> medicalRecords;
    private final List<Supply> personalBelongings;

    private static final List<String> VALID_GENDERS = Arrays.asList(
        "man", "woman", "non-binary person"
    );

    private static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";

    public DisasterVictim(String firstName, String lastName, String dateOfBirth, String gender, String entryDate) {
        validateDateFormat(dateOfBirth);
        validateDateFormat(entryDate);
        validateGender(gender);

        this.assignedSocialID = nextSocialID++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.entryDate = entryDate;
        this.medicalRecords = new ArrayList<>();
        this.personalBelongings = new ArrayList<>();
    }

    // ---------------- Getters ----------------
    public int getAssignedSocialID() {
        return assignedSocialID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public String getEntryDate() {
        return entryDate;
    }

    public String getComments() {
        return comments;
    }

    public FamilyGroup getFamilyGroup() {
        return familyGroup;
    }

    public Location getLocation() {
        return location;
    }

    public List<MedicalRecord> getMedicalRecords() {
        return new ArrayList<>(medicalRecords);
    }

    public List<Supply> getPersonalBelongings() {
        return new ArrayList<>(personalBelongings);
    }

    // ---------------- Setters ----------------
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDateOfBirth(String dateOfBirth) {
        validateDateFormat(dateOfBirth);
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(String gender) {
        validateGender(gender);
        this.gender = gender;
    }

    public void setEntryDate(String entryDate) {
        validateDateFormat(entryDate);
        this.entryDate = entryDate;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setFamilyGroup(FamilyGroup familyGroup) {
        this.familyGroup = familyGroup;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // ---------------- Medical Records ----------------
    public void addMedicalRecord(MedicalRecord record) {
        this.medicalRecords.add(record);
    }

    public void removeMedicalRecord(MedicalRecord record) {
        if (!this.medicalRecords.remove(record)) {
            throw new IllegalArgumentException("MedicalRecord not found.");
        }
    }

    public void setMedicalRecords(MedicalRecord[] records) {
        this.medicalRecords.clear();
        this.medicalRecords.addAll(Arrays.asList(records));
    }

    // ---------------- Personal Belongings ----------------
    public void addPersonalBelonging(Supply supply) {
        this.personalBelongings.add(supply);
    }

    public void removePersonalBelonging(Supply supply) {
        if (!this.personalBelongings.remove(supply)) {
            throw new IllegalArgumentException("Supply not found.");
        }
    }

    public void setPersonalBelongings(Supply[] supplies) {
        this.personalBelongings.clear();
        this.personalBelongings.addAll(Arrays.asList(supplies));
    }

    // ---------------- Inquiry ----------------
    public void createInquiry(String message) {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Inquiry message cannot be null or empty.");
        }
        new Inquiry(this, message);
    }

    // ---------------- Validators ----------------
    private void validateDateFormat(String date) {
        if (date == null || !Pattern.matches(DATE_PATTERN, date)) {
            throw new IllegalArgumentException("Date must be in yyyy-MM-dd format.");
        }
    }

    private void validateGender(String gender) {
        if (gender == null || !VALID_GENDERS.contains(gender.toLowerCase())) {
            throw new IllegalArgumentException("Invalid gender value.");
        }
    }
}
