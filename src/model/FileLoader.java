package model;

import view.GamePanel;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public final class FileLoader {



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

        File file = new File("src/res/city_map1.txt");
        System.out.println(file.length());
        GamePanel result = null;

        try (Scanner input = new Scanner(new BufferedReader(new FileReader(file)))) {

            // First, we read the map description
            result = new GamePanel(readGrid(input));
            input.close();
        } catch (final IOException ioe) {
            JOptionPane.showMessageDialog(theFrame, "failed to load a resource file somewhere, check paths " + file.getName()
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
