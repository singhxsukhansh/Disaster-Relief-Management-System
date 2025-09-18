package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Scanner;

public class UserInterfaceTest {

    private UserInterface ui;

    @Before
    public void setUp() {
        // Provide controlled input for testing
        String fakeInput = "1\n123\nHello\nexit\n";
        Scanner testScanner = new Scanner(fakeInput);
        ui = new UserInterface(testScanner);
    }

    @Test
    public void testDisplayMenu() {
        try {
            ui.displayMenu();
        } catch (Exception e) {
            fail("displayMenu() threw an exception");
        }
    }

    @Test
    public void testGetUserInputNonNull() {
        String input = ui.getUserInput();
        assertNotNull("getUserInput() should not return null", input);
    }

    @Test
    public void testProcessSelectionValid() {
        try {
            ui.processSelection("1"); // simulate create inquiry
        } catch (Exception e) {
            fail("processSelection() with valid input threw an exception");
        }
    }

    @Test
    public void testProcessSelectionInvalid() {
        try {
            ui.processSelection("invalid");
        } catch (Exception e) {
            fail("processSelection() with invalid input threw an exception");
        }
    }

    @Test
    public void testProcessMultipleSelections() {
        try {
            ui.processSelection("1");
            ui.processSelection("2");
            ui.processSelection("exit");
        } catch (Exception e) {
            fail("Multiple processSelection() calls threw an exception");
        }
    }

    @Test
    public void testExit() {
        try {
            ui.exit();
        } catch (Exception e) {
            fail("exit() threw an exception");
        }
    }
}
