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
 * The main user profile for the player. Will contain details like character name, etc.
 */
public class UserController {

    private static final int MOVEMENT_SPEED = 5;
    private final Player player;
    public int dx, dy;

    /** The terrain grid for the simulation. */
    private final Terrain[][] myGrid;

    public UserController(int theX,
                          int theY,
                          final Direction theDir,
                          final Terrain[][] theGrid) throws IOException {
        this.myGrid = theGrid.clone();
        player = new Player(theX, theY);
        player.setDirection(theDir);
        System.out.println("Grid length: " + myGrid.length + " " + myGrid[0].length);
    }

    public void move(final Direction theDir) {
        player.setDirection(theDir);
        player.setX(player.getX() + dx);
        player.setY(player.getY() + dy);
    }


    public Player getPlayer() {return player;}



    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
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
        }
    }



    private Map<Direction, Terrain> generateNeighbors(final Player theMover) {

        final int div = 100;
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
