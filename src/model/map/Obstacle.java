package model.map;

import java.io.Serial;

/**
 * Represents an Obstacle the Player can collide with.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class Obstacle extends MapEntity {

    @Serial
    private static final long serialVersionUID = 2781244165356964611L;

    /**
     * Constructs an Obstacle at the given pixel (x, y) location.
     *
     * @param theX The pixel x position.
     * @param theY The pixel y position.
     */
    public Obstacle(final int theX, final int theY) {
        super(theX, theY);
    }

}
