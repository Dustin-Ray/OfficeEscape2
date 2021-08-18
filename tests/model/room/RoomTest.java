package model.room;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Implements tests for Room.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
class RoomTest {

    private final Room room;

    private static final int ROOM_ID = 0;

    public RoomTest() {
        room = new Room(ROOM_ID);
        Door door = new Door();
        door.unlockDoor();
        door.setDoor(true);
        room.setC(new Room(1), door);
    }

    @Test
    void getRoomID_onRoom_returnsCorrectID() {
        assertEquals(ROOM_ID, room.getRoomID());
    }


    @Test
    void equals_onIdenticalRooms_returnsTrue() {

    }
}