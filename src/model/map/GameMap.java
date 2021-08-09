package model.map;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.map.Terrain.*;

public class GameMap {

    public static final int TILE_WIDTH = 48;

    public static final int TILE_HEIGHT = 48;

    public static final int TILE_ROWS = 16;

    public static final int TILE_COLS = 16;

    public static final int PIXEL_WIDTH = TILE_WIDTH * TILE_COLS;

    public static final int PIXEL_HEIGHT = TILE_HEIGHT * TILE_COLS;

    public static final String PATH = "src/res/maps/map_";

    private static final String ROOM_A = "0";

    private static final String ROOM_B = "1";

    private static final String ROOM_C = "2";

    private static final String ROOM_D = "3";

    private static final String RED_ZONE = "-1";

    private final Terrain[][] grid;

    private final List<MapEntity> obstacles;

    private final List<MapEntity> doorAPositions;

    private final List<MapEntity> doorBPositions;

    private final List<MapEntity> doorCPositions;

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
     * array, filling doorAPositions, doorBPositions, doorCPositions, and
     * doorDPositions with the pixel x and y locations.
     *
     * @param path The path of this Map's csv file.
     */
    private void translateFile(String path) {
        try {
            Scanner sc = new Scanner(new File(path));
            for (int row = 0; row < TILE_ROWS; row++) {
                String[] line = sc.nextLine().split(",");
                for (int col = 0; col < TILE_COLS; col++) {
                    String curr = line[col];
                    Terrain terrain = FLOOR;
                    if (curr.equals(ROOM_A)) {
                        terrain = DOOR_CLOSED_A;
                        doorAPositions.add(new Obstacle(col * TILE_WIDTH, row * TILE_HEIGHT));
                    } else if (curr.equals(ROOM_B)) {
                        terrain = DOOR_CLOSED_B;
                        doorBPositions.add(new Obstacle(col * TILE_WIDTH, row * TILE_HEIGHT));
                    } else if (curr.equals(ROOM_C)) {
                        terrain = DOOR_CLOSED_C;
                        doorCPositions.add(new Obstacle(col * TILE_WIDTH, row * TILE_HEIGHT));
                    } else if (curr.equals(ROOM_D)) {
                        terrain = DOOR_CLOSED_D;
                        doorDPositions.add(new Obstacle(col * TILE_WIDTH, row * TILE_HEIGHT));
                    } else if (curr.equals(RED_ZONE)) {
                        terrain = OBSTACLE;
                        obstacles.add(new Obstacle(col * TILE_WIDTH, row * TILE_HEIGHT));
                    }
                    grid[row][col] = terrain;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
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


    public List<MapEntity> doorAPositions() {
        return doorAPositions;
    }

    public List<MapEntity> doorBPositions() {
        return doorBPositions;
    }

    public List<MapEntity> doorCPositions() {
        return doorCPositions;
    }


    public List<MapEntity> doorDPositions() {
        return doorDPositions;
    }

}
