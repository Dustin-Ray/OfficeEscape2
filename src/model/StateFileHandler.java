/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model;

import java.io.*;

/**
 * A utility class for saving/loading the state of serializable game objects.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */

public final class StateFileHandler {

    /**
     * A private constructor to prevent instantiation.
     */
    private StateFileHandler() { }


    /**
     * Serializes the given object if it's serializable.
     *
     * @param savePath The path to serialize the object to.
     * @param object The object to serialize.
     */
    public static void save(final String savePath, final Object object) {
        try {
            FileOutputStream fos = new FileOutputStream(savePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Deserializes an object from the given file path.
     *
     * @param loadPath The path of the file to deserialize.
     * @return The deserialized object.
     */
    public static Object load(String loadPath) {
        Object o = null;
        try {
            FileInputStream fis = new FileInputStream(loadPath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            o = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }

}
