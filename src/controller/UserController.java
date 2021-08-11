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

    /** The terrain grid for the simulation. Square size defined by terrain grid.  */
    private final Terrain[][] myGrid;

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

    private boolean nextToDoorA;

    private boolean nextToDoorB;

    private boolean nextToDoorC;

    private boolean nextToDoorD;

    /**
     * Constructor.
     * @param theDir starting facing direction for player object.
     * @param theGrid is the terrain that the player object will interact with.
     * @throws IOException if player object cannot load a given resource.
     */
    public UserController(final Player thePlayer,
                          final GameMap theGM,
                          final Direction theDir,
                          final Terrain[][] theGrid) {
        myPcs = new PropertyChangeSupport(this);
        myNextToDoor = false;
        this.myGrid = theGM.getTerrainGrid();
        System.out.println(myGrid);
        myGM = theGM;
        myPlayer = thePlayer;
    }


    /**
     * Checks if the Player collides with any Obstacles in the Map.
     *
     * @return true if Player collides with an Obstacle and false otherwise
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
    }


    /**
     * Handles key pressed events.
     *
     * @param event is an int value of the current key event value.
     */
    public void keyPressed(final KeyEvent event) {
        int key = event.getKeyCode();
        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_KP_LEFT) {
            myPlayer.setVelX(-MOVEMENT_SPEED);
            myPlayer.setDirection(Direction.WEST);
            myPlayer.setSprite(myPlayer.chairLeft);
        } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_KP_RIGHT) {
            myPlayer.setVelX(MOVEMENT_SPEED);
            myPlayer.setDirection(Direction.EAST);
            myPlayer.setSprite(myPlayer.chairRight);
        } else if (key == KeyEvent.VK_DOWN|| key == KeyEvent.VK_KP_DOWN) {
            myPlayer.setVelY(MOVEMENT_SPEED);
            myPlayer.setDirection(Direction.SOUTH);
            myPlayer.setSprite(myPlayer.chairDown);
        }else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_KP_UP) {
            myPlayer.setVelY(-MOVEMENT_SPEED);
            myPlayer.setDirection(Direction.NORTH);
            myPlayer.setSprite(myPlayer.chairUp);
        }
        if (key == KeyEvent.VK_E) {
            myLoadGameFlag = true;
        }
    }

    /**
     * Handles a key release event. Used to stop movement of the
     * player sprite and also sets the load game value to false if user
     * if not pressing the "e" key.
     * @param event is the key released event.
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
            myLoadGameFlag = true;
        }
    }


    public void checkDoorProximity() {
        if (collisionWith(myGM.doorAPositions())) {
            nextToDoorA = true;
            fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_A);
//            System.out.println("By door A");
        } else if (collisionWith(myGM.doorBPositions())) {
            nextToDoorB = true;
            fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_B);
//            System.out.println("By door B");
        } else if (collisionWith(myGM.doorCPositions())) {
            nextToDoorC = true;
            fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_C);
//            System.out.println("By door C");
        } else if (collisionWith(myGM.doorDPositions())) {
            nextToDoorD = true;
            fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_D);
//            System.out.println("By door D");
        } else {
            nextToDoorA = false;
            nextToDoorB = false;
            nextToDoorC = false;
            nextToDoorD = false;
        }
    }



//    /**
//     * Generates a map of the current terrain surrounding the player sprite.
//     * @return each cardinal direction (NWES) and its current terrain in relation
//     * to the player sprite.
//     */
//    private Map<Direction, Terrain> generateNeighbors() {
//        int playerTileX = (myPlayer.getX() / GameMap.TILE_WIDTH) + 1;
//        int playerTileY = (myPlayer.getY() / GameMap.TILE_HEIGHT) + 1;
//        int arrPosX = playerTileX - 1;
//        int arrPosY = playerTileY - 1;
//        if (arrPosY - 1 < 0) {
//            arrPosY += 1;
//        }
//        final Map<Direction, Terrain> result = new HashMap<>();
//        for (int i = 0; i < Direction.values().length; i++) {
//            //uses the x y position of the sprite to access the elements of the terrain array.
//            result.put(Direction.NORTH, myGrid[arrPosY - 1][arrPosX]);
//            result.put(Direction.SOUTH, myGrid[arrPosY + 1][arrPosX]);
//            result.put(Direction.EAST, myGrid[arrPosY][arrPosX - 1]);
//            result.put(Direction.WEST, myGrid[arrPosY][arrPosX + 1]);
//            myPositions = "Y pos: " + playerTileY + "\t" + "X pos: " + playerTileX;
//        }
//
//
//        //helper code to fire debug info to console
//        myNeighbors = "Surrounding terrain: \n";
//        for (int j = 0; j < 4; j++) {
//            Set<Direction> s = result.keySet();
//            Object[] sArr = s.toArray();
//            myNeighbors = myNeighbors +
//                    sArr[j].toString() +
//                    ":    "
//                    + result.get(sArr[j])
//                    + "\n";
//        }
//        return Collections.unmodifiableMap(result);
//    }

//
//    /**
//     * Valid terrain that the sprite is allowed to move on.
//     * @param theTerrain is the terrain to check for validity.
//     * @return boolean determining if terrain passed in is valid to move on.
//     */
//    public boolean canPass(final Terrain theTerrain) {return !(theTerrain == OBSTACLE);}
//
//    /**
//     * Checks proximity to doors and other elements that can be interacted with.
//     * Moves player sprite if interaction is valid.
//     */
//    public void advance() {
//        final Map<Direction, Terrain> neighbors = generateNeighbors();
//        if(this.canPass(neighbors.get(myPlayer.getDirection()))) {
//            if (neighbors.get(myPlayer.getDirection()) == DOOR_CLOSED_A) {
//                myNextToDoor = true;
//                fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_A);
//            } else if (neighbors.get(myPlayer.getDirection()) == DOOR_CLOSED_B) {
//                myNextToDoor = true;
//                fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_B);
//            }
//            else if (neighbors.get(myPlayer.getDirection()) == DOOR_CLOSED_C) {
//                myNextToDoor = true;
//                fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_C);
//            }
//            else if (neighbors.get(myPlayer.getDirection()) == DOOR_CLOSED_D) {
//                myNextToDoor = true;
//                fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_D);
//            }
//            else {
//                myNextToDoor = false;
//                fireXYPositionChange();
//                fireNeighborChange();
//            }
//        }
//    }


    /**
     * Gets player object for this class.
     * @return Current player object for this class.
     */
    public Player getMyPlayer() {return myPlayer;}

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


}
