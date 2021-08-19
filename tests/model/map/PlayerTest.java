/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.map;

import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Implements unit tests for Player and AbstractMapEntity since Player extends
 * AbstractMapEntity.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
class PlayerTest {

    /** The path to the left chair image. */
    private static final String LEFT_PATH = Player.PATH + "chair_left.png";

    /** The path to the right chair image. */
    private static final String RIGHT_PATH = Player.PATH + "chair_right.png";

    /** The path to the up chair image. */
    private static final String UP_PATH = Player.PATH + "chair_up.png";

    /** The path to the down chair image. */
    private static final String DOWN_PATH = Player.PATH + "chair_down.png";

    /** The right chair image. */
    private BufferedImage myChairRight;

    /** The left chair image. */
    private BufferedImage myChairLeft;

    /** The up chair image. */
    private BufferedImage myChairUp;

    /** The down chair image. */
    private  BufferedImage myChairDown;

    /** The Player. */
    private final Player myPlayer;


    /**
     * Constructs a PlayerTest, reading Player image files and initializing
     * test fields.
     */
    public PlayerTest() {
        readFiles();
        myPlayer = new Player();
    }


    /**
     * Reads the Player image files from the defined path constants.
     */
    private void readFiles() {
        try {
            myChairLeft = ImageIO.read(new File(LEFT_PATH));
            myChairRight = ImageIO.read(new File(RIGHT_PATH));
            myChairUp = ImageIO.read(new File(UP_PATH));
            myChairDown = ImageIO.read(new File(DOWN_PATH));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Returns true if two buffered images are the same and false otherwise.
     *
     * @param imageA A buffered image.
     * @param imageB Another buffered image.
     * @return true if imageA and imageB are the same and false otherwise.
     */
    private boolean equalImages(final BufferedImage imageA,
                                final BufferedImage imageB) {
        int imageAWidth = imageA.getWidth();
        int imageAHeight = imageA.getHeight();
        int imageBWidth = imageB.getWidth();
        int imageBHeight = imageB.getHeight();

        if (imageAWidth != imageBWidth || imageAHeight != imageBHeight) {
            return false;
        }

        for (int x = 0; x < imageAWidth; x++) {
            for (int y = 0; y < imageAHeight; y++) {
                if (imageA.getRGB(x, y) != imageB.getRGB(x, y))
                    return false;
            }
        }

        return true;
    }


    /**
     * Checks that getX() after setX() returns the correct x.
     */
    @Test
    void getX_afterSetX_returnsCorrectX() {
        myPlayer.setX(1);
        assertEquals(1, myPlayer.getX(),
                "getX() after setX() does not return correct x");
    }


    /**
     * Checks that getY() after setY() returns the correct y.
     */
    @Test
    void getY_afterSetY_returnsCorrectY() {
        myPlayer.setY(1);
        assertEquals(1, myPlayer.getY(),
                "getY() after setY() does not return correct y");
    }


    /**
     * Checks that getSprite() after setSprite('R') returns the correct
     * buffered image.
     */
    @Test
    void getSprite_afterSetSpriteRight_returnsRightBufferedImage() {
        myPlayer.setSprite('R');
        assertTrue(equalImages(myChairRight, myPlayer.getSprite()),
                "getSprite('R') after setSprite('R') should return the chair" +
                        " right image");
    }


    /**
     * Checks that getSprite() after setSprite('L' returns the correct
     * buffered image.
     */
    @Test
    void getSprite_afterSetSpriteToLeft_returnsLeftBufferedImage() {
        myPlayer.setSprite('L');
        assertTrue(equalImages(myChairLeft, myPlayer.getSprite()),
                "getSprite('L') after setSprite('L') should return the chair" +
                        " left image");
    }


    /**
     * Checks that getSprite() after setSprite('U') returns the correct
     * buffered image.
     */
    @Test
    void getSprite_afterSetSpriteToUp_returnsUpBufferedImage() {
        myPlayer.setSprite('U');
        assertTrue(equalImages(myChairUp, myPlayer.getSprite()),
                "getSprite('U') after setSprite('U') should return the chair" +
                        " up image");
    }


    /**
     * Checks that getSprite() after setSprite('D') returns the correct
     * buffered image.
     */
    @Test
    void getSprite_afterSetSpriteToDown_returnsDownBufferedImage() {
        myPlayer.setSprite('D');
        assertTrue(equalImages(myChairDown, myPlayer.getSprite()),
                "getSprite() after setSprite('D') should return the chair" +
                        " down image");
    }


    /**
     * Checks that setSprite() throws IllegalArgumentException when given an
     * invalid character.
     */
    @Test
    void setSprite_givenInvalidChar_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> myPlayer.setSprite('a'),
                "setSprite() should throw exception given invalid char");
    }


