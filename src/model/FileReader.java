package model;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;

public class FileReader {

    private static final String ROOM_A = "5";

    private static final String ROOM_B = "184";

    private static final String ROOM_C = "102";

    private static final String ROOM_D = "98";

    private static final String INVALID = "-1";

    private static final int PADDING = 2;

    private final int myRows;

    private final int myCols;

    private final String myPath;

    private String[][] myGrid;


    public FileReader(final int theRows, final int theCols, String thePath) {
        myRows = theRows;
        myCols = theCols;
        myPath = thePath;

        myGrid = new String[myRows + PADDING][myCols + PADDING];
        for (String[] row : myGrid) {
            Arrays.fill(row, "R");
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
                    if (curr.equals(ROOM_A)) {
                        curr = "A";
                    } else if (curr.equals(ROOM_B)) {
                        curr = "B";
                    } else if (curr.equals(ROOM_C)) {
                        curr = "C";
                    } else if (curr.equals(ROOM_D)) {
                        curr = "D";
                    } else if (!curr.equals(INVALID)) {
                        curr = "F";
                    } else {
                        curr = "R";
                    }
                    myGrid[row][col] = curr;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[][] getGrid() {
        return myGrid;
    }
}
