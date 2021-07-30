package model.room;

import model.TerrainGrid;

/**
 * Implements behavior common to all Rooms.
 *
 * @author Dustin Ray
 * @author Reuben Keller
 */
public abstract class AbstractRoom {

    /** Door object containing a given trivia question and link to another room. */
    private Door DoorA;
    /** Door object containing a given trivia question and link to another room. */
    private Door DoorB;
    /** Door object containing a given trivia question and link to another room. */
    private Door DoorC;
    /** Door object containing a given trivia question and link to another room. */
    private Door DoorD;
    /** Room object possibly linked to this room by a given door. */
    private Room RoomA;
    /** Room object possibly linked to this room by a given door. */
    private Room RoomB;
    /** Room object possibly linked to this room by a given door. */
    private Room RoomC;
    /** Room object possibly linked to this room by a given door. */
    private Room RoomD;
    /** The int ID for this room. */
    private final int myID;
    /** The terrain grid for this room. */
    private Terrain[][] myTerrain;


    /**
     * Constructs a Room with the given integer ID.
     * @param theID The integer ID of this Room.
     */
    public AbstractRoom(final int theID) {
        myID = theID;
        TerrainGrid tG = new TerrainGrid(16, 16, getFilePath());
        myTerrain = tG.getGrid();
    }


    public String getFilePath() {
        return "src/res/floor_maps/floor_map_" + myID +  "/floor_map_validfloor.csv";
    }


    /**
     * Gets the terrain grid for this room.
     * @return 2D character array representing terrain grid for this room.
     */
    public Terrain[][] getTerrain() {
        return myTerrain;
    }

    /**
     * Returns the integer ID of for this Room.
     * @return The integer ID for this Room.
     */
    public int getRoomID() {
        return myID;
    }


    /**
     * Sets the north Room and Door to the given Room and Door.
     * @param room The Room north of this Room.
     * @param door The Door separating this Room and the north Room.
     */
    public void setDoorA(final Room room, final Door door) {
        RoomA = room;
        DoorA = door;
    }

    /**
     * Sets the south Room and Door to the given Room and Door.
     * @param room The Room south of this Room.
     * @param door The Door separating this Room and the south Room.
     */
    public void setDoorB(final Room room, final Door door) {
        RoomB = room;
        DoorB = door;
    }

    /**
     * Sets the east Room and Door to the given Room and Door.
     * @param room The Room east of this Room.
     * @param door The Door separating this Room and the east Room.
     */
    public void setDoorC(final Room room, final Door door) {
        RoomC = room;
        DoorC = door;
    }

    /**
     * Sets the west Room and Door to the given Room and Door.
     * @param room The Room west of this Room.
     * @param door The Door separating this Room and the west Room.
     */
    public void setDoorD(final Room room, final Door door) {
        RoomD = room;
        DoorD = door;
    }

    /**
     * Returns the door between this Room and the north Room.
     * @return The Door between this Room and the north Room.
     * @throws NullPointerException if this Room does not have a north Room.
     */
    public Door getDoorA() {
        if (!hasRoomA()) {
            throw new NullPointerException("Room A is null (i.e., not connected)");
        }
        return DoorA;
    }

    /**
     * Returns the door between this Room and the south Room.
     * @return The Door between this Room and the south Room.
     * @throws NullPointerException if this Room does not have a south Room.
     */
    public Door getDoorB() {
        if (!hasRoomB()) {
            throw new NullPointerException("Room B is null (i.e., not connected)");
        }
        return DoorB;
    }


    /**
     * Returns the door between this Room and the east Room.
     * @return The Door between this Room and the east Room.
     * @throws NullPointerException if this Room does not have an east Room.
     */
    public Door getDoorC() {
        if (!hasRoomC()) {
            throw new NullPointerException("Room C is null (i.e., not connected)");
        }
        return DoorC;
    }

    /**
     * Returns the door between this Room and the west Room.
     * @return The Door between this Room and the west Room.
     * @throws NullPointerException if this Room does not have a west Room.
     */
    public Door getDoorD() {
        if (!hasRoomD()) {
            throw new NullPointerException("Room D is null (i.e., not connected)");
        }
        return DoorD;
    }

    /**
     * Returns the Room north of this Room.
     * @return The Room north of this Room.
     */
    public Room getRoomA() {
        return RoomA;
    }

    /**
     * Returns the Room south of this Room.
     * @return The Room south of this Room.
     */
    public Room getRoomB() {
        return RoomB;
    }

    /**
     * Returns the Room west of this Room.
     * @return The Room west of this Room.
     */
    public Room getRoomC() {
        return RoomC;
    }

    /**
     * Returns the Room east of this Room.
     * @return The Room east of this Room.
     */
    public Room getRoomD() {
        return RoomD;
    }

    /**
     * Returns true if this Room is connected to a north Room and false
     * otherwise.
     * @return true if this Room is connected to a north Room and false
     *     otherwise.
     */
    public boolean hasRoomA() {
        return RoomA != null;
    }

    /**
     * Returns true if this Room is connected to a south Room and false
     * otherwise.
     * @return true if this Room is connected to a south Room and false
     *     otherwise.
     */
    public boolean hasRoomB() {
        return RoomB != null;
    }

    /**
     * Returns true if this Room is connected to a east Room and false
     * otherwise.
     * @return true if this Room is connected to a east Room and false
     *     otherwise.
     */
    public boolean hasRoomC() {
        return RoomC != null;
    }

    /**
     * Returns true if this Room is connected to a west Room and false
     * otherwise.
     * @return true if this Room is connected to a west Room and false
     *     otherwise.
     */
    public boolean hasRoomD() {
        return RoomD != null;
    }

}
