package controller;

import model.Direction;
import model.Player;
import model.room.Terrain;

import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static model.room.Terrain.*;

/**
 * Controls attributes for player character. Communicates with RoomPanel via key listener.
 * @author Dustin Ray
 */
public class UserController implements PropertyChangeEnabledUserControls {

    /** Movement speed of player sprite. */
    private static final int MOVEMENT_SPEED = 3;
    /** Object representing player character. */
    private final Player player;
    /**Values uses to represent change in x and y positioning during key
     * pressed/released event. */
    private int dx, dy;
    /**Triggers option for trivia event if true. */
    private boolean myNextToDoor;
    /** The terrain grid for the simulation. 8 x 8 square with each square 96 x 96 pixels. */
    private final Terrain[][] myGrid;
    /**Property change support manager for this object. Used to fire changes to listeners.  */
    private final PropertyChangeSupport myPcs;
    /** A value used to determine if the player wants to enter the next room. */
    private boolean myLoadGameFlag;
    /** The value used to calculate proximity to terrain in getNeighbors method.
     * Can be changed to fit different asset pixel sizes. */
    private int myDiv;
    /** Used for debugging, fires to console panel so sprite position can be determined. */
    private String myPositions;
    /**String representation of neighbors surrounding the player sprite.  */
    private String myNeighbors;



    /**
     * Constructor.
     * @param theX starting x value for player object.
     * @param theY starting y value for player object.
     * @param theDir starting facing direction for player object.
     * @param theGrid is the terrain that the player object will interact with.
     * @throws IOException if player object cannot load a given resource.
     */
    public UserController(int theX,
                          int theY,
                          final Direction theDir,
                          final Terrain[][] theGrid) throws IOException {

        myDiv = 0;
        myPcs = new PropertyChangeSupport(this);
        myNextToDoor = false;
        this.myGrid = theGrid.clone();
        player = new Player(theX, theY);
        player.setDirection(theDir);
    }

    /**
     * Generates a map of the current terrain surrounding the player sprite.
     * @param theMover is the current player sprite.
     * @return each cardinal direction (NWES) and its current terrain in relation
     * to the player sprite.
     */
    private Map<Direction, Terrain> generateNeighbors(final Player theMover) {

        //the current tile size
        myDiv = 48;
        final int x = Math.abs(theMover.getX());
        final int y = Math.abs(theMover.getY());
        final Map<Direction, Terrain> result = new HashMap<>();
        for (int i = 0; i < Direction.values().length; i++) {
            //uses the x y position of the sprite to access the elements of the terrain array.
            result.put(Direction.NORTH, myGrid[(y / myDiv) -1][(x / myDiv)]);
            result.put(Direction.SOUTH, myGrid[(y / myDiv)][(x / myDiv)]);
            result.put(Direction.EAST, myGrid[(y / myDiv)][(x / myDiv)]);
            result.put(Direction.WEST, myGrid[(y / myDiv)][(x / myDiv)- 1]);
            myPositions = "Y pos: " + ((y / myDiv)) + "\t" + "X pos: " + ((x / myDiv));
        }
        //helper code to fire debug info to console
        myNeighbors = "Surrounding terrain: \n";
        for (int j = 0; j < 4; j++) {
            Set<Direction> s = result.keySet();
            Object[] sArr = s.toArray();
            myNeighbors = myNeighbors +
                        sArr[j].toString() +
                        ":    "
                        + result.get(sArr[j])
                        + "\n";
        }
        return Collections.unmodifiableMap(result);
    }

    /**
     * Handles key pressed event.
     * @param theKeyEvent is an int value of the current key event value.
     */
    public void keyPressed(KeyEvent theKeyEvent) {
        int key = theKeyEvent.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            player.setDirection(Direction.WEST);
            this.player.setMyPlayerSprite(player.chair_left);
            dx = -MOVEMENT_SPEED;}
        if (key == KeyEvent.VK_RIGHT) {
            player.setDirection(Direction.EAST);
            this.player.setMyPlayerSprite(player.chair_right);
            dx = MOVEMENT_SPEED;}
        if (key == KeyEvent.VK_UP) {
            player.setDirection(Direction.NORTH);
            this.player.setMyPlayerSprite(player.chair_up);
            dy = -MOVEMENT_SPEED;}
        if (key == KeyEvent.VK_DOWN) {
            player.setDirection(Direction.SOUTH);
            this.player.setMyPlayerSprite(player.chair_down);
            dy = MOVEMENT_SPEED;}
        if (key == KeyEvent.VK_E) {
            myLoadGameFlag = true;}
    }

    /**
     * Handles a key release event. Used to stop movement of the
     * player sprite and also sets the load game value to false if user
     * if not pressing the "e" key.
     * @param e is the key released event.
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {dx = 0;}
        if (key == KeyEvent.VK_RIGHT) {dx = 0;}
        if (key == KeyEvent.VK_UP) {dy = 0;}
        if (key == KeyEvent.VK_DOWN) {dy = 0;}
        if (key == KeyEvent.VK_E) {
            myLoadGameFlag = false;}
    }


    /**
     * Moves the player object on the grid and sets direction according to
     * current key press.
     * @param theDir direction that the player object should be oriented in
     *               according to current key press.
     */
    public void move(final Direction theDir) {
        player.setDirection(theDir);
        player.setX(player.getX() + dx);
        player.setY(player.getY() + dy);
//        System.out.println(myLoadGameFlag);
    }

    /**
     * Valid terrain that the sprite is allowed to move on.
     * @param theTerrain is the terrain to check for validity.
     * @return boolean determining if terrain passed in is valid to move on.
     */
    public boolean canPass(final Terrain theTerrain) {

        return !(theTerrain == RED_ZONE);
    }

    /**
     * Checks proximity to doors and other elements that can be interacted with.
     * Moves player sprite if interaction is valid.
     */
    public void advance() {
        final Map<Direction, Terrain> neighbors = generateNeighbors(player);
        if(this.canPass(neighbors.get(player.getDirection()))) {

            if (neighbors.get(player.getDirection()) == DOOR_CLOSED_A) {
                myNextToDoor = true;
                fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_A);
            } else if (neighbors.get(player.getDirection()) == DOOR_CLOSED_B) {
                myNextToDoor = true;
                fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_B);
            }
            else if (neighbors.get(player.getDirection()) == DOOR_CLOSED_C) {
                myNextToDoor = true;
                fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_C);
            }
            else if (neighbors.get(player.getDirection()) == DOOR_CLOSED_D) {
                myNextToDoor = true;
                fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_D);
            }
            else {
                myNextToDoor = false;
                fireXYPositionChange();
                fireNeighborChange();
            }
            this.move(player.getDirection());
        }
    }


    /**
     * Gets player object for this class.
     * @return Current player object for this class.
     */
    public Player getPlayer() {return player;}

    /**
     * Gets the value of the current load game flag. Used so that the player can
     * press the "e" key on the keyboard to load the next room.
     * @return boolean value of current load game flag. True is user is pressing and
     * holding the "e" key, false otherwise.
     */
    public boolean getMyLoadGameFlag() {return myLoadGameFlag;}

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

    /**
     * Adds a property change listener.
     * @param thePropertyName is the name of the listener to add.
     * @param theListener is the listener to add.
     */
    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);
    }

    /**
     * Removes a property change listener.
     * @param thePropertyName is the name of the listener to remove.
     * @param theListener is the listener to remove.
     */
    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);
    }
}
