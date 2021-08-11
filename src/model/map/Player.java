package model.map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Class to represent the player sprite that moves through the game terrain.
 *
 * @author Dustin Ray
 * @author Reuben Keller
 * @version Summer 2021
 */
public class Player extends MapEntity {


    /** The file path for the Player sprite images. */
    public static final String PATH = "src/res/assets/chair/testing/";

    /** The default x position of this PLayer. */
    private static final int DEFAULT_X = 288;

    /** The default y position of this Player. */
    private static final int DEFAULT_Y = 384;

    /** The buffered image of the current player sprite. */
    private BufferedImage myPlayerSprite;

    /** Image file representing the current player sprite. */
    public BufferedImage chairDown;

    /** Image file representing the current player sprite. */
    public BufferedImage chairUp;

    /** Image file representing the current player sprite. */
    private BufferedImage chairUpQuestion;

    /** Image file representing the current player sprite. */
    public BufferedImage chairLeft;

    /** Image file representing the current player sprite. */
    public BufferedImage chairRight;

    /** The Direction of this player. */
    private Direction myDir;


    /**
     * Constructor. Initializes player object to starting state.
     * @param theX initial X position.
     * @param theY initial Y position.
     * @throws IOException if any resources fail to load.
     */
    public Player(final int theX, final int theY) {
        super(theX, theY);
        readFiles();
    }



    /**
     * Reads in the image files for this sprite.
     */
    private void readFiles() {
        try {
            setSprite(ImageIO.read(new File(PATH + "chair_up_testing.png")));
            chairDown = ImageIO.read(new File(PATH + "chair_down_testing.png"));
            chairUp = ImageIO.read(new File(PATH + "chair_up_testing.png"));
            chairUpQuestion = ImageIO.read(new File(PATH + "chair_up_testing_question.png"));
            chairLeft = ImageIO.read(new File(PATH + "chair_left_testing.png"));
            chairRight = ImageIO.read(new File(PATH + "chair_right_testing.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Sets the direction of the sprite as needed.
     * @param theDirection is the direction to face.
     */
    public void setDirection(final Direction theDirection) {
        myDir = theDirection;
    }


    /** returns the current player object */
    public Direction getDirection() {
        return myDir;
    }


    /** Sets the player sprite image as needed.
     * @param theImage is a string that represents the type of image to
     *                 set the sprite to.
     *                 */
    public void setSprite(final String theImage) {
        if (theImage.equals("UP")) {
            this.setSprite(chairUp);}
        if (theImage.equals("UP?")) {
            this.setSprite(chairUpQuestion());}
        if (theImage.equals("DOWN")) {
            this.setSprite(chairDown);}
        if (theImage.equals("LEFT")) {
            this.setSprite(chairLeft);}
        if (theImage.equals("RIGHT")) {
            this.setSprite(chairRight);}
    }

    /** Resets the sprite to initial conditions.*/
    public void reset() {
        this.setSprite(chairUp);
        this.setX(DEFAULT_X);
        this.setY(DEFAULT_Y);
    }

    /** Sets the X position for this player object. */
    public void setX(final int theX) {
        this.myX = theX;
    }

    /** Sets the X position for this player object. */
    public void setY(final int theY) {
        this.myY = theY;
    }

    /** Sets the current image for this sprite. */
    public void setSprite(BufferedImage myPlayerSprite) {
        this.myPlayerSprite = myPlayerSprite;
    }

    /** Image file representing the current player sprite when in proximity to a door or trivia event.
      * @return BufferedImage representing the player sprite for this player object. */
    public BufferedImage chairUpQuestion() {return chairUpQuestion;}

    /** Gets the current image for this player object.
     * @return BufferedImage representing the player sprite for this player object. */
    public BufferedImage getPlayerSprite() {return myPlayerSprite;}

}
