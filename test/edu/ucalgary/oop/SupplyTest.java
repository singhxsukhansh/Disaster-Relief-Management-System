package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SupplyTest {

    private Supply blanket;
    private Supply water;
    private Supply cot;
    private Supply personalBelonging;

    @Before
    public void setUp() {
        blanket = new Blanket(101, "Blanket", 5);
        water = new Water(102, "Water", 10, "2024-12-31");
        cot = new Cot(103, "Cot", 3, "115", "B6");
        personalBelonging = new PersonalBelonging(104, "Personal Belonging", 1, "a green leather suitcase");
    }

    @Test
    public void testBlanketConstructor() {
        assertNotNull(blanket);
        assertEquals("Blanket", blanket.getType());
        assertEquals(5, blanket.getQuantity());
    }

    @Test
    public void testBlanketSetType() {
        blanket.setType("Emergency Blanket");
        assertEquals("Emergency Blanket", blanket.getType());
    }

    @Test
    public void testBlanketSetQuantityValid() {
        blanket.setQuantity(7);
        assertEquals(7, blanket.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBlanketSetNegativeQuantity() {
        blanket.setQuantity(-2);
    }

    @Test
    public void testWaterConstructor() {
        assertNotNull(water);
        assertEquals("Water", water.getType());
        assertEquals(10, water.getQuantity());
    }

    @Test
    public void testWaterSetQuantityValid() {
        water.setQuantity(15);
        assertEquals(15, water.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWaterSetNegativeQuantity() {
        water.setQuantity(-5);
    }

    //@Test
    //public void testWaterExpiration() {
       // boolean expired = water.isExpired();
        //assertNotNull(expired);
  //  }

    @Test
    public void testCotConstructor() {
        assertNotNull(cot);
        assertEquals("Cot", cot.getType());
        assertEquals(3, cot.getQuantity());
        assertEquals("115", ((Cot) cot).getRoomNumber());
        assertEquals("B6", ((Cot) cot).getGridLocation());
    }

    @Test
    public void testCotSetRoomNumberValid() {
        ((Cot) cot).setRoomNumber("116");
        assertEquals("116", ((Cot) cot).getRoomNumber());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCotSetRoomNumberInvalid() {
        ((Cot) cot).setRoomNumber("");
    }

    @Test
    public void testCotSetGridLocationValid() {
        ((Cot) cot).setGridLocation("C3");
        assertEquals("C3", ((Cot) cot).getGridLocation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCotSetGridLocationInvalid() {
        ((Cot) cot).setGridLocation(null);
    }

    @Test
    public void testPersonalBelongingConstructor() {
        assertNotNull(personalBelonging);
        assertEquals("Personal Belonging", personalBelonging.getType());
        assertEquals(1, personalBelonging.getQuantity());
        assertEquals("a green leather suitcase", ((PersonalBelonging) personalBelonging).getDescription());
    }

    @Test
    public void testPersonalBelongingSetDescriptionValid() {
        ((PersonalBelonging) personalBelonging).setDescription("a red backpack");
        assertEquals("a red backpack", ((PersonalBelonging) personalBelonging).getDescription());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testPersonalBelongingSetDescriptionInvalid() {
        ((PersonalBelonging) personalBelonging).setDescription("");
    }
}