    /**
     * Checks that reset() after setX() resets x to Player.DEFAULT_X
     */
    @Test
    void reset_afterSetX_resetsXToDefaultX() {
        myPlayer.setX(576);
        myPlayer.reset();
        assertEquals(myPlayer.getX(), Player.DEFAULT_X,
                "reset() after setX() should reset x to Player.DEFAULT_X");
    }


    /**
     * Checks that reset() after setX() resets player sprite to the default
     * Player image.
     */
    @Test
    void reset_afterSetX_resetsSpriteToDefaultImage() {
        myPlayer.setX(698);
        myPlayer.reset();
        assertTrue(equalImages(myChairUp, myPlayer.getDefaultImage()),
                "reset() after setX() should reset Player image" +
                        " to default image");
    }


    /**
     * Checks that reset() after setY() resets y to Player.DEFAULT_Y
     */
    @Test
    void reset_afterSetY_resetsYToDefaultY() {
        myPlayer.setY(471);
        myPlayer.reset();
        assertEquals(myPlayer.getY(), Player.DEFAULT_Y,
                "reset() after setY() should reset y to Player.DEFAULT_Y");
    }


    /**
     * Checks that reset() after setY() resets the sprite to the default
     * Player image.
     */
    @Test
    void reset_afterSetY_resetsSpriteToDefaultImage() {
        myPlayer.setX(492);
        assertTrue(equalImages(myChairUp, myPlayer.getDefaultImage()),
                "reset() after setY() should reset Player image" +
                        " to default image");
    }


    /**
     * Checks that update() after setVelX() correctly updates x.
     */
    @Test
    void update_afterSetVelX_correctlyUpdatesX() {
        int x = myPlayer.getX();
        int xVel = 5;
        myPlayer.setVelX(xVel);
        myPlayer.update();
        assertEquals(x + xVel, myPlayer.getX(),
                "update() after setVelX() does not correctly update x");
    }


    /**
     * Checks that update() after setVelY() correctly updates y.
     */
    @Test
    void update_afterSetVelY_correctlyUpdatesY() {
        int y = myPlayer.getY();
        int yVel = 8;
        myPlayer.setVelY(yVel);
        myPlayer.update();
        assertEquals(y + yVel, myPlayer.getY(),
                "update() after setVelY() does not correctly update y");
    }


    /**
     * Checks that outOfBounds() returns true when Player x exceeds
     * GameMap.PIXEL_WIDTH.
     */
    @Test
    void outOfBounds_afterSetXAboveUpperXBound_returnsTrue() {
        myPlayer.setX(GameMap.PIXEL_WIDTH + 1);
        assertTrue(myPlayer.outOfBounds(),
                "outOfBounds() should return true when Player x " +
                        "exceeds GameMap.PIXEL_WIDTH");
    }


    /**
     * Checks that outOfBounds() returns true when Player x is less than 0.
     */
    @Test
    void outOfBounds_afterSetXBelowLowerXBound_returnsTrue() {
        myPlayer.setX(-1);
        assertTrue(myPlayer.outOfBounds(),
                "outOfBounds() should return true when Player x" +
                        " is less than 0");
    }


    /**
     * Checks that outOfBounds() returns true when Player y exceeds
     * GameMap.PIXEL_HEIGHT
     */
    @Test
    void outOfBounds_afterSetYAboveUpperYBound_returnsTrue() {
        myPlayer.setY(GameMap.PIXEL_HEIGHT + 1);
        assertTrue(myPlayer.outOfBounds(),
                "outOfBounds() should return true when Player y " +
                        "exceeds GameMap.PIXEL_HEIGHT");
    }


    /**
     * Checks that outOfBounds() returns true when Player y is less than 0.
     */
    @Test
    void outOfBounds_afterSetYBelowLowerYBound_returnsTrue() {
        myPlayer.setY(-1);
        assertTrue(myPlayer.outOfBounds(),
                "outOfBounds() should return true when Player y" +
                        " is less than 0");
    }


    /**
     * Checks that outOfBounds() returns false when Player x and y are valid.
     */
    @Test
    void outOfBounds_whenPlayerPositionIsValid_returnsFalse() {
        assertFalse(myPlayer.outOfBounds(),
                "outOfBounds() should return false when Player x" +
                        " and y are valid");
    }


    /**
     * Checks that this.collidesWith(other) returns true when this x overlaps
     * other x from the left.
     */
    @Test
    void collidesWith_whenXOverlapsOtherXFromLeft_returnsTrue() {
        Player other = new Player(50, 50);
        myPlayer.setX(51);
        myPlayer.setY(50);
        assertTrue(myPlayer.collidesWith(other),
                "this.collidesWith(other) returns true when this x overlaps " +
                        "other x from the left");
    }


