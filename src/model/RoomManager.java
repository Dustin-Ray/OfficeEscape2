package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Manages a collection of Rooms.
 *
 * @author Reuben Keller
 */
public class RoomManager {

    private final Map<Integer, List<Integer>> myRooms;
    private final int myRows;
    private final int myCols;

    public RoomManager(final Map<Integer, List<Integer>> theRooms,
                       final int theRows,
                       final int theCols) {
        myRooms = theRooms;
        myRows = theRows;
        myCols = theCols;
    }

    public List<Room> extractRooms() {
        List<Room> extractedRooms = new ArrayList<>();
        for (Integer room : myRooms.keySet()) {
            Room curr = new Room(room, myRows, myCols);
            for (Integer perimeterRoom : myRooms.get(room)) {
                curr.setValid(perimeterRoom);
            }
            extractedRooms.add(curr);
        }
        return extractedRooms;
    }
}
