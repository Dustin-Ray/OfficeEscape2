/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Dustin Ray, Raz Consta, Reuben Keller
 */

package controller;

import model.map.*;

import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.*;

import static model.map.Terrain.*;

/**
 * Controls attributes for player character. Communicates with RoomPanel via key listener.
 *
 * @author Dustin Ray
 * @author Reuben Keller
 */
public class UserController implements PropertyChangeEnabledUserControls {

    /** Movement speed of player sprite. */
    private static final int MOVEMENT_SPEED = 3;

    /** Object representing player character. */
    private final Player myPlayer;

    /**Triggers option for trivia event if true. */
    private boolean myNextToDoor;

    /**Property change support manager for this object. Used to fire changes to listeners.  */
    private final PropertyChangeSupport myPcs;

    /** A value used to determine if the player wants to enter the next room. */
    private boolean myLoadGameFlag;

    /** Used for debugging, fires to console panel so sprite position can be determined. */
    private String myPositions;

    /**String representation of neighbors surrounding the player sprite.  */
    private String myNeighbors;

    /** The GameMap the Player is in. */
    private final GameMap myGM;


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
            myPlayer.setDirection(Direction.WEST);
            myPlayer.setSprite(myPlayer.chairLeft);
        }
        if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_KP_RIGHT) {
            myPlayer.setVelX(MOVEMENT_SPEED);
            myPlayer.setDirection(Direction.EAST);
            myPlayer.setSprite(myPlayer.chairRight);
        }
        if (key == KeyEvent.VK_DOWN|| key == KeyEvent.VK_KP_DOWN) {
            myPlayer.setVelY(MOVEMENT_SPEED);
            myPlayer.setDirection(Direction.SOUTH);
            myPlayer.setSprite(myPlayer.chairDown);
        }
        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_KP_UP) {
            myPlayer.setVelY(-MOVEMENT_SPEED);
            myPlayer.setDirection(Direction.NORTH);
            myPlayer.setSprite(myPlayer.chairUp);
        }
        if (key == KeyEvent.VK_E) {
            myLoadGameFlag = true;
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
    }

    /**
     * Checks if the Player collides with any Obstacles in the Map.
     *
     * @return true if the Player collides with an Obstacle and false otherwise
     */
    private boolean collisionWith(List<MapEntity> obstacles) {
        for (MapEntity obstacle : obstacles) {
            if (myPlayer.collidesWith(obstacle)) {
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
        if (collisionWith(myGM.getObstacles()) || myPlayer.outOfBounds()) {
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
        generateNeighbors();
        if (collisionWith(myGM.doorAPositions())) {
            fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_A);
            myNextToDoor = true;
        } else if (collisionWith(myGM.doorBPositions())) {
            fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_B);
            myNextToDoor = true;
        } else if (collisionWith(myGM.doorCPositions())) {
            fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_C);
            myNextToDoor = true;
        } else if (collisionWith(myGM.doorDPositions())) {
            fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_D);
            myNextToDoor = true;
        } else {
            myNextToDoor = false;
            fireXYPositionChange();
            fireNeighborChange();
        }
    }


    /**
     * Generates a map of the current Terrain surrounding the Player.
     */
    private void generateNeighbors() {
        int arrPosX = myPlayer.tileX() - 1;
        int arrPosY = myPlayer.tileY() - 1;
        if (arrPosY - 1 < 0) {
            arrPosY += 1;
        }
        if (arrPosX - 1 < 0) {
            arrPosX += 1;
        }
        Terrain[][] grid = myGM.getTerrainGrid();
        final Map<Direction, Terrain> result = new HashMap<>();
        for (int i = 0; i < Direction.values().length; i++) {
            result.put(Direction.NORTH, grid[arrPosY - 1][arrPosX]);
            result.put(Direction.SOUTH, grid[arrPosY + 1][arrPosX]);
            result.put(Direction.EAST, grid[arrPosY][arrPosX - 1]);
            result.put(Direction.WEST, grid[arrPosY][arrPosX + 1]);
            myPositions = "Y pos: " + myPlayer.tileY() + "\t"
                    + "X pos: " + myPlayer.tileX();
        }

        //helper code to fire debug info to console
        myNeighbors = "Surrounding terrain: \n";
        for (int j = 0; j < result.size(); j++) {
            Set<Direction> s = result.keySet();
            Object[] sArr = s.toArray();
            myNeighbors = myNeighbors +
                    sArr[j].toString() +
                    ":    "
                    + result.get(sArr[j])
                    + "\n";
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
     * Fires a property change when the player sprite is in proximity to a door.
     * @param thePropertyChange is the current event of the player being next to a door.
     */
    private void fireProximityChangeDoor(final String thePropertyChange) {
        myPcs.firePropertyChange(thePropertyChange, null, myNextToDoor);
    }


    /** Fires a property change when the player sprite position changes. */
    private void fireXYPositionChange() {
        myPcs.firePropertyChange(PropertyChangeEnabledUserControls.XY_POSITION, null, myPositions);
    }


    /** Fires a property change when the terrain surrounding the sprite changes. */
    private void fireNeighborChange() {
        myPcs.firePropertyChange(PropertyChangeEnabledUserControls.NEIGHBOR_CHANGE, null, myNeighbors);
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
