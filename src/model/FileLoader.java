package model;

import view.GamePanel;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public final class FileLoader {

    /**
     * The filename of the city map.
     */
    private static final String CITY_FILE = "city_map1.txt";

    /**
     * A private constructor, to prevent external instantiation.
     */
    private FileLoader() {
    }

    /**
     * Read the city text file and build an RoadRagePanel based on the file.
     *
     * @param theFrame the Frame containing the panel. Needed for error output.
     * @return the RoadRagePanel based on the city text file.
     */
    public static GamePanel readCity(final JFrame theFrame) {
        GamePanel result = null;
        try (Scanner input = new Scanner(new File("src/res/city_map1.txt"))) {

            // First, we read the map description
            // Then, we read where the initial vehicles are

            result = new GamePanel(readGrid(input));

            input.close();

        } catch (final IOException ioe) {
            JOptionPane.showMessageDialog(theFrame, "Could not read city map file " + CITY_FILE
                            + ":\n\n" + ioe.getMessage(), "I/O Error",
                    JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }


    /**
     * Reads the grid portion of the map file.
     *
     * @param theInput The input scanner.
     * @return the map of the terrains.
     */
    private static Terrain[][] readGrid(final Scanner theInput) {
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
}
