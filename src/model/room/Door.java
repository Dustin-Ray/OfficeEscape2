package model.room;

import model.trivia.Trivia;

import java.util.ArrayList;

/**
 * Maintains Door information. A Door is valid if it can be used to pass
 * between two Rooms after being unlocked. A Door is unlocked if a user
 * answers a trivia question correctly.
 *
 * @author Reuben Keller
 */
public class Door {

    //TODO: Assign a Trivia object to the Door

    /** A boolean field to track whether this Door is valid or invalid. */
    private boolean isValid;

    /** A boolean variable to track whether this Door is locked or unlocked. */
    private boolean isUnlocked;

    private ArrayList<Trivia> myTriviaQuestions;


    /**
     * Constructs a Door.
     */
    public Door() {
        isValid = false;
        isUnlocked = false;
    }

    public Door(final boolean valid, final boolean unlocked) {

        isValid = valid;
        isUnlocked = unlocked;
    }


    /**
     * Sets the validity of this Door. A Door is valid if it can be used to pass
     * between two rooms after being unlocked.
     *
     * @param theValue true to make this Door valid and false to make this Door
     *     invalid.
     */
    public void setDoor(final boolean theValue) {
        isValid = theValue;
    }


    /**
     * Unlocks this Door.
     */
    public void unlockDoor() {
        isUnlocked = true;
    }


    /**
     * Locks this Door.
     */
    public void lockDoor() {
        isUnlocked = false;
    }


    /**
     * Checks if this door is valid.
     *
     * @return true if this Door is valid and false otherwise.
     */
    public boolean isValid() {
        return isValid;
    }


    /**
     * Checks if this door has been unlocked.
     *
     * @return true if this Door has been unlocked and false otherwise.
     */
    public boolean isUnlocked() {
        return isUnlocked;
    }
}
