/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.room;

import model.map.GameMap;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * Implements behavior common to all Rooms. Each Room has 4 possible Doors and
 * 4 possible Rooms: A, B, C, and D. The neighboring Rooms connected to the
 * current Room by the Doors are labeled with the same letter as the Doors.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public abstract class AbstractRoom implements Serializable {

    @Serial
    private static final long serialVersionUID = 500430890537751612L;

    private final String INVALID_ID_MESSAGE = "letterID must be one of" +
            " {'A', 'B', 'C', 'D'}";

    /** Door A of this Room. */
    private Door myDoorA;

    /** Door B of this Room. */
    private Door myDoorB;

    /** Door C of this Room. */
    private Door myDoorC;

    /** Door D of this Room. */
    private Door myDoorD;

    /** The Room linked to this Room by Door A. */
    private Room myRoomA;

    /** The Room linked to this Room by Door B. */
    private Room myRoomB;

    /** The Room linked to this Room by Door C. */
    private Room myRoomC;

    /** The Room linked to this Room by Door D. */
    private Room myRoomD;

    /** The unique integer ID of this Room. */
    private final int myID;

    /** The GameMap of this Room. */
    private final GameMap myGM;


    /**
     * Constructs a Room with the given integer ID.
     *
     * @param theID The integer ID of this Room.
     */
    public AbstractRoom(final int theID) {
        myID = theID;
        myGM = new GameMap(myID);
    }


    /**
     * Returns the GameMap of this Room.
     *
     * @return The GameMap of this Room.
     */
    public GameMap getMap() {
        return myGM;
    }


    /**
     * Returns the integer ID of of this Room.
     *
     * @return The integer ID of this Room.
     */
    public int getRoomID() {
        return myID;
    }


    /**
     * Returns the Door specified by the given letter ID.
     *
     * @param letterID The letter representing the Door to return.
     * @return The Door specified by letterID or null if letterID is not
     *     A, B, C, or D.
     */
    public Door getDoor(final char letterID) {
        return switch (letterID) {
            case 'A' -> myDoorA;
            case 'B' -> myDoorB;
            case 'C' -> myDoorC;
            case 'D' -> myDoorD;
            default -> throw new IllegalArgumentException(INVALID_ID_MESSAGE);
        };
    }


    /**
     * Returns the room specified by the given letter ID.
     *
     * @param letterID The letter representing the Room to return.
     * @return the Room specified by letterID or null if letterID is not
     *     A, B, C, or D.
     */
    public Room getRoom(final char letterID) {
        return switch (letterID) {
            case 'A' -> myRoomA;
            case 'B' -> myRoomB;
            case 'C' -> myRoomC;
            case 'D' -> myRoomD;
            default -> throw new IllegalArgumentException(INVALID_ID_MESSAGE);
        };
    }


    /**
     * Checks to see if the specified room exists.
     *
     * @param letterID The letter ID of the Room to check.
     * @return true if a room corresponding to letterID is not null and false
     *     otherwise.
     */
    public boolean hasRoom(final char letterID) {
        return switch (letterID) {
            case 'A' -> myRoomA != null;
            case 'B' -> myRoomB != null;
            case 'C' -> myRoomC != null;
            case 'D' -> myRoomD != null;
            default -> false;
        };
    }


    /**
     * Sets Room A and Door A to the given Room and Door.
     *
     * @param room Room A of this Room.
     * @param door Door A separating this Room and Room A.
     */
    public void setA(final Room room, final Door door) {
        myRoomA = room;
        myDoorA = door;
    }


    /**
     * Sets Room B and Door B to the given Room and Door.
     *
     * @param room Room B of this Room.
     * @param door Door B separating this Room and Room B.
     */
    public void setB(final Room room, final Door door) {
        myRoomB = room;
        myDoorB = door;
    }


    /**
     * Sets Room C and Door C to the given Room and Door.
     *
     * @param theRoom Room C of this Room.
     * @param theDoor Door C separating this Room and Room C.
     */
    public void setC(final Room theRoom, final Door theDoor) {
        myRoomC = theRoom;
        myDoorC = theDoor;
    }


    /**
     * Sets Room D and Door D to the given Room and Door.
     *
     * @param theRoom Room D of this Room.
     * @param theDoor Door D separating this Room and Room D.
     */
    public void setD(final Room theRoom, final Door theDoor) {
        myRoomD = theRoom;
        myDoorD = theDoor;
    }


    @Override
    public boolean equals(final Object other) {
        boolean result = false;
        if ((other != null) && (other.getClass().equals(this.getClass()))) {
            AbstractRoom o = (Room) other;
            result = (this.myID == o.myID
                    && Objects.equals(this.myRoomA, o.myRoomA)
                    && Objects.equals(this.myRoomB, o.myRoomB)
                    && Objects.equals(this.myRoomC, o.myRoomC)
                    && Objects.equals(this.myRoomD, o.myRoomD)
                    && Objects.equals(this.myDoorA, o.myDoorA)
                    && Objects.equals(this.myDoorB, o.myDoorB)
                    && Objects.equals(this.myDoorC, o.myDoorC)
                    && Objects.equals(this.myDoorD, o.myDoorD)
                    && this.myGM.equals(o.myGM)
            );
        }
        return result;
    }


    @Override
    public int hashCode() {
        return Objects.hash(this.getRoomID());
    }


    @Override
    public String toString() {
        return "Room " + getRoomID();
    }

}
