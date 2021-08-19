/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.room;

import java.io.Serial;

/**
 * Represents a Room. Extends AbstractRoom so it can be made into a unique type
 * of AbstractRoom in the future. (e.g., We could potentially have a MagicRoom,
 * ZombieRoom, etc., with extended behavior.)
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class Room extends AbstractRoom {

    @Serial
    private static final long serialVersionUID = -7030346659383444765L;

    /**
     * Constructs a Room with the given ID.
     *
     * @param theID The ID of this Room.
     */
    public Room(final int theID) {
        super(theID);
    }



}