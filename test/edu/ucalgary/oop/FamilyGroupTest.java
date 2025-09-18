package edu.ucalgary.oop;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Arrays;

public class FamilyGroupTest {

    private FamilyGroup familyGroup;
    private DisasterVictim victim1, victim2, victim3;

    @Before
    public void setUp() {
        familyGroup = new FamilyGroup();
        victim1 = new DisasterVictim("John", "Doe", "2000-05-15", "man", "2023-10-01");
        victim2 = new DisasterVictim("Alice", "Smith", "1998-08-20", "woman", "2023-10-02");
        victim3 = new DisasterVictim("Bob", "Brown", "1995-03-10", "man", "2023-10-03");
    }

    @Test
    public void testConstructorValid() {
        assertNotNull(familyGroup);
        assertEquals(0, familyGroup.getMembers().length);
    }

    @Test
    public void testAddMemberValid() {
        familyGroup.addMember(victim1);
        assertTrue(arrayContains(familyGroup.getMembers(), victim1));
    }

    @Test
    public void testAddMultipleMembers() {
        familyGroup.addMember(victim1);
        familyGroup.addMember(victim2);
        DisasterVictim[] expected = new DisasterVictim[] { victim1, victim2 };
        assertTrue(Arrays.equals(expected, familyGroup.getMembers()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicateMember() {
        familyGroup.addMember(victim1);
        familyGroup.addMember(victim1);
    }

    @Test
    public void testRemoveMemberValid() {
        familyGroup.addMember(victim1);
        familyGroup.addMember(victim2);
        familyGroup.removeMember(victim1);
        assertFalse(arrayContains(familyGroup.getMembers(), victim1));
        assertTrue(arrayContains(familyGroup.getMembers(), victim2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonexistentMember() {
        familyGroup.removeMember(victim3);
    }

    @Test
    public void testGetMembersAfterOperations() {
        familyGroup.addMember(victim1);
        familyGroup.addMember(victim2);
        familyGroup.removeMember(victim1);
        DisasterVictim[] members = familyGroup.getMembers();
        assertEquals(1, members.length);
        assertTrue(arrayContains(members, victim2));
    }

    private boolean arrayContains(DisasterVictim[] array, DisasterVictim victim) {
        for (DisasterVictim v : array) {
            if (v.equals(victim)) {
                return true;
            }
        }
        return false;
    }
}
