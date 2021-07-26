package model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class to represent the player sprite that moves through the game terrain.
 * @author Dustin Ray.
 * @version Summer 2021
 */
public class Player {

    /** Image file representing the current player sprite. */
    public BufferedImage myPlayerSprite;
    /** Image file representing the current player sprite. */
    public final BufferedImage chair_down;
    /** Image file representing the current player sprite. */
    public final BufferedImage chair_up;
    /** Image file representing the current player sprite. */
    public final BufferedImage chair_up_question;
    /** Image file representing the current player sprite. */
    public final BufferedImage chair_left;
    /** Image file representing the current player sprite. */
    public final BufferedImage chair_right;
    /** The current direction that the sprite is facing. */
    private Direction myDir;
    /** Current x position of sprite. */
    private int myX;
    /** Current Y position of sprite. */
    private int myY;

    /**
     * Constructor. Initializes player object to starting state.
     * @param theX initial X position.
     * @param theY initial Y position.
     * @throws IOException if any resources fail to load.
     */
    public Player(int theX,
                  int theY) throws IOException {

        this.myPlayerSprite = ImageIO.read(new File("src/res/assets/chair/testing/chair_up_testing.png"));
        this.chair_down = ImageIO.read(new File("src/res/assets/chair/testing/chair_down_testing.png"));
        this.chair_up = ImageIO.read(new File("src/res/assets/chair/testing/chair_up_testing.png"));
        this.chair_up_question = ImageIO.read(new File("src/res/assets/chair/testing/chair_up_testing_question.png"));
        this.chair_left = ImageIO.read(new File("src/res/assets/chair/testing/chair_left_testing.png"));
        this.chair_right = ImageIO.read(new File("src/res/assets/chair/testing/chair_right_testing.png"));

        this.myX = theX;
        this.myY = theY;

    }

    /**
     * Sets the direction of the sprite as needed.
     * @param theDirection is the direction to face.
     */
    public void setDirection(final Direction theDirection) {myDir = theDirection;}

    /** returns the current player object */
    public Direction getDirection() {return myDir;}

    /** Sets the player sprite image as needed.
     * @param theImage is a string that represents the type of image to
     *                 set the sprite to.
     *                 */
    public void setMyPlayerSprite(final String theImage) {
        if (theImage.equals("UP")) {this.myPlayerSprite = chair_up;}
        if (theImage.equals("UP?")) {this.myPlayerSprite = chair_up_question;}
        if (theImage.equals("DOWN")) {this.myPlayerSprite = chair_down;}
        if (theImage.equals("LEFT")) {this.myPlayerSprite = chair_left;}
        if (theImage.equals("RIGHT")) {this.myPlayerSprite = chair_right;}
    }

    /** Gets the X position for this player object. */
    public int getX(){ return this.myX; }
    /** Gets the Y position for this player object. */
    public int getY(){ return this.myY; }
    /** Sets the X position for this player object. */
    public void setX(final int theX) {this.myX = theX;}
    /** Sets the X position for this player object. */
    public void setY(final int theY) {this.myY = theY;}
    /** Gets the current image for this player object. */
    public BufferedImage getImage(){ return this.myPlayerSprite; }

    /** Resets the sprite to initial conditions.*/
    public void reset() {
        this.myPlayerSprite = chair_up;
        this.setX(288);
        this.setY(384);
    }

}
