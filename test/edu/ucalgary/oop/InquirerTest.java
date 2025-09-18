package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InquirerTest {

    private Inquirer inquirer;

    @Before
    public void setUp() {
        inquirer = new Inquirer("Joseph", "Bouillon", "+1-123-456-7890", "Looking for my family members");
    }

    @Test
    public void testConstructorValid() {
        assertNotNull(inquirer);
        assertEquals("Joseph", inquirer.getFirstName());
        assertEquals("Bouillon", inquirer.getLastName());
        assertEquals("+1-123-456-7890", inquirer.getServicesPhoneNum());
        assertEquals("Looking for my family members", inquirer.getInfo());
    }

    @Test
    public void testSetAndGetFirstName() {
        inquirer.setFirstName("Alex");
        assertEquals("Alex", inquirer.getFirstName());
    }

    @Test
    public void testSetAndGetLastName() {
        inquirer.setLastName("Smith");
        assertEquals("Smith", inquirer.getLastName());
    }

    @Test
    public void testSetAndGetServicesPhoneNum() {
        inquirer.setServicesPhoneNum("+1-987-654-3210");
        assertEquals("+1-987-654-3210", inquirer.getServicesPhoneNum());
    }

    @Test
    public void testSetAndGetInfo() {
        inquirer.setInfo("Updated inquiry info");
        assertEquals("Updated inquiry info", inquirer.getInfo());
    }
}
