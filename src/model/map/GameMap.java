/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.map;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents the GameMap the Player moves within. Reads in a csv file with
 * data indicating where the Player can go and provides methods to get door
 * and obstacle map entities.
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

    /** The prefix of the path to the csv floor data. */
    public static final String PATH_PREFIX = "src/res/maps/map_";

    /** The suffix of the path to the csv floor data. */
    public static final String PATH_SUFFIX = "/map.csv";

    /** The String representing door A in the csv file. */
    public static final String DOOR_A = "0";

    /** The String representing door B in the csv file. */
    public static final String DOOR_B = "1";

    /** The String representing door C in the csv file. */
    public static final String DOOR_C = "2";

    /** The String representing door D in the csv file. */
    public static final String DOOR_D = "3";

    /** The String representing an obstacle in the csv file. */
    public static final String OBSTACLE = "-1";

    @Serial
    private static final long serialVersionUID = 3840283924276090102L;

    /** The obstacle map entities in this GameMap. */
    private final List<AbstractMapEntity> myObstacleEntities;

    /** The door A map entities in this GameMap. */
    private final List<AbstractMapEntity> myDoorAEntities;

    /** The door B map entities in this GameMap. */
    private final List<AbstractMapEntity> myDoorBEntities;

    /** The door C map entities in this GameMap. */
    private final List<AbstractMapEntity> myDoorCEntities;

    /** The door D map entities in this GameMap. */
    private final List<AbstractMapEntity> myDoorDEntities;


    /**
     * Constructs a GameMap by reading a csv file corresponding to the given
     * room ID.
     *
     * @param roomID The integer ID of the room for this GameMap.
     */
    public GameMap(final int roomID) {
        this(PATH_PREFIX + roomID + PATH_SUFFIX);
    }


    /**
     * Constructs a GameMap by reading from the given file path.
     *
     * @param thePath The path to this GameMap's csv file containing floor
     *     information.
     */
    public GameMap(final String thePath) {
        myObstacleEntities = new ArrayList<>();
        myDoorAEntities = new ArrayList<>();
        myDoorBEntities = new ArrayList<>();
        myDoorCEntities = new ArrayList<>();
        myDoorDEntities = new ArrayList<>();
        translateFile(thePath);
    }


    /**
     * Translates the csv file containing floor information to myDoorAEntities,
     * myDoorBEntities, myDoorCEntities, myDoorDEntities, and
     * myObstacleEntities.
     *
     * @param path The path of this GameMap's csv file.
     */
    private void translateFile(final String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine();
            for (int i = 0; line != null; i++) {
                String[] cols = line.split(",");
                for (int j = 0; j < TILE_COLS; j++) {
                    AbstractMapEntity entity = new MapEntity(j * TILE_WIDTH,
                            i * TILE_HEIGHT);
                    switch (cols[j]) {
                        case DOOR_A -> myDoorAEntities.add(entity);
                        case DOOR_B -> myDoorBEntities.add(entity);
                        case DOOR_C -> myDoorCEntities.add(entity);
                        case DOOR_D -> myDoorDEntities.add(entity);
                        case OBSTACLE -> myObstacleEntities.add(entity);
                    }
                }
                line = reader.readLine();
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
    public List<AbstractMapEntity> obstacleEntities() {
        return myObstacleEntities;
    }


    /**
     * Returns a List of door A map entities.
     *
     * @return A List of door A map entities.
     */
    public List<AbstractMapEntity> doorAEntities() {
        return myDoorAEntities;
    }


    /**
     * Returns a List of door B map entities.
     *
     * @return A List of door B map entities.
     */
    public List<AbstractMapEntity> doorBEntities() {
        return myDoorBEntities;
    }


    /**
     * Returns a List of door C map entities.
     *
     * @return A List of door C map entities.
     */
    public List<AbstractMapEntity> doorCEntities() {
        return myDoorCEntities;
    }


    /**
     * Returns a List of door D map entities.
     *
     * @return A List of door D map entities.
     */
    public List<AbstractMapEntity> doorDEntities() {
        return myDoorDEntities;
    }


    @Override
    public boolean equals(final Object other) {
        boolean result = false;
        if ((other != null) && (other.getClass().equals(this.getClass()))) {
            GameMap o = (GameMap) other;
            result = (this.myDoorAEntities.equals(o.myDoorAEntities)
                    && this.myDoorBEntities.equals(o.myDoorBEntities)
                    && this.myDoorCEntities.equals(o.myDoorCEntities)
                    && this.myDoorDEntities.equals(o.myDoorDEntities)
                    && this.myObstacleEntities.equals(o.myObstacleEntities));
        }
        return result;
    }


    @Override
    public int hashCode() {
        return Objects.hash(myObstacleEntities, myDoorAEntities, myDoorBEntities,
                myDoorCEntities, myDoorDEntities);
    }

}
