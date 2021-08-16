/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Dustin Ray, Raz Consta, Reuben Keller
 */

package model.map;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.map.Terrain.*;

/**
 * Represents the GameMap that the Player moves within.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class GameMap implements Serializable {

    /** The pixel width of each tile. */
    public static final int TILE_WIDTH = 48;

    /** The pixel height of each tile. */
    public static final int TILE_HEIGHT = 48;

    /** The number of rows with tiles. */
    public static final int TILE_ROWS = 16;

    /** The number of columns with tiles. */
    public static final int TILE_COLS = 16;

    /** The pixel width of the map. */
    public static final int PIXEL_WIDTH = TILE_WIDTH * TILE_COLS;

    /** The pixel height of the map. */
    public static final int PIXEL_HEIGHT = TILE_HEIGHT * TILE_COLS;

    /** The file path containing the csv floor data. */
    public static final String PATH = "src/res/maps/map_";

    /** The String representing door A in the csv file. */
    private static final String DOOR_A = "0";

    /** The String representing door B in the csv file. */
    private static final String DOOR_B = "1";

    /** The String representing door C in the csv file. */
    private static final String DOOR_C = "2";

    /** The String representing door D in the csv file. */
    private static final String DOOR_D = "3";

    /** The String representing an obstacle in the csv file. */
    private static final String OBSTACLE = "-1";

    /** A 2D array containing Terrain values for each of the tiles. */
    private final Terrain[][] grid;

    /** A list of the pixel obstacle positions in the map. */
    private final List<MapEntity> obstacles;

    /** A list of the pixel door A positions in the map. */
    private final List<MapEntity> doorAPositions;

    /** A list of the pixel door B positions in the map. */
    private final List<MapEntity> doorBPositions;

    /** A list of the pixel door C positions in the map. */
    private final List<MapEntity> doorCPositions;

    /** A list of the pixel door D positions in the map. */
    private final List<MapEntity> doorDPositions;


    /**
     * Constructs a GameMap corresponding to the given room ID.
     *
     * @param roomID The integer ID of the room for this GameMap.
     */
    public GameMap(final int roomID) {
        grid = new Terrain[TILE_ROWS][TILE_COLS];
        obstacles = new ArrayList<>();
        doorAPositions = new ArrayList<>();
        doorBPositions = new ArrayList<>();
        doorCPositions = new ArrayList<>();
        doorDPositions = new ArrayList<>();
        translateFile(PATH + roomID + "/map_" + roomID + ".csv");
    }


    /**
     * Translates the csv file indicating valid positions to a 2D Terrain
     * array, filling doorAPositions, doorBPositions, doorCPositions,
     * doorDPositions, and obstacles with the pixel x and y locations of
     * door A, door B, door C, doorD, and obstacles, respectively.
     *
     * @param path The path of this GameMap's csv file.
     */
    private void translateFile(final String path) {
        try {
            Scanner sc = new Scanner(new File(path));
            for (int row = 0; row < TILE_ROWS; row++) {
                String[] line = sc.nextLine().split(",");
                for (int col = 0; col < TILE_COLS; col++) {
                    setGameMapPositions(line[col], row, col);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Sets the value of grid and adds pixel positions to the door and obstacle
     * lists based on the given String containing tile information, and the
     * corresponding row and column in grid.
     *
     * @param tileString The String representing a tile.
     * @param row The row of tileString in grid.
     * @param col The column of tileString in grid.
     */
    private void setGameMapPositions(final String tileString, final int row,
                                     final int col) {
        Terrain terrain = FLOOR;
        switch (tileString) {
            case DOOR_A -> {
                terrain = DOOR_CLOSED_A;
                doorAPositions.add(new Obstacle(col * TILE_WIDTH,
                        row * TILE_HEIGHT));
            }
            case DOOR_B -> {
                terrain = DOOR_CLOSED_B;
                doorBPositions.add(new Obstacle(col * TILE_WIDTH,
                        row * TILE_HEIGHT));
            }
            case DOOR_C -> {
                terrain = DOOR_CLOSED_C;
                doorCPositions.add(new Obstacle(col * TILE_WIDTH,
                        row * TILE_HEIGHT));
            }
            case DOOR_D -> {
                terrain = DOOR_CLOSED_D;
                doorDPositions.add(new Obstacle(col * TILE_WIDTH,
                        row * TILE_HEIGHT));
            }
            case OBSTACLE -> {
                terrain = Terrain.OBSTACLE;
                obstacles.add(new Obstacle(col * TILE_WIDTH,
                        row * TILE_HEIGHT));
            }
        }
        grid[row][col] = terrain;
    }


    /**
     * Returns a List of Obstacles in this GameMap.
     *
     * @return A List of Obstacles in this GameMap.
     */
    public List<MapEntity> getObstacles() {
        return obstacles;
    }


    /**
     * Returns a 2D Terrain array representation of this GameMap.
     *
     * @return A 2D Terrain array of this GameMap.
     */
    public Terrain[][] getTerrainGrid() {
        return grid;
    }


    /**
     * Returns a list of the pixel positions of door A.
     *
     * @return A list of the pixel positions of door A.
     */
    public List<MapEntity> doorAPositions() {
        return doorAPositions;
    }


    /**
     * Returns a list of the pixel positions of door B.
     *
     * @return A list of the pixel positions of door B.
     */
    public List<MapEntity> doorBPositions() {
        return doorBPositions;
    }


    /**
     * Returns a list of the pixel positions of door C.
     *
     * @return A list of the pixel positions of door C.
     */
    public List<MapEntity> doorCPositions() {
        return doorCPositions;
    }


    /**
     * Returns a list of the pixel positions of door D.
     *
     * @return A list of the pixel positions of door D.
     */
    public List<MapEntity> doorDPositions() {
        return doorDPositions;
    }

}