    /**
     * Checks that this.collidesWith(other) returns true when this x overlaps
     * other x from the right.
     */
    @Test
    void collidesWith_whenXOverLapsOtherXFromRight_returnsTrue() {
        Player other =  new Player(71, 75);
        myPlayer.setX(70);
        myPlayer.setY(75);
        assertTrue(myPlayer.collidesWith(other),
                "this.collidesWith(other) returns true when this x overlaps " +
                        "other x from the right");
    }


    /**
     * Checks that this.collidesWith(other) returns true when this y overlaps
     * other y from above.
     */
    @Test
    void collidesWith_whenYOverLapsOtherYFromAbove_returnsTrue() {
        Player other = new Player(42, 43);
        myPlayer.setX(42);
        myPlayer.setY(43);
        assertTrue(myPlayer.collidesWith(other),
                "this.collidesWith(other) returns true when this y overlaps " +
                        "other y from above");
    }


    /**
     * Checks that this.collidesWith(other) returns true when this y overlaps
     * other y from below.
     */
    @Test
    void collidesWith_whenOverLapsOtherYFromBelow_returnsTrue() {
        Player other = new Player(51, 52);
        myPlayer.setX(52);
        myPlayer.setY(52);
        assertTrue(myPlayer.collidesWith(other),
                "this.collidesWith(other) returns true when this y overlaps " +
                        "other y from below");
    }


    /**
     * Checks that this.collidesWith(other) returns false for no x overlap.
     */
    @Test
    void collidesWith_whenNoXOverlaps_returnsFalse() {
        Player other = new Player(50, 50);
        myPlayer.setX(2);
        myPlayer.setY(50);
        assertFalse(myPlayer.collidesWith(other),
                "this.collidesWith(other) should return false for " +
                        "no x overlap");
    }


    /**
     * Checks that this.collidesWith(other) returns false for no y overlap.
     */
    @Test
    void collidesWith_whenNoYOverlaps_returnsFalse() {
        Player other = new Player(61, 60);
        myPlayer.setX(61);
        myPlayer.setY(12);
        assertFalse(myPlayer.collidesWith(other),
                "this.collidesWith(other) should return false for" +
                        " no y overlap");
    }


    /**
     * Checks that this.collidesWith(this) returns true.
     */
    @Test
    void collidesWith_isReflexive() {
        assertTrue(myPlayer.collidesWith(myPlayer),
                "collidesWith() is not reflexive");
    }


    /**
     * Checks that if this.collidesWith(other) is true, then
     * other.collidesWith(this) true.
     */
    @Test
    void collidesWith_isSymmetric() {
        Player other = new Player();
        String msg = "collidesWith() is not symmetric";
        assertTrue(myPlayer.collidesWith(other), msg);
        assertTrue(other.collidesWith(myPlayer), msg);
    }


    /**
     * Checks that this.equals(other) returns true if this and other are equal.
     */
    @Test
    void equals_withEqualOther_returnsTrue() {
        Player other = new Player();
        other.setX(30);
        other.setY(30);
        myPlayer.setX(30);
        myPlayer.setY(30);
        assertEquals(myPlayer, other, "equals() should return true for equal" +
                " Players");
    }


    /**
     * Checks that this.equals(this) is true.
     */
    @Test
    void equals_isReflexive() {
        assertEquals(myPlayer, myPlayer, "equals() is not reflexive");
    }


    /**
     * Checks that if this.equals(other) then other.equals(this).
     */
    @Test
    void equals_isSymmetric() {
        Player other = new Player();
        other.setVelY(10);
        myPlayer.setVelY(10);
        String msg = "equals() is not symmetric";
        assertEquals(myPlayer, other, msg);
        assertEquals(other, myPlayer, msg);
    }


    /**
     * Checks that hashCode() returns the same value for equal Players.
     */
    @Test
    void hashCode_forEqualMapEntities_isSame() {
        myPlayer.setX(50);
        myPlayer.setY(50);
        Player other = new Player(50, 50);
        assertEquals(myPlayer.hashCode(), other.hashCode(),
                "hashCode() should return same value for equal Players");
    }


    /**
     * Checks that toString() returns the correct String.
     */
    @Test
    void toString_returnsCorrectString() {
        String exp = "(x=" + myPlayer.getX() + ", y=" + myPlayer.getY() + ")";
        assertEquals(exp, myPlayer.toString(),
                "toString() does not return correct String");
    }

}