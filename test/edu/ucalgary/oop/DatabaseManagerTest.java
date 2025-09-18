package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatabaseManagerTest {

    private DatabaseManager dbManager;

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNullUrlThrowsException() {
        dbManager = new DatabaseManager(null, "user", "pass");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyUsernameThrowsException() {
        dbManager = new DatabaseManager("jdbc:postgresql://localhost:5432/testdb", "", "pass");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithEmptyPasswordThrowsException() {
        dbManager = new DatabaseManager("jdbc:postgresql://localhost:5432/testdb", "user", "");
    }

    @Test
    public void testConstructorWithInvalidConnectionDoesNotThrowButConnectionIsNull() {
        // Invalid credentials or DB URL — we want to confirm connection becomes null
        dbManager = new DatabaseManager("jdbc:postgresql://localhost:9999/invalid", "wrong", "wrong");
        assertNull("Connection should be null due to failure", dbManager.getConnection());
    }

    @Test
    public void testCloseConnectionWithoutError() {
        dbManager = new DatabaseManager("jdbc:postgresql://localhost:9999/invalid", "wrong", "wrong");
        dbManager.closeConnection();  // Should not throw even if connection is null
        assertTrue(true); // Reached without exception
    }
}
