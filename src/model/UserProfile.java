package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static model.Terrain.*;

/**
 * The main user profile for the player. Will contain details like character name, etc.
 */
public class UserProfile {


    private int x;
    private int y;
    public BufferedImage img;
    public final BufferedImage chair_down;
    public final BufferedImage chair_up;
    public final BufferedImage chair_left;
    public final BufferedImage chair_right;
    public int speedKeyX, speedKeyY;
    private Direction myDir;


    public UserProfile(int x, int y, final Direction theDir) throws IOException {
        myDir = theDir;
        this.img = ImageIO.read(new File("src/res/assets/chair/chair_down.png"));
        this.chair_down = ImageIO.read(new File("src/res/assets/chair/chair_down.png"));
        this.chair_up = ImageIO.read(new File("src/res/assets/chair/chair_up.png"));
        this.chair_left = ImageIO.read(new File("src/res/assets/chair/chair_left.png"));
        this.chair_right = ImageIO.read(new File("src/res/assets/chair/chair_right.png"));

        this.x = x;
        this.y = y;
    }

    public Direction getDirection() {
        return myDir;
    }

    public void setDirection(final Direction theDirection) {
        myDir = theDirection;
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


    //GETTERS
    public BufferedImage getImage(){ return this.img; }
    public int getX(){ return this.x; }
    public int getY(){ return this.y; }

    //SETTERS
    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }



}
