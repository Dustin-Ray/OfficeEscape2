package model.map;

import model.map.Terrain;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

import static model.map.Terrain.*;

public class TerrainGrid {

    private static final String ROOM_A = "0";

    private static final String ROOM_B = "1";

    private static final String ROOM_C = "2";

    private static final String ROOM_D = "3";

    private static final String RED_ZONE = "-1";

    private static final int PADDING = 2;

    private final int myRows;

    private final int myCols;

    private final String myPath;

    private final Terrain[][] myGrid;


    public TerrainGrid(final int theRows, final int theCols, String thePath) {
        myRows = theRows;
        myCols = theCols;
        myPath = thePath;

        myGrid = new Terrain[myRows + PADDING][myCols + PADDING];
        for (Terrain[] row : myGrid) {
            Arrays.fill(row, Terrain.RED_ZONE);
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
                    Terrain terrain = Terrain.RED_ZONE;
                    if (curr.equals(ROOM_A)) {
                        terrain = DOOR_CLOSED_A;
                    } else if (curr.equals(ROOM_B)) {
                        terrain = DOOR_CLOSED_B;
                    } else if (curr.equals(ROOM_C)) {
                        terrain = DOOR_CLOSED_C;
                    } else if (curr.equals(ROOM_D)) {
                        terrain = DOOR_CLOSED_D;
                    } else if (!curr.equals(RED_ZONE)) {
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
