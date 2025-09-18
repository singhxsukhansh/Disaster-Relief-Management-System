package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class LocationTest {

    private Location location;
    private DisasterVictim victim1, victim2;
    private Supply supply1, supply2;

    @Before
    public void setUp() {
        location = new Location("Relief Shelter", "123 Main Street");
        victim1 = new DisasterVictim("John", "Doe", "2000-05-15", "man", "2023-10-01");
        victim2 = new DisasterVictim("Alice", "Smith", "1998-08-20", "woman", "2023-10-02");
        supply1 = new Water(1, "Water", 2, "2024-12-01");
        supply2 = new Blanket(2, "Blanket", 1);
    }

    @Test
    public void testConstructorValid() {
        assertEquals("Relief Shelter", location.getName());
        assertEquals("123 Main Street", location.getAddress());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidName() {
        new Location("", "123 Main Street");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullName() {
        new Location(null, "123 Main Street");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidAddress() {
        new Location("Shelter", "");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullAddress() {
        new Location("Shelter", null);
    }

    @Test
    public void testSetNameValid() {
        location.setName("New Shelter");
        assertEquals("New Shelter", location.getName());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetNameInvalid() {
        location.setName("");
    }

    @Test
    public void testSetAddressValid() {
        location.setAddress("456 Elm Street");
        assertEquals("456 Elm Street", location.getAddress());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetAddressInvalid() {
        location.setAddress(null);
    }

    @Test
    public void testAddOccupant() {
        location.addOccupant(victim1);
        assertTrue(Arrays.asList(location.getOccupants()).contains(victim1));
    }

    @Test
    public void testAddMultipleOccupants() {
        location.addOccupant(victim1);
        location.addOccupant(victim2);
        DisasterVictim[] expected = new DisasterVictim[] { victim1, victim2 };
        assertTrue(Arrays.equals(expected, location.getOccupants()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicateOccupant() {
        location.addOccupant(victim1);
        location.addOccupant(victim1);
    }

    @Test
    public void testRemoveOccupant() {
        location.addOccupant(victim1);
        location.removeOccupant(victim1);
        assertFalse(Arrays.asList(location.getOccupants()).contains(victim1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonexistentOccupant() {
        location.removeOccupant(victim1);
    }

    @Test
    public void testOccupantsInitiallyEmpty() {
        assertEquals(0, location.getOccupants().length);
    }

    @Test
    public void testAddSupply() {
        location.addSupply(supply1);
        assertTrue(Arrays.asList(location.getSupplies()).contains(supply1));
    }

    @Test
    public void testAddMultipleSupplies() {
        location.addSupply(supply1);
        location.addSupply(supply2);
        Supply[] expected = new Supply[] { supply1, supply2 };
        assertTrue(Arrays.equals(expected, location.getSupplies()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicateSupply() {
        location.addSupply(supply1);
        location.addSupply(supply1);
    }

    @Test
    public void testRemoveSupply() {
        location.addSupply(supply1);
        location.removeSupply(supply1);
        assertFalse(Arrays.asList(location.getSupplies()).contains(supply1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonexistentSupply() {
        location.removeSupply(supply1);
    }

    @Test
    public void testSuppliesInitiallyEmpty() {
        assertEquals(0, location.getSupplies().length);
    }
}
