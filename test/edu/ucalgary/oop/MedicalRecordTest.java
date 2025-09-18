package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MedicalRecordTest {

    private Location location;
    private MedicalRecord record;

    @Before
    public void setUp() {
        location = new Location("Relief Shelter", "123 Main Street");
        record = new MedicalRecord(location, "Fracture treatment", "2023-11-15");
    }

    @Test
    public void testConstructorValid() {
        assertEquals("Constructor should correctly assign location", location, record.getLocation());
        assertEquals("Constructor should correctly assign treatment details", "Fracture treatment", record.getTreatmentDetails());
        assertEquals("Constructor should correctly assign date of treatment", "2023-11-15", record.getDateOfTreatment());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidDateFormat() {
        new MedicalRecord(location, "Treatment", "15-11-2023");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidCharactersInDate() {
        new MedicalRecord(location, "Treatment", "2023-Nov-15");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorFutureDate() {
        new MedicalRecord(location, "Treatment", "2100-12-01"); // Too far in the future
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEmptyTreatmentDetails() {
        new MedicalRecord(location, "", "2023-11-15");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullTreatmentDetails() {
        new MedicalRecord(location, null, "2023-11-15");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullLocation() {
        new MedicalRecord(null, "Fracture treatment", "2023-11-15");
    }

    @Test
    public void testSetLocationValid() {
        Location newLocation = new Location("Hospital", "456 Elm Street");
        record.setLocation(newLocation);
        assertEquals("setLocation should update location", newLocation, record.getLocation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetLocationNull() {
        record.setLocation(null);
    }

    @Test
    public void testSetTreatmentDetailsValid() {
        record.setTreatmentDetails("Updated treatment details");
        assertEquals("setTreatmentDetails should update details", "Updated treatment details", record.getTreatmentDetails());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTreatmentDetailsEmpty() {
        record.setTreatmentDetails("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetTreatmentDetailsNull() {
        record.setTreatmentDetails(null);
    }

    @Test
    public void testSetDateOfTreatmentValid() {
        record.setDateOfTreatment("2023-12-01");
        assertEquals("setDateOfTreatment should update date", "2023-12-01", record.getDateOfTreatment());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfTreatmentInvalidFormat() {
        record.setDateOfTreatment("01-12-2023");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfTreatmentNonDateString() {
        record.setDateOfTreatment("Not a date");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfTreatmentNull() {
        record.setDateOfTreatment(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfTreatmentFutureDate() {
        record.setDateOfTreatment("2100-12-31");
    }
}
