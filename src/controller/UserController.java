package controller;

import model.Direction;
import model.Player;
import model.room.Terrain;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static model.room.Terrain.*;

/**
 * Controls attributes for player character. Communicates with RoomPanel via key listener.
 * @author Dustin Ray
 */
public class UserController {

    /** Movement speed of player sprite. */
    private static final int MOVEMENT_SPEED = 5;
    /** Object representing player character. */
    private final Player player;
    /**Values uses to represent change in x and y positing during key
     * pressed/released event. */
    public int dx, dy;

    /** The terrain grid for the simulation. 8 x 8 square with each square 96 x 96 pixels. */
    private final Terrain[][] myGrid;

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
        this.myGrid = theGrid.clone();
        player = new Player(theX, theY);
        player.setDirection(theDir);
        System.out.println("Grid length: " + myGrid.length + " " + myGrid[0].length);
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
            this.player.img = player.chair_left;
            dx = -MOVEMENT_SPEED;}
        if (key == KeyEvent.VK_RIGHT) {
            player.setDirection(Direction.EAST);
            this.player.img = player.chair_right;
            dx = MOVEMENT_SPEED;}
        if (key == KeyEvent.VK_UP) {
            player.setDirection(Direction.NORTH);
            this.player.img = player.chair_up;
            dy = -MOVEMENT_SPEED;}
        if (key == KeyEvent.VK_DOWN) {
            player.setDirection(Direction.SOUTH);
            this.player.img = player.chair_down;
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
            if(neighbors.get(Direction.NORTH) == DOOR_CLOSED_A ||
                neighbors.get(Direction.NORTH) == DOOR_CLOSED_B ||
                neighbors.get(Direction.NORTH) == DOOR_CLOSED_C ||
                neighbors.get(Direction.NORTH) == DOOR_CLOSED_D) {
                    player.setImg("UP?");
            }
            //create a boolean value to be used as a test as whether to open a trivia question
        }
    }

    private Map<Direction, Terrain> generateNeighbors(final Player theMover) {

        final int div = 96;
        final int x = theMover.getX();
        final int y = theMover.getY();
        final Map<Direction, Terrain> result = new HashMap<>();
        for (int i = 0; i < Direction.values().length; i++) {
            result.put(Direction.NORTH, myGrid[(y / div)][(x / div)]);
            result.put(Direction.SOUTH, myGrid[(y / div) + 1][(x / div)]);
            result.put(Direction.EAST, myGrid[(y / div) + 1][(x / div)]);
            result.put(Direction.WEST, myGrid[(y / div) + 1][(x / div)]);
        }
        System.out.println(result);
        return Collections.unmodifiableMap(result);
    }

    public boolean canPass(final Terrain theTerrain) {
        return (theTerrain == FLOOR_1);
    }
}
