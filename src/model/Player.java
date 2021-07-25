package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Player {

    public BufferedImage img;
    public final BufferedImage chair_down;
    public final BufferedImage chair_up;
    public final BufferedImage chair_up_question;
    public final BufferedImage chair_left;
    public final BufferedImage chair_right;
    private Direction myDir;
    private int myX;
    private int myY;
    public Player(int theX,
                  int theY) throws IOException {

        this.img = ImageIO.read(new File("src/res/assets/chair/testing/chair_up_testing.png"));
        this.chair_down = ImageIO.read(new File("src/res/assets/chair/testing/chair_down_testing.png"));
        this.chair_up = ImageIO.read(new File("src/res/assets/chair/testing/chair_up_testing.png"));
        this.chair_up_question = ImageIO.read(new File("src/res/assets/chair/testing/chair_up_testing_question.png"));
        this.chair_left = ImageIO.read(new File("src/res/assets/chair/testing/chair_left_testing.png"));
        this.chair_right = ImageIO.read(new File("src/res/assets/chair/testing/chair_right_testing.png"));

        this.myX = theX;
        this.myY = theY;

    }
    public void setDirection(final Direction theDirection) {myDir = theDirection;}

    public Direction getDirection() {return myDir;}

    public void setImg(final String theImage) {

        if (theImage.equals("UP")) {this.img = chair_up;}
        if (theImage.equals("UP?")) {this.img = chair_up_question;}
        if (theImage.equals("DOWN")) {this.img = chair_down;}
        if (theImage.equals("LEFT")) {this.img = chair_left;}
        if (theImage.equals("RIGHT")) {this.img = chair_right;}

    }


    public int getX(){ return this.myX; }
    public int getY(){ return this.myY; }

    public void setX(final int theX) {this.myX = theX;}

    public void setY(final int theY) {this.myY = theY;}

    public BufferedImage getImage(){ return this.img; }
    public void reset() throws IOException {
        this.img = chair_up;
        this.setX(288);
        this.setY(384);
    }

}
