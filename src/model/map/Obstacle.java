package model.map;

/**
 * Represents an Obstacle the Player can collide with.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class Obstacle extends MapEntity {

    /**
     * Constructs an Obstacle at the given pixel (x, y) location.
     * @param theX The pixel x position.
     * @param theY The pixel y position.
     */
    public Obstacle(final int theX, final int theY) {
        super(theX, theY);
    }

}
