package controller;

import model.Direction;
import model.Player;
import model.room.Terrain;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static model.room.Terrain.DOOR_CLOSED;
import static model.room.Terrain.DOOR_OPEN;

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
    }

    public void move() {
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
            this.move();
        }
    }

    private boolean isValidIndex(final int theY, final int theX) {
        return 0 <= theY && theY < myGrid.length
                && 0 <= theX && theX < myGrid[theY].length;
    }

    private Map<Direction, Terrain> generateNeighbors(final Player theMover) {
        final int x = theMover.getX();
        final int y = theMover.getY();
        final Map<Direction, Terrain> result = new HashMap<>();

        System.out.println(result);
        for (final Direction dir : Direction.values()) {
            if (isValidIndex(y + dir.dy(), x + dir.dx())) {
                result.put(dir, myGrid[y + dir.dy()][x + dir.dx()]);
            }
        }
        return Collections.unmodifiableMap(result);
    }

    public boolean canPass(final Terrain theTerrain) {
        System.out.println(theTerrain);
        return !(theTerrain == DOOR_OPEN ||
                theTerrain == DOOR_CLOSED);
    }
}
