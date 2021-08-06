/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Dustin Ray, Raz Consta, Reuben Keller
 */

package model.room;

import model.trivia.Trivia;

/**
 * Maintains Door information. A Door is valid if it can be used to pass
 * between two Rooms after being unlocked. A Door is unlocked if a user
 * answers a trivia question correctly.
 *
 * @author Reuben Keller
 */
public class Door {


    /** A boolean variable to track whether this Door is valid or invalid. */
    private boolean isValid;

    /** A boolean variable to track whether this Door is locked or unlocked. */
    private boolean isUnlocked;

    private Trivia myTrivia;

    /** Constructs a Door. */
    public Door() {
        isValid = false;
        isUnlocked = false;
    }

    public Door(final boolean valid,
                final boolean unlocked,
                final Trivia theTrivia) {
        myTrivia = theTrivia;
        isValid = valid;
        isUnlocked = unlocked;

    }

    /**
     * Sets the validity of this Door. A Door is valid if it can be used to pass
     * between two rooms after being unlocked.
     * @param theValue true to make this Door valid and false to make this Door
     *     invalid.
     */
    public void setDoor(final boolean theValue) {
        isValid = theValue;
    }

     /** Unlocks this Door. */
    public void unlockDoor() {
        isUnlocked = true;
    }
    /** Locks this Door. */
    public void lockDoor() {
        isUnlocked = false;
    }

    /** Returns Trivia object for this door. */
    public Trivia getTrivia() {return myTrivia;}

    /**
     * Checks if this door is valid.
     * @return true if this Door is valid and false otherwise.
     */
    public boolean isValid() {
        return isValid;
    }

    /**
     * Checks if this door has been unlocked.
     * @return true if this Door has been unlocked and false otherwise.
     */
    public boolean isUnlocked() {
        return isUnlocked;
    }
}
