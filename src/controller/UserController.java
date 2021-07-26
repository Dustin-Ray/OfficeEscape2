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

import static model.room.Terrain.*;

/**
 * Controls attributes for player character. Communicates with RoomPanel via key listener.
 * @author Dustin Ray
 */
public class UserController implements PropertyChangeEnabledUserControls {

    /** Movement speed of player sprite. */
    private static final int MOVEMENT_SPEED = 8;
    /** Object representing player character. */
    private final Player player;
    /**Values uses to represent change in x and y positioning during key
     * pressed/released event. */
    private int dx, dy;

    /**Triggers option for trivia event if true. */
    private boolean myNextToDoor;

    /** The terrain grid for the simulation. 8 x 8 square with each square 96 x 96 pixels. */
    private final Terrain[][] myGrid;

    private final PropertyChangeSupport myPcs;




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

        myPcs = new PropertyChangeSupport(this);
        myNextToDoor = false;
        this.myGrid = theGrid.clone();
        player = new Player(theX, theY);
        player.setDirection(theDir);
        System.out.println("User controller generated");
    }

    /**
     * Gets player object for this class.
     * @return Current player object for this class.
     */
    public Player getPlayer() {return player;}

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
    }

    /**
     * Handles key pressed event.
     * @param theKeyEvent is an int value of the current key event value.
     */
    public void keyPressed(KeyEvent theKeyEvent) {
        int key = theKeyEvent.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            player.setDirection(Direction.WEST);
            this.player.myPlayerSprite = player.chair_left;
            dx = -MOVEMENT_SPEED;}
        if (key == KeyEvent.VK_RIGHT) {
            player.setDirection(Direction.EAST);
            this.player.myPlayerSprite = player.chair_right;
            dx = MOVEMENT_SPEED;}
        if (key == KeyEvent.VK_UP) {
            player.setDirection(Direction.NORTH);
            this.player.myPlayerSprite = player.chair_up;
            dy = -MOVEMENT_SPEED;}
        if (key == KeyEvent.VK_DOWN) {
            player.setDirection(Direction.SOUTH);
            this.player.myPlayerSprite = player.chair_down;
            dy = MOVEMENT_SPEED;}
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {dx = 0;}
        if (key == KeyEvent.VK_RIGHT) {dx = 0;}
        if (key == KeyEvent.VK_UP) {dy = 0;}
        if (key == KeyEvent.VK_DOWN) {dy = 0;}
    }


    public void advance() {
        final Map<Direction, Terrain> neighbors = generateNeighbors(player);
        if(this.canPass(neighbors.get(player.getDirection()))) {
            this.move(player.getDirection());
            if (neighbors.get(Direction.NORTH) == DOOR_CLOSED_A) {
                myNextToDoor = true;
                fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_A);
                player.setMyPlayerSprite("UP?");
            } else if (neighbors.get(Direction.NORTH) == DOOR_CLOSED_B) {
                myNextToDoor = true;
                fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_B);
                player.setMyPlayerSprite("UP?");
            }
            else if (neighbors.get(Direction.NORTH) == DOOR_CLOSED_C) {
                myNextToDoor = true;
                fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_C);
                player.setMyPlayerSprite("UP?");
            }
            else if (neighbors.get(Direction.NORTH) == DOOR_CLOSED_D) {
                myNextToDoor = true;
                fireProximityChangeDoor(PROPERTY_PROXIMITY_DOOR_D);
                player.setMyPlayerSprite("UP?");
            }

            else {
                myNextToDoor = false;
                fireProximityChangeDoor(PROPERTY_PROXIMITY_NO_DOOR);
            }
        }
    }


    private void fireProximityChangeDoor(final String thePropertyChange) {
        myPcs.firePropertyChange(thePropertyChange, null, myNextToDoor);
    }

    private Map<Direction, Terrain> generateNeighbors(final Player theMover) {

        final int div = 96;
        final int x = Math.abs(theMover.getX());
        final int y = Math.abs(theMover.getY());
        final Map<Direction, Terrain> result = new HashMap<>();
        for (int i = 0; i < Direction.values().length; i++) {
            result.put(Direction.NORTH, myGrid[(y / div)][(x / div)]);
            result.put(Direction.SOUTH, myGrid[(y / div) + 1][(x / div)]);
            result.put(Direction.EAST, myGrid[(y / div) + 1][(x / div) + 1]);
            result.put(Direction.WEST, myGrid[(y / div) + 1][(x / div)]);
        }
//        System.out.println(result);
        return Collections.unmodifiableMap(result);
    }

    public boolean canPass(final Terrain theTerrain) {
        return (theTerrain == FLOOR_1);
    }

    @Override
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }


    @Override
    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }

    @Override
    public void addPropertyChangeListener(final String thePropertyName,
                                          final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(thePropertyName, theListener);

    }

    @Override
    public void removePropertyChangeListener(final String thePropertyName,
                                             final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(thePropertyName, theListener);

    }


}
