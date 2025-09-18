package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ReliefServiceTest {

    private ReliefService reliefService;
    private Inquirer inquirer;
    private DisasterVictim missingPerson;
    private Location lastKnownLocation;
    private String validDate = "2025-02-10";
    private String invalidDate = "2025/02/10";
    private String infoProvided = "Looking for family member";

    @Before
    public void setUp() {
        inquirer = new Inquirer("John", "Alex", "+1-123-456-7890", "Looking for my family member");
        missingPerson = new DisasterVictim("Jane", "Doe", "1995-04-10", "woman", "2025-01-25");
        lastKnownLocation = new Location("University of Calgary", "2500 University Dr NW");
        reliefService = new ReliefService(inquirer, missingPerson, validDate, infoProvided, lastKnownLocation);
    }

    @Test
    public void testConstructorValid() {
        assertNotNull(reliefService);
        assertEquals(inquirer, reliefService.getInquirer());
        assertEquals(missingPerson, reliefService.getMissingPerson());
        assertEquals(validDate, reliefService.getDateOfInquiry());
        assertEquals(infoProvided, reliefService.getInfoProvided());
        assertEquals(lastKnownLocation, reliefService.getLastKnownLocation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfInquiryInvalid() {
        reliefService.setDateOfInquiry(invalidDate);
    }

    @Test
    public void testSetDateOfInquiryValid() {
        reliefService.setDateOfInquiry(validDate);
        assertEquals(validDate, reliefService.getDateOfInquiry());
    }
    
    @Test
    public void testSetAndGetInquirer() {
        Inquirer newInquirer = new Inquirer("Alice", "Cooper", "+1-555-555-5555", "New inquiry");
        reliefService.setInquirer(newInquirer);
        assertEquals(newInquirer, reliefService.getInquirer());
    }
    
    @Test
    public void testSetAndGetMissingPerson() {
        DisasterVictim newMissing = new DisasterVictim("Bob", "Marley", "1980-02-06", "man", "2025-01-20");
        reliefService.setMissingPerson(newMissing);
        assertEquals(newMissing, reliefService.getMissingPerson());
    }
    
    @Test
    public void testSetAndGetInfoProvided() {
        String newInfo = "Updated inquiry info";
        reliefService.setInfoProvided(newInfo);
        assertEquals(newInfo, reliefService.getInfoProvided());
    }
    
    @Test
    public void testSetAndGetLastKnownLocation() {
        Location newLocation = new Location("Shelter", "1234 New St");
        reliefService.setLastKnownLocation(newLocation);
        assertEquals(newLocation, reliefService.getLastKnownLocation());
    }
    
    @Test
    public void testGetLogDetails() {
        String expectedLog = "Inquirer: " + inquirer.getFirstName() + " " + inquirer.getLastName() +
                             ", Missing Person: " + missingPerson.getFirstName() + " " + missingPerson.getLastName() +
                             ", Date of Inquiry: " + validDate +
                             ", Info Provided: " + infoProvided +
                             ", Last Known Location: " + lastKnownLocation.getName();
        assertEquals(expectedLog, reliefService.getLogDetails());
    }
}
