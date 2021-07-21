package model;

import model.room.Terrain;

import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static model.room.Terrain.*;

/**
 * The main user profile for the player. Will contain details like character name, etc.
 */
public class UserProfile {

    private static final int MOVEMENT_SPEED = 10;
    private int myX;
    private int myY;
    public BufferedImage img;
    public final BufferedImage chair_down;
    public final BufferedImage chair_up;
    public final BufferedImage chair_left;
    public final BufferedImage chair_right;
    public int dx, dy;
    private Direction myDir;

    public UserProfile(int theX, int theY, final Direction theDir) throws IOException {
        myDir = theDir;
        this.img = ImageIO.read(new File("src/res/assets/chair/chair_up.png"));
        this.chair_down = ImageIO.read(new File("src/res/assets/chair/chair_down.png"));
        this.chair_up = ImageIO.read(new File("src/res/assets/chair/chair_up_question.png"));
        this.chair_left = ImageIO.read(new File("src/res/assets/chair/chair_left.png"));
        this.chair_right = ImageIO.read(new File("src/res/assets/chair/chair_right.png"));
        this.myX = theX;
        this.myY = theY;
    }

    public void move() {
        this.myX += dx;
        this.myY += dy;
    }

    public BufferedImage getImage(){ return this.img; }
    public int getMyX(){ return this.myX; }
    public int getMyY(){ return this.myY; }
    public Direction getDirection() {
        return myDir;
    }

    public void setDirection(final Direction theDirection) {myDir = theDirection;}

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            this.setDirection(Direction.WEST);
            this.img = chair_left;
            dx = -MOVEMENT_SPEED;}
        if (key == KeyEvent.VK_RIGHT) {
            this.setDirection(Direction.EAST);
            this.img = chair_right;
            dx = MOVEMENT_SPEED;}
        if (key == KeyEvent.VK_UP) {
            this.setDirection(Direction.NORTH);
            this.img = chair_up;
            dy = -MOVEMENT_SPEED;}
        if (key == KeyEvent.VK_DOWN) {
            this.setDirection(Direction.SOUTH);
            System.out.println(this.getMyY());
            this.img = chair_down;
            dy = MOVEMENT_SPEED;}
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {dx = 0;}
        if (key == KeyEvent.VK_RIGHT) {dx = 0;}
        if (key == KeyEvent.VK_UP) {dy = 0;}
        if (key == KeyEvent.VK_DOWN) {dy = 0;}
    }


    public boolean canPass(final Terrain theTerrain) {
        return !(theTerrain == BOTTOM_WALL ||
                theTerrain == TOP_WALL||
                theTerrain == LEFT_WALL||
                theTerrain == RIGHT_WALL||
                theTerrain == TOP_LEFT_CORNER||
                theTerrain == TOP_RIGHT_CORNER||
                theTerrain == BOTTOM_LEFT_CORNER||
                theTerrain == BOTTOM_RIGHT_CORNER);
    }

    public void reset() throws IOException {
        this.img = ImageIO.read(new File("src/res/assets/chair/chair_up.png"));
        this.myX = 500;
        this.myY = 585;
    }

}
