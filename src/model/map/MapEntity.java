/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.map;

import java.io.Serial;

/**
 * MapEntity is a map object with x and y positions. Can represent door tiles,
 * obstacle tiles, etc. It's distinct from a Player that moves through a map.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class MapEntity extends AbstractMapEntity {

    @Serial
    private static final long serialVersionUID = 2781244165356964611L;

    /**
     * Constructs an MapEntity at the given pixel (x, y) location.
     *
     * @param theX The pixel x position.
     * @param theY The pixel y position.
     */
    public MapEntity(final int theX, final int theY) {
        super(theX, theY);
    }

}
