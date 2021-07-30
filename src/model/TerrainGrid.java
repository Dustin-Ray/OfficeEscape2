package model;

import model.room.Terrain;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import static model.room.Terrain.*;

public class TerrainGrid {

    private static final String ROOM_A = "77";

    private static final String ROOM_B = "78";

    private static final String ROOM_C = "94";

    private static final String ROOM_D = "93";

    private static final String INVALID = "-1";

    private static final int PADDING = 2;

    private final int myRows;

    private final int myCols;

    private final String myPath;

    private Terrain[][] myGrid;


    public TerrainGrid(final int theRows, final int theCols, String thePath) {
        myRows = theRows;
        myCols = theCols;
        myPath = thePath;

        myGrid = new Terrain[myRows + PADDING][myCols + PADDING];
        for (Terrain[] row : myGrid) {
            Arrays.fill(row, RED_ZONE);
        }

        translateFile();
    }


    private void translateFile() {
        try {
            Scanner sc = new Scanner(new File(myPath));
            for (int row = 1; row < myRows + 1; row++) {
                String[] line = sc.nextLine().split(",");
                for (int col = 1; col < myCols + 1; col++) {
                    String curr = line[col - 1];
                    Terrain terrain = RED_ZONE;
                    if (curr.equals(ROOM_A)) {
                        terrain = DOOR_CLOSED_A;
                    } else if (curr.equals(ROOM_B)) {
                        terrain = DOOR_CLOSED_B;
                    } else if (curr.equals(ROOM_C)) {
                        terrain = DOOR_CLOSED_C;
                    } else if (curr.equals(ROOM_D)) {
                        terrain = DOOR_CLOSED_D;
                    } else if (!curr.equals(INVALID)) {
                        terrain = FLOOR_1;
                    }
                    myGrid[row][col] = terrain;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Terrain[][] getGrid() {
        return myGrid;
    }

}