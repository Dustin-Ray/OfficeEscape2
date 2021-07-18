package model;

/**
 * Maintains door information.
 *
 * @author Reuben Keller
 */
public class Door {
    //TODO: Assign a Trivia object to the Door

    private boolean isValid;

    public Door() {
        isValid = false;
    }

    public void setDoor(final boolean theValue) {
        isValid = theValue;
    }
}
