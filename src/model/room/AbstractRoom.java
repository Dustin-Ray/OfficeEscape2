package model.room;

import view.RoomPanel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Implements behavior common to all Rooms.
 *
 * @author Dustin Ray
 * @author Reuben Keller
 */
public abstract class AbstractRoom {

    private Door northDoor;

    private Door southDoor;

    private Door eastDoor;

    private Door westDoor;

    private Room northRoom;

    private Room southRoom;

    private Room eastRoom;

    private Room westRoom;

    private final int myID;

    private final RoomPanel myRoomPanel;


    /**
     * Constructs a Room with the given integer ID.
     *
     * @param theID The integer ID of this Room.
     */
    public AbstractRoom(final int theID) {
        myID = theID;
        myRoomPanel = readMapFile();
    }


    /**
     * Reads the map text file corresponding to this Rooms ID and builds this
     * Room's RoomPanel.
     *
     * @return The RoomPanel for this Room.
     */
    private RoomPanel readMapFile() {
        File file = new File("src/res/floor_maps/floor_map_" + myID + ".txt");
        RoomPanel result = null;

        try (Scanner input = new Scanner(new BufferedReader(new FileReader(file)))) {
            // First, we read the map description
            result = new RoomPanel(readGrid(input));
        } catch (final IOException ioe) {
            System.out.println("Error loading resource, check all externally loaded file paths. ");
        }
        return result;
    }


    /**
     * Reads the grid portion of the map file.
     *
     * @param theInput The input scanner.
     * @return the map of the terrains.
     */
    private Terrain[][] readGrid(final Scanner theInput) {
        final int numRows = theInput.nextInt();
        final int numColumns = theInput.nextInt();
        theInput.nextLine();
        final Terrain[][] grid = new Terrain[numRows][numColumns];
        for (int row = 0; row < numRows; row++) {
            final String line = theInput.nextLine();
            for (int column = 0; column < numColumns; column++) {
                grid[row][column] = Terrain.valueOf(line.charAt(column));
            }
        }
        return grid;
    }


    /**
     * Returns the RoomPanel for this Room.
     *
     * @return The RoomPanel for this Room.
     */
    public RoomPanel getRoomPanel() {
            return myRoomPanel;
    }


    /**
     * Returns the integer ID of for this Room.
     *
     * @return The integer ID for this Room.
     */
    public int getRoomID() {
        return myID;
    }


    /**
     * Sets the north Room and Door to the given Room and Door.
     *
     * @param room The Room north of this Room.
     * @param door The Door separating this Room and the north Room.
     */
    public void setNorth(final Room room, final Door door) {
        northRoom = room;
        northDoor = door;
    }


    /**
     * Sets the south Room and Door to the given Room and Door.
     *
     * @param room The Room south of this Room.
     * @param door The Door separating this Room and the south Room.
     */
    public void setSouth(final Room room, final Door door) {
        southRoom = room;
        southDoor = door;
    }


    /**
     * Sets the west Room and Door to the given Room and Door.
     *
     * @param room The Room west of this Room.
     * @param door The Door separating this Room and the west Room.
     */
    public void setWest(final Room room, final Door door) {
        westRoom = room;
        westDoor = door;
    }


    /**
     * Sets the east Room and Door to the given Room and Door.
     *
     * @param room The Room east of this Room.
     * @param door The Door separating this Room and the east Room.
     */
    public void setEast(final Room room, final Door door) {
        eastRoom = room;
        eastDoor = door;
    }


    /**
     * Returns the door between this Room and the north Room.
     *
     * @return The Door between this Room and the north Room.
     * @throws NullPointerException if this Room does not have a north Room.
     */
    public Door getNorthDoor() {
        if (!hasNorth()) {
            throw new NullPointerException("north Room is null (i.e., not connected)");
        }
        return northDoor;
    }


    /**
     * Returns the door between this Room and the south Room.
     *
     * @return The Door between this Room and the south Room.
     * @throws NullPointerException if this Room does not have a south Room.
     */
    public Door getSouthDoor() {
        if (!hasSouth()) {
            throw new NullPointerException("south Room is null (i.e., not connected)");
        }
        return southDoor;
    }


    /**
     * Returns the door between this Room and the east Room.
     *
     * @return The Door between this Room and the east Room.
     * @throws NullPointerException if this Room does not have an east Room.
     */
    public Door getEastDoor() {
        if (!hasEast()) {
            throw new NullPointerException("east Room is null (i.e., not connected)");
        }
        return eastDoor;
    }


    /**
     * Returns the door between this Room and the west Room.
     *
     * @return The Door between this Room and the west Room.
     * @throws NullPointerException if this Room does not have a west Room.
     */
    public Door getWestDoor() {
        if (!hasWest()) {
            throw new NullPointerException("west Room is null (i.e., not connected)");
        }
        return westDoor;
    }


    /**
     * Returns the Room north of this Room.
     *
     * @return The Room north of this Room.
     */
    public Room getNorthRoom() {
        return northRoom;
    }


    /**
     * Returns the Room south of this Room.
     *
     * @return The Room south of this Room.
     */
    public Room getSouthRoom() {
        return southRoom;
    }


    /**
     * Returns the Room west of this Room.
     *
     * @return The Room west of this Room.
     */
    public Room getWestRoom() {
        return westRoom;
    }


    /**
     * Returns the Room east of this Room.
     *
     * @return The Room east of this Room.
     */
    public Room getEastRoom() {
        return eastRoom;
    }


    /**
     * Returns true if this Room is connected to a north Room and false
     * otherwise.
     *
     * @return true if this Room is connected to a north Room and false
     *     otherwise.
     */
    public boolean hasNorth() {
        return northRoom != null;
    }


    /**
     * Returns true if this Room is connected to a south Room and false
     * otherwise.
     *
     * @return true if this Room is connected to a south Room and false
     *     otherwise.
     */
    public boolean hasSouth() {
        return southRoom != null;
    }


    /**
     * Returns true if this Room is connected to a west Room and false
     * otherwise.
     *
     * @return true if this Room is connected to a west Room and false
     *     otherwise.
     */
    public boolean hasWest() {
        return westRoom != null;
    }


    /**
     * Returns true if this Room is connected to a east Room and false
     * otherwise.
     *
     * @return true if this Room is connected to a east Room and false
     *     otherwise.
     */
    public boolean hasEast() {
        return eastRoom != null;
    }

}
