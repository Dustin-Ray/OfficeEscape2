/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.room;

import model.trivia.Trivia;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Implements unit tests for Door.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class DoorTest {

    /** A Door to use in testing. */
    private final Door door;


    /**
     * Constructs a DoorTest.
     */
    public DoorTest() {
        door = new Door();
    }


    /**
     * Checks that the default constructor creates an invalid, locked Door.
     */
    @Test
    void constructor_withNoArguments_makesInvalidAndLocked() {
        String isValidMsg = "no argument constructor should make Door invalid";
        String isUnlockedMsg = "no argument constructor should make Door locked";
        assertFalse(door.isValid(), isValidMsg);
        assertFalse(door.isUnlocked(), isUnlockedMsg);
    }

    /**
     * Checks that the argument constructor correctly assigns the given fields.
     */
    @Test
    void constructor_withArguments_correctlySetsFields() {
        String isValidMsg = "arg constructor should assign validity";
        String isUnlockedMsg = "arg constructor should assign lock status";
        Door door = new Door(true, true, null);
        assertTrue(door.isValid(), isValidMsg);
        assertTrue(door.isUnlocked(), isUnlockedMsg);
    }


    /**
     * Checks that setDoorValidity(true) correctly sets validity to true.
     */
    @Test
    void setDoorValidity_givenTrue_setsValidityToTrue() {
        door.setDoorValidity(false);
        door.setDoorValidity(true);
        assertTrue(door.isValid(),
                "setDoorValidity() does not correctly assign validity");
    }


    /**
     * Checks that setDoorValidity(false) correctly sets validity to false.
     */
    @Test
    void setDoorValidity_givenFalse_setsValidityToFalse() {
        door.setDoorValidity(true);
        door.setDoorValidity(false);
        assertFalse(door.isValid(),
                "setDoorValidity(false) does not correctly assign validity");
    }


    /**
     * Checks that unlockDoor() unlocks a locked Door.
     */
    @Test
    void unlockDoor_afterLockDoor_unlocksDoor() {
        door.lockDoor();
        door.unlockDoor();
        assertTrue(door.isUnlocked(),
                "unlockDoor() should unlock a locked Door");
    }


    /**
     * Checks that lockDoor() locks an unlocked Door.
     */
    @Test
    void lockDoor_afterUnlockDoor_lockDoor() {
        door.unlockDoor();
        door.lockDoor();
        assertFalse(door.isUnlocked(),
                "lockDoor() should lock an unlocked Door");
    }


    /**
     * Checks that getTrivia() after setTrivia() returns the correct Trivia.
     */
    @Test
    void getTrivia_afterSetTrivia_returnsCorrectTrivia() {
        Trivia trivia = new Trivia(1, "Do you like apples?",
                "Yes", "No", 5);
        door.setTrivia(trivia);
        assertEquals(trivia, door.getTrivia(),
                "setTrivia does not properly set Trivia");
    }


    /**
     *  Checks that isValid() returns false after setting a Door's validity
     *  from true to false.
     */
    @Test
    void isValid_afterSetValidToInvalid_returnsFalse() {
        door.setDoorValidity(true);
        door.setDoorValidity(false);
        assertFalse(door.isValid(),
                "isValid() on invalid Door should return false");
    }

    /**
     * Checks that equals() on equal Doors returns true.
     */
    @Test
    void equals_onIdenticalDoors_returnsTrue() {
        Trivia trivia = new Trivia(1, "Do you like apples?",
                "Yeah", "No", 5);
        Door doorA = new Door(true, false, trivia);
        Door doorB = new Door(true, false, trivia);
        assertEquals(doorA, doorB);
    }


    /**
     * Checks that equals() returns false for Doors with different Trivia.
     */
    @Test
    void equals_onDoorsWithDifferentTrivia_returnsFalse() {
        Trivia trivia = new Trivia(1, "Do you like pizza?",
                "Yes", "No", 5);
        Door doorA = new Door(true, false, trivia);
        Door doorB = new Door();
        doorB.setDoorValidity(true);
        doorB.lockDoor();
        assertNotEquals(doorA, doorB,
                "equals() on Doors with different Trivia should return false");
    }


    /**
     * Checks that hashCode() returns the same value for equal Doors.
     */
    @Test
    void hashCode_onEqualDoors_isSame() {
        String msg = "hashCode() should return the same value for equal Door";
        Door other = new Door();
        assertEquals(door.hashCode(), other.hashCode(), msg);
        Trivia trivia = new Trivia(1, "Do you like hot dogs?",
                "Yes", "No", 5);
        door.setTrivia(trivia);
        other.setTrivia(trivia);
        assertEquals(door.hashCode(), other.hashCode(), msg);
    }


}