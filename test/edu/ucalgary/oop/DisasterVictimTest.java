package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class DisasterVictimTest {

    private DisasterVictim victim;
    private DisasterVictim victim2;
    private FamilyGroup familyGroup;
    private Location location;
    private MedicalRecord record1, record2;
    private Supply supply1, supply2;

    @Before
    public void setUp() {
        victim = new DisasterVictim("John", "Doe", "2000-05-15", "man", "2023-10-01");
        victim2 = new DisasterVictim("Alice", "Smith", "1998-08-20", "woman", "2023-10-02");
        familyGroup = new FamilyGroup();
        location = new Location("Relief Shelter", "123 Main Street");
        record1 = new MedicalRecord(location, "Fracture treatment", "2023-11-15");
        record2 = new MedicalRecord(location, "Flu treatment", "2023-11-20");
        supply1 = new Water(1, "Water", 2, "2024-12-01");
        supply2 = new Blanket(2, "Blanket", 1);
    }

    @Test
    public void testConstructorValid() {
        assertEquals("John", victim.getFirstName());
        assertEquals("Doe", victim.getLastName());
        assertEquals("2000-05-15", victim.getDateOfBirth());
        assertEquals("man", victim.getGender());
        assertEquals("2023-10-01", victim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidGender() {
        new DisasterVictim("Jane", "Doe", "1995-03-10", "invalid", "2023-10-01");
    }

 //   @Test(expected = IllegalArgumentException.class)
 //   public void testConstructorInvalidEntryDate() {
  //      new DisasterVictim("Jane", "Doe", "1995-03-10", "woman", "2023-31-01");
  //  }

    @Test
    public void testSetFirstName() {
        victim.setFirstName("Jane");
        assertEquals("Jane", victim.getFirstName());
    }

    @Test
    public void testSetLastName() {
        victim.setLastName("Smith");
        assertEquals("Smith", victim.getLastName());
    }

    @Test
    public void testSetGenderValid() {
        victim.setGender("non-binary person");
        assertEquals("non-binary person", victim.getGender());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetGenderInvalid() {
        victim.setGender("other");
    }

    @Test
    public void testSetEntryDateValid() {
        victim.setEntryDate("2023-11-10");
        assertEquals("2023-11-10", victim.getEntryDate());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetEntryDateInvalid() {
        victim.setEntryDate("10/11/2023");
    }

    @Test
    public void testSetDateOfBirthValid() {
        victim.setDateOfBirth("1999-01-01");
        assertEquals("1999-01-01", victim.getDateOfBirth());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetDateOfBirthInvalid() {
        victim.setDateOfBirth("01-01-1999");
    }

    @Test
    public void testSocialIDPositive() {
        assertTrue(victim.getAssignedSocialID() > 0);
    }

    @Test
    public void testSocialIDAutoIncrement() {
        DisasterVictim d1 = new DisasterVictim("Tom", "Brown", "1990-03-10", "man", "2023-10-01");
        DisasterVictim d2 = new DisasterVictim("Jerry", "White", "1992-07-12", "man", "2023-10-02");
        assertEquals(d1.getAssignedSocialID() + 1, d2.getAssignedSocialID());
    }

    @Test
    public void testAddMedicalRecord() {
        victim.addMedicalRecord(record1);
        assertTrue(victim.getMedicalRecords().contains(record1));
    }

    @Test
    public void testRemoveMedicalRecord() {
        victim.addMedicalRecord(record1);
        victim.removeMedicalRecord(record1);
        assertFalse(victim.getMedicalRecords().contains(record1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonexistentMedicalRecord() {
        victim.removeMedicalRecord(record1);
    }

    @Test
    public void testAddMultipleMedicalRecords() {
        victim.addMedicalRecord(record1);
        victim.addMedicalRecord(record2);
        List<MedicalRecord> expected = Arrays.asList(record1, record2);
        assertEquals(expected, victim.getMedicalRecords());
    }

    @Test
    public void testSetMedicalRecordsArray() {
        MedicalRecord[] records = new MedicalRecord[] { record1, record2 };
        victim.setMedicalRecords(records);
        assertArrayEquals(records, victim.getMedicalRecords().toArray());
    }

    @Test
    public void testMedicalRecordsInitiallyEmpty() {
        assertEquals(0, victim.getMedicalRecords().size());
    }

    @Test
    public void testAddPersonalBelonging() {
        victim.addPersonalBelonging(supply1);
        assertTrue(victim.getPersonalBelongings().contains(supply1));
    }

    @Test
    public void testRemovePersonalBelonging() {
        victim.addPersonalBelonging(supply1);
        victim.removePersonalBelonging(supply1);
        assertFalse(victim.getPersonalBelongings().contains(supply1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonexistentPersonalBelonging() {
        victim.removePersonalBelonging(supply1);
    }

    @Test
    public void testAddMultiplePersonalBelongings() {
        victim.addPersonalBelonging(supply1);
        victim.addPersonalBelonging(supply2);
        List<Supply> expected = Arrays.asList(supply1, supply2);
        assertEquals(expected, victim.getPersonalBelongings());
    }

    @Test
    public void testSetPersonalBelongingsArray() {
        Supply[] supplies = new Supply[] { supply1, supply2 };
        victim.setPersonalBelongings(supplies);
        assertArrayEquals(supplies, victim.getPersonalBelongings().toArray());
    }

    @Test
    public void testPersonalBelongingsInitiallyEmpty() {
        assertEquals(0, victim.getPersonalBelongings().size());
    }

    @Test
    public void testSetFamilyGroup() {
        victim.setFamilyGroup(familyGroup);
        assertEquals(familyGroup, victim.getFamilyGroup());
    }

    @Test
    public void testFamilyGroupIntegration() {
        victim.setFamilyGroup(familyGroup);
        familyGroup.addMember(victim);
        DisasterVictim[] members = familyGroup.getMembers();
        boolean found = false;
        for (DisasterVictim v : members) {
            if (v.equals(victim)) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    public void testCreateInquiryValid() {
        victim.createInquiry("Looking for missing family member.");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateInquiryNull() {
        victim.createInquiry(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateInquiryEmpty() {
        victim.createInquiry("");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateInquiryWhitespace() {
        victim.createInquiry("   ");
    }

    @Test
    public void testSetAndGetComments() {
        victim.setComments("Medical assistance needed.");
        assertEquals("Medical assistance needed.", victim.getComments());
    }

    @Test
    public void testCommentsInitiallyNull() {
        assertNull(victim.getComments());
    }

    @Test
    public void testModifyComments() {
        victim.setComments("Urgent help needed.");
        victim.setComments("Condition stable now.");
        assertEquals("Condition stable now.", victim.getComments());
    }

    @Test
    public void testSetLocation() {
        victim.setLocation(location);
        assertEquals(location, victim.getLocation());
    }

    @Test
    public void testSetAndRetrieveLocation() {
        Location newLocation = new Location("Temporary Camp", "789 Pine Ave");
        victim.setLocation(newLocation);
        assertEquals(newLocation, victim.getLocation());
    }

    @Test
    public void testBoundaryEntryDate() {
        victim.setEntryDate("2023-01-01");
        assertEquals("2023-01-01", victim.getEntryDate());
    }

    @Test
    public void testValidLeapYearBirthdate() {
        victim.setDateOfBirth("2000-02-29");
        assertEquals("2000-02-29", victim.getDateOfBirth());
    }
}
