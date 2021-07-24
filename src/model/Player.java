package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {

    public BufferedImage img;
    public final BufferedImage chair_down;
    public final BufferedImage chair_up;
    public final BufferedImage chair_left;
    public final BufferedImage chair_right;
    private Direction myDir;
    private int myX;
    private int myY;
    public Player(int theX,
                  int theY) throws IOException {

        this.img = ImageIO.read(new File("src/res/assets/chair/chair_up.png"));
        this.chair_down = ImageIO.read(new File("src/res/assets/chair/chair_down.png"));
        this.chair_up = ImageIO.read(new File("src/res/assets/chair/chair_up.png"));
        this.chair_left = ImageIO.read(new File("src/res/assets/chair/chair_left.png"));
        this.chair_right = ImageIO.read(new File("src/res/assets/chair/chair_right.png"));

        this.myX = theX;
        this.myY = theY;

    }
    public void setDirection(final Direction theDirection) {myDir = theDirection;}

    public Direction getDirection() {return myDir;}


    public int getX(){ return this.myX; }
    public int getY(){ return this.myY; }

    public void setX(final int theX) {this.myX = theX;}

    public void setY(final int theY) {this.myY = theY;}

    public BufferedImage getImage(){ return this.img; }
    public void reset() throws IOException {
        this.img = chair_up;
        this.setX(500);
        this.setY(585);
    }

}
