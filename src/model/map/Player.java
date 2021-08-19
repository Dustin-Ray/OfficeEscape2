/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.map;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serial;

/**
 * Represents a movable Player in the game.
 *
 * @author Dustin Ray
 * @author Reuben Keller
 * @version Summer 2021
 */
public class Player extends AbstractMapEntity {

    /** The default direction of this Player. */
    public static final char DEFAULT_DIRECTION = 'U';

    /** The file path for the Player sprite images. */
    public static final String PATH = "src/res/assets/chair/";

    /** The default x position of this PLayer. */
    public static final int DEFAULT_X = 384;

    /** The default y position of this Player. */
    public static final int DEFAULT_Y = 384;

    @Serial
    private static final long serialVersionUID = -5429428768135494602L;

    /** A buffered image of the current Player sprite. */
    private BufferedImage mySprite;

    /** A buffered image of the Player sprite facing up. */
    private BufferedImage chairUp;

    /** A buffered image of the Player sprite facing down. */
    private BufferedImage chairDown;

    /** A buffered image of the Player sprite facing left. */
    private BufferedImage chairLeft;

    /** A buffered image of the Player sprite facing right. */
    private BufferedImage chairRight;

    /** The default BufferedImage for this sprite. */
    private BufferedImage defaultImage;


    /**
     * Constructs a Player at the default pixel position (x, y) = (384, 384).
     */
    public Player() {
        this(DEFAULT_X, DEFAULT_Y);
    }


    /**
     * Constructs a Player at the given pixel (x, y) position.
     *
     * @param theX The pixel x position to assign the Player.
     * @param theY The pixel y position to assign the Player.
     */
    public Player(final int theX, final int theY) {
        super(theX, theY);
        readFiles();
    }


    /**
     * Reads in the image files for the Player sprite.
     */
    private void readFiles() {
        try {
            chairDown = ImageIO.read(new File(PATH + "chair_down.png"));
            chairUp = ImageIO.read(new File(PATH + "chair_up.png"));
            chairLeft = ImageIO.read(new File(PATH + "chair_left.png"));
            chairRight = ImageIO.read(new File(PATH + "chair_right.png"));
            mySprite = chairUp;
            defaultImage = chairUp;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Sets the Player sprite and Direction according to the given Direction.
     *
     * @param direction The Direction to set the Player sprite. 'U' for UP, 'D'
     *     for DOWN, 'L' for LEFT, and 'R' for RIGHT.
     * @throws IllegalArgumentException if the given direction is not in
     *     {'U', 'D', 'L', 'R'}
     */
    public void setSprite(final char direction) {
        switch (direction) {
            case 'U' -> mySprite = chairUp;
            case 'D' -> mySprite = chairDown;
            case 'L' -> mySprite = chairLeft;
            case 'R' -> mySprite = chairRight;
            default -> throw new IllegalArgumentException(
                    "was not given a valid direction"
            );
        }
    }


    /** Resets the sprite to initial conditions.*/
    public void reset() {
        this.setSprite(DEFAULT_DIRECTION);
        this.setX(DEFAULT_X);
        this.setY(DEFAULT_Y);
    }



    /**
     * Returns the buffered image of the Player.
     *
     * @return The buffered image of the Player.
     */
    public BufferedImage getSprite() {
        return mySprite;
    }


    /**
     * Returns the default buffered image of the Player.
     *
     * @return The default buffered image of the Player.
     */
    public BufferedImage getDefaultImage() {
        return defaultImage;
    }

}
