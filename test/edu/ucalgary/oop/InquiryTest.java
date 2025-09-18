package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InquiryTest {

    private Inquiry inquiry;
    private Inquirer inquirer;
    private DisasterVictim victim;
    
    @Before
    public void setUp() {
        inquirer = new Inquirer("Joseph", "Bouillon", "+1-123-456-7890", "Looking for my family members");
        victim = new DisasterVictim("Jane", "Doe", "1995-04-10", "woman", "2025-01-25");
        inquiry = new Inquiry(1001, "2025-02-15", "Need assistance", inquirer, victim);
    }
    
    @Test
    public void testConstructorValid() {
        assertNotNull(inquiry);
        assertEquals(1001, inquiry.getInquiryID());
        assertEquals("2025-02-15", inquiry.getInquiryDate());
        assertEquals("Need assistance", inquiry.getInfoProvided());
        assertEquals(inquirer, inquiry.getInquirer());
        assertEquals(victim, inquiry.getVictim());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullInquirer() {
        new Inquiry(1002, "2025-02-16", "Assistance required", null, victim);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullVictim() {
        new Inquiry(1003, "2025-02-16", "Assistance required", inquirer, null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorEmptyInfoProvided() {
        new Inquiry(1004, "2025-02-16", "", inquirer, victim);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWhitespaceInfoProvided() {
        new Inquiry(1005, "2025-02-16", "   ", inquirer, victim);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorInvalidInquiryDateFormat() {
        new Inquiry(1006, "15-02-2025", "Assistance required", inquirer, victim);
    }
    
    @Test
    public void testGetInquiryID() {
        assertEquals(1001, inquiry.getInquiryID());
    }
    
    @Test
    public void testGetInquiryDate() {
        assertEquals("2025-02-15", inquiry.getInquiryDate());
    }
    
    @Test
    public void testGetInfoProvided() {
        assertEquals("Need assistance", inquiry.getInfoProvided());
    }
    
    @Test
    public void testGetInquirer() {
        assertEquals(inquirer, inquiry.getInquirer());
    }
    
    @Test
    public void testGetVictim() {
        assertEquals(victim, inquiry.getVictim());
    }
}
