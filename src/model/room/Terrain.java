package model.room;

public enum Terrain {



    BOTTOM_WALL('B'),
    TOP_WALL('T'),
    TOP_LEFT_CORNER('('),
    TOP_RIGHT_CORNER(')'),
    BOTTOM_LEFT_CORNER('['),
    BOTTOM_RIGHT_CORNER(']'),

    /**Letters represent textures for walls and stuff */
    LEFT_WALL('L'),
    RIGHT_WALL('R'),
    FLOOR_1('F'),
    FLOOR_2('F'),
    DOOR_CLOSED('D'),
    DOOR_OPEN('O'),

    /**Symbols represent decorations and objects */
    WHITE_BOARD('!'),
    WARHOL('@'),
    VENDING_MACHINE('#'),
    DESK_1('1'),
    DESK_2('2'),
    DESK_3('3'),
    DEAD_CHAIR('$'),
    PLANT_CHAIR('%');




    /**
     * The character corresponding to a particular value of the enumeration.
     */
    private final char myLetter;


    /**
     * Constructs a new Terrain with the specified letter.
     *
     * @param theLetter The letter.
     */
    Terrain(final char theLetter) {
        myLetter = theLetter;
    }


    /**
     * Returns the Terrain represented by the given letter.
     *
     * @param theLetter The letter.
     * @return the Terrain represented by the given letter, or GRASS if no
     *         Terrain is represented by the given letter.
     */
    public static Terrain valueOf(final char theLetter) {
        Terrain result = FLOOR_2;

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
     *
     * @return a String representation of this Terrain.
     */
    @Override
    public String toString() {
        return super.toString() + " (" + myLetter + ")";
    }
}

