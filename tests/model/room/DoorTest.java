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

    private final Door door;

    public DoorTest() {
        door = new Door();
    }


    @Test
    void constructor_withNoArguments_makesInvalidAndLocked() {
        assertFalse(door.isValid());
        assertFalse(door.isUnlocked());
    }

    @Test
    void constructor_withArguments_correctlySetsFields() {
        Door door = new Door(true, true, null);
        assertTrue(door.isValid());
        assertTrue(door.isUnlocked());
    }


    @Test
    void setDoor_givenTrue_setsValidityToTrue() {
        door.setDoor(true);
        assertTrue(door.isValid());
    }

    @Test
    void unlockDoor_onDoor_unlocksDoor() {
        door.unlockDoor();
        assertTrue(door.isUnlocked());
    }

    @Test
    void lockDoor() {
        door.unlockDoor();
        door.lockDoor();
        assertFalse(door.isUnlocked());
    }

    @Test
    void getTrivia() {

    }

    @Test
    void isValid_onDoor_returnsCorrectValidity() {
        door.setDoor(true);
        assertTrue(door.isValid());
    }

    @Test
    void isUnlocked() {
    }

    @Test
    void equals_onIdenticalDoors_returnsTrue() {
        Trivia trivia = new Trivia(1, "Do you like apples?",
                "Yeah", "No", 5);
        Door doorA = new Door(true, false, trivia);
        Door doorB = new Door(true, false, trivia);
        assertEquals(doorA, doorB);
    }
}