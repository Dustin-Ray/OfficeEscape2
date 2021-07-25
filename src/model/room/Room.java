package model.room;

import java.util.Objects;

/**
 * Represents a Room.
 *
 * @author Reuben Keller
 */
public class Room extends AbstractRoom {

    public Room(final int theID) {
        super(theID);
    }

    @Override
    public boolean equals(final Object other) {
        boolean result = false;
        if ((other != null) && (other.getClass().equals(this.getClass()))) {
            Room o = (Room) other;
            result = (this.getRoomID() == ((Room) other).getRoomID());
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getRoomID());
    }
}