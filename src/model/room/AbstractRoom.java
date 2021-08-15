/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Dustin Ray, Raz Consta, Reuben Keller
 */

package model.room;

import model.map.GameMap;

import java.io.Serializable;

/**
 * Implements behavior common to all Rooms. Each Room has 4 possible Doors and
 * 4 possible neighboring Rooms.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public abstract class AbstractRoom implements Serializable {

    /** Door A of this Room. */
    private Door doorA;

    /** Door B of this Room. */
    private Door doorB;

    /** Door C of this Room. */
    private Door doorC;

    /** Door D of this Room. */
    private Door doorD;

    /** The Room linked to this Room by Door A. */
    private Room roomA;

    /** The Room linked to this Room by Door B. */
    private Room roomB;

    /** The Room linked to this Room by Door C. */
    private Room roomC;

    /** The Room linked to this Room by Door D. */
    private Room roomD;

    /** The unique integer ID of this Room. */
    private final int myID;

    /** The GameMap of this Room. */
    private GameMap gm;


    /**
     * Constructs a Room with the given integer ID.
     *
     * @param theID The integer ID of this Room.
     */
    public AbstractRoom(final int theID) {
        myID = theID;
        gm = new GameMap(myID);
    }


    /**
     * Returns the GameMap for this Room.
     *
     * @return The GameMap for this Room.
     */
    public GameMap getMap() {
        return gm;
    }


    /**
     * Returns the integer ID of for this Room.
     *
     * @return The integer ID for this Room.
     */
    public int getRoomID() {
        return myID;
    }


    /**
     * Returns the Door specified by the given letter ID.
     *
     * @param letterID The letter representing the Door to return.
     * @return The Door specified by letterID.
     */
    public Door getDoor(final String letterID) {
        return switch (letterID) {
            case "A" -> doorA;
            case "B" -> doorB;
            case "C" -> doorC;
            case "D" -> doorD;
            default -> null;
        };
    }


    /**
     * Returns the room specified by the given letter ID.
     *
     * @param letterID A letter representing the Room to return.
     * @return the Room specified by letterID.
     */
    public Room getRoom(final String letterID) {
        return switch (letterID) {
            case "A" -> roomA;
            case "B" -> roomB;
            case "C" -> roomC;
            case "D" -> roomD;
            default -> null;
        };
    }


    /**
     * Checks to see if the specified room exists.
     *
     * @param theRoomName the room to check.
     * @return true if room exists, false otherwise.
     */
    public boolean hasRoom(final String theRoomName) {
        return switch (theRoomName) {
            case "A" -> roomA != null;
            case "B" -> roomB != null;
            case "C" -> roomC != null;
            case "D" -> roomD != null;
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
        roomA = room;
        doorA = door;
    }


    /**
     * Sets Room B and Door B to the given Room and Door.
     *
     * @param room Room B of this Room.
     * @param door Door B separating this Room and the Room B.
     */
    public void setB(final Room room, final Door door) {
        roomB = room;
        doorB = door;
    }


    /**
     * Sets Room C and Door C to the given Room and Door.
     *
     * @param room Room C of this Room.
     * @param door Door C separating this Room and the Room C.
     */
    public void setC(final Room room, final Door door) {
        roomC = room;
        doorC = door;
    }


    /**
     * Sets Room D and Door D to the given Room and Door.
     *
     * @param room Room D of this Room.
     * @param door Door D separating this Room and Room D.
     */
    public void setD(final Room room, final Door door) {
        roomD = room;
        doorD = door;
    }

}
