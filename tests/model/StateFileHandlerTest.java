/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model;

import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Implements tests for StateFileHandler.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class StateFileHandlerTest {

    /** The path to save a test file at. (Used for testing save().) */
    private static final String TEST_SAVE_PATH = "tests/model/test_save_file";

    /** The path to load a test file from. (Used for testing load().) */
    private static final String TEST_LOAD_PATH = "tests/model/test_load_path";

    /** A serializable object to use in testing. */
    private final List<Double> myList;


    /**
     * Constructs a StateFileHandlerTest, initializing a serializable object
     * for test.
     */
    public StateFileHandlerTest() {
        myList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            myList.add(Math.random());
        }
    }


    /**
     * Checks that save() correctly serializes a serializable object to a path.
     */
    @Test
    void save_givenSerializableObjectAndPath_serializesObjectToPath() {
        StateFileHandler.save(TEST_SAVE_PATH, myList);
        Object o = null;
        try {
            FileInputStream fis = new FileInputStream(TEST_SAVE_PATH);
            ObjectInputStream ois = new ObjectInputStream(fis);
            o = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(myList, (List<Double>) o);
    }


    /**
     * Checks that load() correctly deserializes a serialized object from a
     * path.
     */
    @Test
    void load_givenPathToSerialized_returnsCorrectObject() {
        try {
            FileOutputStream fos = new FileOutputStream(TEST_LOAD_PATH);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(myList);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Double> actual;
        actual = (List<Double>) StateFileHandler.load(TEST_LOAD_PATH);
        assertEquals(myList, actual);
    }

}