/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package controller;

import model.map.AbstractMapEntity;
import model.map.GameMap;
import model.map.Player;

import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * Controls attributes for player character. Communicates with RoomPanel via key listener.
 *
 * @author Dustin Ray
 * @author Reuben Keller
 * @version Summer 2021
 */
public class UserController implements PropertyChangeEnabledUserControls {

    /** Movement speed of player sprite. */
    private static final int MOVEMENT_SPEED = 10;

    /** Object representing player character. */
    private final Player myPlayer;

    /**Triggers option for trivia event if true. */
    private boolean myNextToDoor;

    /**Property change support manager for this object. Used to fire changes to listeners.  */
    private final PropertyChangeSupport myPcs;

    /** A value used to determine if the player wants to enter the next room. */
    private boolean myLoadGameFlag;

    /** The GameMap the Player is in. */
    private final GameMap myGM;

    /** A flag to tell listeners if the user is pressing q on the keyboard. */
    private boolean myCheatFlag;

    /**
     * Constructor.
     * @param thePlayer is the player object which tracks the sprite image and x y positions.
     * @param theGM is the game map which contains information about terrain and collision.
     */
    public UserController(final Player thePlayer, final GameMap theGM) {
        myPcs = new PropertyChangeSupport(this);
        myNextToDoor = false;
        myGM = theGM;
        myPlayer = thePlayer;
    }

    /**
     * Handles key-pressed events.
     *
     * @param event The KeyEvent triggered by pressing a key.
     */
    public void keyPressed(final KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_KP_LEFT) {
            myPlayer.setVelX(-MOVEMENT_SPEED);
            myPlayer.setSprite('L');
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_KP_RIGHT) {
            myPlayer.setVelX(MOVEMENT_SPEED);
            myPlayer.setSprite('R');
        }
        if (key == KeyEvent.VK_DOWN|| key == KeyEvent.VK_KP_DOWN) {
            myPlayer.setVelY(MOVEMENT_SPEED);
            myPlayer.setSprite('D');
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_KP_UP) {
            myPlayer.setVelY(-MOVEMENT_SPEED);
            myPlayer.setSprite('U');
        }
        if (key == KeyEvent.VK_E) {
            myLoadGameFlag = true;
        }
        if (key == KeyEvent.VK_Q) {
            myCheatFlag = true;
        }
    }


    /**
     * Handles a key-release event. Sets the velocity of a player in a
     * direction to 0 based on the key that's released. Also sets the
     * load game value to false if the user releases the "e" key.
     *
     * @param event The KeyEvent triggered by releasing a key.
     */
    public void keyReleased(KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_KP_LEFT) {
            myPlayer.setVelX(0);
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_KP_RIGHT) {
            myPlayer.setVelX(0);
        }
        if (key == KeyEvent.VK_DOWN|| key == KeyEvent.VK_KP_DOWN) {
            myPlayer.setVelY(0);
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_KP_UP) {
            myPlayer.setVelY(0);
        }
        if (key == KeyEvent.VK_E) {
            myLoadGameFlag = false;
        }
        if (key == KeyEvent.VK_Q) {
            myCheatFlag = false;
        }
    }

    /**
     * Checks if the Player collides with any Obstacles in the Map.
     *
     * @return true if the Player collides with an MapEntity and false otherwise
     */
    private boolean collisionWith(List<AbstractMapEntity> mapEntity) {
        for (AbstractMapEntity entity : mapEntity) {
            if (myPlayer.collidesWith(entity)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Updates the x and y positions of the player.
     */
    public void updatePlayer() {
        int oldX = myPlayer.getX();
        int oldY = myPlayer.getY();
        myPlayer.update();
        int newX = myPlayer.getX();
        int newY = myPlayer.getY();
        if (collisionWith(myGM.obstacleEntities()) || myPlayer.outOfBounds()) {
            newX = oldX;
            newY = oldY;
        }
        myPlayer.setX(newX);
        myPlayer.setY(newY);
        checkDoorProximity();
    }


    /**
     * Checks the proximity of the Player sprite for any Doors.
     */
    private void checkDoorProximity() {
        if (collisionWith(myGM.doorAEntities())) {
            fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_A);
            myNextToDoor = true;
        } else if (collisionWith(myGM.doorBEntities())) {
            fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_B);
            myNextToDoor = true;
        } else if (collisionWith(myGM.doorCEntities())) {
            fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_C);
            myNextToDoor = true;
        } else if (collisionWith(myGM.doorDEntities())) {
            fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_D);
            myNextToDoor = true;
        } else {
            myNextToDoor = false;
            fireXYPositionChange();
            fireNeighborChange();
        }
    }


    /**
     * Gets player object for this class.
     * @return Current player object for this class.
     */
    public Player getMyPlayer() {
        return myPlayer;
    }


    /**
     * Gets the value of the current load game flag. Used so that the player can
     * press the "e" key on the keyboard to load the next room.
     * @return boolean value of current load game flag. True is user is pressing and
     * holding the "e" key, false otherwise.
     */
    public boolean getMyLoadGameFlag() {
        return myLoadGameFlag;
    }

    /**
     * Checks to see if user is pressing q on the keyboard.
     * @return boolean if q is pressed.
     */
    public boolean getCheatFlag() {return myCheatFlag;}

    /**
     * Fires a property change when the player sprite is in proximity to a door.
     * @param thePropertyChange is the current event of the player being next to a door.
     */
    private void fireProximityChangeDoor(final String thePropertyChange) {
        myPcs.firePropertyChange(thePropertyChange, null, myNextToDoor);
    }

    /** Fires a property change when the player sprite position changes. */
    private void fireXYPositionChange() {
        myPcs.firePropertyChange(PropertyChangeEnabledUserControls.XY_POSITION, null, "");
    }

    /** Fires a property change when the terrain surrounding the sprite changes. */
    private void fireNeighborChange() {
        myPcs.firePropertyChange(PropertyChangeEnabledUserControls.NEIGHBOR_CHANGE, null, "");
    }

    /**
     * Adds a property change listener.
     * @param theListener the listen to add.
     */
    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    /**
     * Removes a property change listener.
     * @param theListener the listen to remove.
     */
    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }

}
