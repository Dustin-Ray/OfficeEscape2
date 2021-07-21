package model;

import view.RoomPanel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class AbstractRoom {


    private final int myID;
    private final RoomPanel myRoomPanel;

    public AbstractRoom(final int theID, final int theRows, final int theCols) {
        myID = theID;
        myRoomPanel = readMapFile();
    }

    /**
     * Read the city text file and build an RoadRagePanel based on the file.
     *
     * @return the RoadRagePanel based on the city text file.
     */
    public RoomPanel readMapFile() {

        File file = new File("src/res/floor_map_" + myID + ".txt");
        RoomPanel result = null;

        try (Scanner input = new Scanner(new BufferedReader(new FileReader(file)))) {

            // First, we read the map description
            result = new RoomPanel(readGrid(input));
            input.close();
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

    public RoomPanel getRoomPanel() {
            return myRoomPanel;
    }

}
