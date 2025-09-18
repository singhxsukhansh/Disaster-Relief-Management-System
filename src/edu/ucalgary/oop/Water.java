package edu.ucalgary.oop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Water extends Supply {
    private String expirationDate;

    public Water(int supplyID, String type, int quantity, String expirationDate) {
        super(supplyID, type, quantity);
        this.expirationDate = expirationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isExpired() {
        try {
            LocalDate exp = LocalDate.parse(expirationDate, DateTimeFormatter.ISO_LOCAL_DATE);
            return exp.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
