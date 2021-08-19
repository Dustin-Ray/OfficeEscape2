/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.room;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Implements tests for Room. Since Room extends AbstractRoom, all methods from
 * AbstractRoom are also tested.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class RoomTest {

    /** The ID of the Room. */
    private static final int ROOM_ID = 7;

    /** The ID of Room A. */
    private static final int ROOM_A_ID = 6;

    /** The ID of Room B. */
    private static final int ROOM_B_ID = 8;

    /** The ID of Room C. */
    private static final int ROOM_C_ID = 9;

    /** The ID of Room D. */
    private static final int ROOM_D_ID = 10;

    /** A Room to use in testing. */
    private final Room room;

    /** Room A of room. */
    private final Room roomA;

    /** Door A of room. */
    private final Door doorA;

    /** Room B of room. */
    private final Room roomB;

    /** Door B of room. */
    private final Door doorB;

    /** Room C of room. */
    private final Room roomC;

    /** Door C of roomC. */
    private final Door doorC;

    /** Room D of room. */
    private final Room roomD;

    /** Door D of room. */
    private final Door doorD;


    /**
     * Constructs a RoomTest.
     */
    public RoomTest() {
        room = new Room(ROOM_ID);

        roomA = new Room(ROOM_A_ID);
        doorA = new Door();

        roomB = new Room(ROOM_B_ID);
        doorB = new Door();


        roomC = new Room(ROOM_C_ID);
        doorC = new Door();

        roomD = new Room(ROOM_D_ID);
        doorD = new Door();
    }


    /**
     * Checks that getRoomID() returns the correct Room ID.
     */
    @Test
    void getRoomID_returnsCorrectID() {
        assertEquals(ROOM_ID, room.getRoomID(),
                "getRoomID does not return correct ID");
    }


    /**
     * Checks that getMap() returns the correct GameMap.
     */
    @Test
    void getMap_returnsCorrectMap() {
        Room otherRoom = new Room(7);
        assertEquals(room.getMap(), otherRoom.getMap(),
                "getMap() does not return correct GameMap");
    }


    /**
     * Checks that getRoom('A') after setA() returns Room A.
     */
    @Test
    void getRoom_afterSetA_returnsRoomA() {
        room.setA(roomA, null);
        assertEquals(roomA, room.getRoom('A'),
                "getRoom('A') after setA() does not return Room A");
    }


    /**
     * Checks that getRoom('B') after setB() returns Room B.
     */
    @Test
    void getRoom_afterSetB_returnsRoomB() {
        room.setB(roomB, null);
        assertEquals(roomB, room.getRoom('B'),
                "getRoom('B') after setB() does not return Room B");
    }


    /**
     * Checks that getRoom('C') after setC() returns Room C.
     */
    @Test
    void getRoom_afterSetC_returnsRoomC() {
        room.setC(roomC, null);
        assertEquals(roomC, room.getRoom('C'),
                "getRoom('C') after setC() does not return Room C");
    }


    /**
     * Checks that getRoom('D') after setD() returns Room D.
     */
    @Test
    void getRoom_afterSetD_returnsRoomD() {
        room.setD(roomD, null);
        assertEquals(roomD, room.getRoom('D'),
                "getRoom('D') after setD() does not return Room D");
    }


    /**
     * Checks that getRoom(ID) throws an IllegalArgumentException when given an
     * ID not in {'A', 'B', 'C', 'D'}
     */
    @Test
    void getRoom_givenLetterIDWithNoRoom_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> room.getRoom('a'),
                "getRoom(ID) should throw exception for invalid ID");
    }


    /**
     * Checks that hasRoom('A') returns true after setA()
     */
    @Test
    void hasRoom_afterSetRoomA_returnsTrue() {
        room.setA(roomA, null);
        assertTrue(room.hasRoom('A'),
                "hasRoom('A') after setA() should return true");
    }


    /**
     * Checks that hasRoom('B') returns true after setB()
     */
    @Test
    void hasRoom_afterSetRoomB_returnsTrue() {
        room.setB(roomB, null);
        assertTrue(room.hasRoom('B'),
                "hasRoom('B') after setB() should return true");
    }


    /**
     * Checks that hasRoom('C') returns true after setC()
     */
    @Test
    void hasRoom_afterSetRoomC_returnsTrue() {
        room.setC(roomC, null);
        assertTrue(room.hasRoom('C'),
                "hasRoom('C') after setC() should return true");
    }


    /**
     * Checks that hasRoom('D') returns true after setD()
     */
    @Test
    void hasRoom_afterSetRoomD_returnsTrue() {
        room.setD(roomD, null);
        assertTrue(room.hasRoom('D'),
                "hasRoom('D') after setD() should return true");
    }


    /**
     * Checks that hasRoom(ID) returns false when given an ID not in
     * {'A', 'B', 'C', 'D'}
     */
    @Test
    void hasRoom_onLetterIDWithNoRoom_returnsFalse() {
        assertFalse(room.hasRoom('a'),
                "hasRoom() on invalid ID should return false");
    }


    /**
     * Checks that getDoor('A') after setA returns Door A.
     */
    @Test
    void getDoor_afterSetRoomAndDoorA_returnsDoorA() {
        room.setA(roomA, doorA);
        assertEquals(doorA, room.getDoor('A'),
                "getDoor('A') after setA() should return Door A");
    }


    /**
     * Checks that getDoor('B') after setB returns Door B.
     */
    @Test
    void getDoor_afterSetRoomAndDoorB_returnsDoorB() {
        room.setB(roomB, doorB);
        assertEquals(doorB, room.getDoor('B'),
                "getDoor('B') after setB() should return Door B");
    }


    /**
     * Checks that getDoor('C') after setC returns Door C.
     */
    @Test
    void getDoor_afterSetRoomAndDoorC_returnsDoorC() {
        room.setC(roomC, doorC);
        assertEquals(doorC, room.getDoor('C'),
                "getDoor('C') after setC() should return Door C");
    }


    /**
     * Checks that getDoor('D') after setD returns Door D.
     */
    @Test
    void getDoor_afterSetRoomAndDoorD_returnsDoorD() {
        room.setD(roomD, doorD);
        assertEquals(doorD, room.getDoor('D'),
                "getDoor('D') after setD() should return Door D");
    }


    /**
     * Checks that getDoor(ID) throws IllegalArgumentException when given an ID
     * not in {'A', 'B', 'C', 'D'}
     */
    @Test
    void getDoor_onLetterIDWithNoDoor_ThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> room.getDoor('d'),
                "getDoor(ID) should throw exception for invalid ID");
    }


    /**
     * Checks that x.equals(x) returns true.
     */
    @Test
    void equals_isReflexive() {
        assertEquals(roomA, roomA,
                "equals should return true for x.equals(x)");
    }


    /**
     * Checks that if x.equals(y), then y.equals(x).
     */
    @Test
    void equals_isSymmetric() {
        room.setA(roomA, doorA);
        room.setB(roomB, doorB);
        room.setC(roomC, doorC);
        room.setD(roomD, doorD);
        Room other = new Room(ROOM_ID);
        other.setA(roomA, doorA);
        other.setB(roomB, doorB);
        other.setC(roomC, doorC);
        other.setD(roomD, doorD);
        assertEquals(room, other,
                "equals() is not symmetric");
        assertEquals(other, room,
                "equals() is not symmetric");
    }


    /**
     * Checks that hasCode() returns the same value for equal items.
     */
    @Test
    void hashCode_onEqualObjects_isSame() {
        Room other = new Room(ROOM_ID);
        other.setA(roomA, doorA);
        room.setA(roomA, doorA);
        assertEquals(room.hashCode(), other.hashCode(),
                "equal objects should have the same hashCode()");
    }


    /**
     * Checks that toString() returns the correct String.
     */
    @Test
    void toString_onRoom_returnsCorrectString() {
        assertEquals("Room " + ROOM_ID, room.toString(),
                "toString() does not return correct String");
    }
}