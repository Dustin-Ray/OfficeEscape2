/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.map;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**
 * Implements tests for GameMap.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
class GameMapTest {

    /** The ID to pass the ID GameMap ID constructor. */
    private static final int GM_ID = 1;

    /** The path to a known csv file in tests. */
    private static final String TEST_PATH = "tests/model/map/map.csv";

    /** The GameMap constructed using the csv file from TEST_PATH. */
    private final GameMap myGM;

    /** The door A map entities. */
    private final List<AbstractMapEntity> myDoorAEntities;

    /** The door B map entities. */
    private final List<AbstractMapEntity> myDoorBEntities;

    /** The door C map entities. */
    private final List<AbstractMapEntity> myDoorCEntities;

    /** The door D map entities.*/
    private final List<AbstractMapEntity> myDoorDEntities;

    /** The obstacle map entities. */
    private final List<AbstractMapEntity> myObstacleEntities;


    /**
     * Constructs a GameMapTest, initializing test fields.
     */
    public GameMapTest() {
        myGM = new GameMap(TEST_PATH);
        myDoorAEntities = new ArrayList<>();
        myDoorBEntities = new ArrayList<>();
        myDoorCEntities = new ArrayList<>();
        myDoorDEntities = new ArrayList<>();
        myObstacleEntities = new ArrayList<>();
        parse(TEST_PATH);
    }


    /**
     * Parses the csv files and extracts the correct door and obstacle entities.
     *
     * @param path The path to the csv file.
     */
    private void parse(final String path) {
        try {
            Scanner sc = new Scanner(new File(path));
            for (int i = 0; i < GameMap.TILE_ROWS; i++) {
                String[] line = sc.nextLine().split(",");
                for (int j = 0; j < GameMap.TILE_COLS; j++) {
                    AbstractMapEntity obs = new MapEntity(
                            j * GameMap.TILE_WIDTH,
                            i * GameMap.TILE_WIDTH
                    );
                    switch (line[j]) {
                        case GameMap.DOOR_A -> myDoorAEntities.add(obs);
                        case GameMap.DOOR_B -> myDoorBEntities.add(obs);
                        case GameMap.DOOR_C -> myDoorCEntities.add(obs);
                        case GameMap.DOOR_D -> myDoorDEntities.add(obs);
                        case GameMap.OBSTACLE -> myObstacleEntities.add(obs);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Checks that a GameMap constructed using the integer ID constructor
     * returns the correct door A entities.
     */
    @Test
    void doorAEntities_forRoomIDConstructorWithDefaultPath_returnsCorrect() {
        GameMap idGM = new GameMap(GM_ID);
        myDoorAEntities.clear();
        parse(GameMap.PATH_PREFIX + GM_ID + GameMap.PATH_SUFFIX);
        assertEquals(myDoorAEntities, idGM.doorAEntities(),
                "GameMap constructed using integer ID does not compute" +
                        " correct map entity positions");
    }


    /**
     * Checks that obstacleEntities() returns the correct obstacle entities.
     */
    @Test
    void obstacleEntities_returnsCorrectObstacleEntities() {
        assertEquals(myObstacleEntities, myGM.obstacleEntities(),
                "obstacleEntities() does not return correct" +
                        " obstacle entities");
    }


    /**
     * Checks that doorAEntities() returns the correct door A entities.
     */
    @Test
    void doorAPositions() {
        assertEquals(myDoorAEntities, myGM.doorAEntities(),
                "doorAEntities() does not return correct door A entities");
    }


    /**
     * Checks that doorBEntities() returns the correct door B entities.
     */
    @Test
    void doorBPositions() {
        assertEquals(myDoorBEntities, myGM.doorBEntities(),
                "doorBEntities() does not return correct door B entities");
    }


    /**
     * Checks that doorCEntities() returns the correct door C entities.
     */
    @Test
    void doorCPositions() {
        assertEquals(myDoorCEntities, myGM.doorCEntities(),
                "doorCEntities() does not return correct door C entities");
    }


    /**
     * Checks that doorDEntities() returns the correct door D entities.
     */
    @Test
    void doorDPositions() {
        assertEquals(myDoorDEntities, myGM.doorDEntities(),
                "doorDEntities() does not return correct entities");
    }


    /**
     * Checks that equals() is reflexive.
     */
    @Test
    void equals_isReflexive() {
        assertEquals(myGM, myGM, "equals() is not reflexive");
    }


    /**
     * Checks that equals() is symmetric.
     */
    @Test
    void equals_isSymmetric() {
        GameMap other = new GameMap(TEST_PATH);
        String msg = "equals() is not symmetric";
        assertEquals(myGM, other, msg);
        assertEquals(other, myGM, msg);
    }

    /**
     * Checks that equals() returns false for different GameMaps.
     */
    @Test
    void equals_withDifferent_returnsFalse() {
        String expPath = GameMap.PATH_PREFIX + 2 + GameMap.PATH_SUFFIX;
        String otherPath = GameMap.PATH_PREFIX + GM_ID + GameMap.PATH_SUFFIX;
        GameMap exp = new GameMap(expPath);
        GameMap other = new GameMap(otherPath);
        assertNotEquals(exp, other,
                "equals() should return false for different GameMaps");
    }


    /**
     * Checks that hashCode()
     */
    @Test
    void hashCode_onEqualGameMaps_isSame() {
        GameMap other = new GameMap(TEST_PATH);
        assertEquals(myGM.hashCode(), other.hashCode());
    }

}