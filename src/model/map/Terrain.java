package model.map;

/**
 * An enumeration object used to define the terrain grid.
 * @author Dustin Ray
 * @version Summer 2021
 */
public enum Terrain {

    /**Letters represent textures for walls and stuff */

    FLOOR_1('F'),
    RED_ZONE('R'),
    DOOR_CLOSED_A('A'),
    DOOR_CLOSED_B('B'),
    DOOR_CLOSED_C('C'),
    DOOR_CLOSED_D('D'),
    DOOR_OPEN('O');



    /** The character corresponding to a particular value of the enumeration. */
    private final char myLetter;


    /**
     * Constructs a new Terrain with the specified letter.
     * @param theLetter The letter.
     */
    Terrain(final char theLetter) {
        myLetter = theLetter;
    }


    /**
     * Returns the Terrain represented by the given letter.
     * @param theLetter The letter.
     * @return the Terrain represented by the given letter, or GRASS if no
     *         Terrain is represented by the given letter.
     */
    public static Terrain valueOf(final char theLetter) {
        Terrain result = FLOOR_1;
        for (final Terrain terrain : Terrain.values()) {
            if (terrain.myLetter == theLetter) {
                result = terrain;
                break;
            }
        }
        return result;
    }

    /**
     * Returns a String representation of this Terrain, such as "WALL (X)".
     * @return a String representation of this Terrain.
     */
    @Override
    public String toString() {
        return super.toString() + " (" + myLetter + ")";
    }
}

