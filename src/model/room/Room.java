/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Dustin Ray, Raz Consta, Reuben Keller
 */

package model.room;

import java.util.Objects;

/**
 * Represents a Room.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class Room extends AbstractRoom {

    /**
     * Constructs a Room with the given ID.
     *
     * @param theID The ID of this Room.
     */
    public Room(final int theID) {
        super(theID);
    }


    @Override
    public boolean equals(final Object other) {
        boolean result = false;
        if ((other != null) && (other.getClass().equals(this.getClass()))) {
            Room o = (Room) other;
            result = getRoomID() == o.getRoomID();
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