package controller;

import model.room.Door;
import model.room.Room;

import java.util.*;






/**
 * Uses a graph representation to generate a mapping of each Room to connected
 * Rooms.
 *
 * @author Reuben Keller
 */
public class RoomManager {

    /** The graph representation of the maze. */
    private final Map<Integer, List<Integer>> myGraphRep;

    /** Stores a mapping of each Room to its connected Rooms. */
    private HashMap<Room, HashSet<Room>> rooms;

    /** The number of rows in the graph representation. */
    private final int myRows;






    /**
     * Constructs a RoomManager for a given graph representation of Rooms.
     *
     * @param theGraphRep The graph representation of connected Rooms.
     * @param theRows The number of rows in the graph representation.
     */
    public RoomManager(final Map<Integer, List<Integer>> theGraphRep,
                       final int theRows) {
        myGraphRep = theGraphRep;
        myRows = theRows;
    }







    /**
     * Builds and returns a mapping of each Room to connected Rooms.
     * Sets valid doors between rooms in the process.
     *
     * @return A mapping of each Room to connected Rooms.
     */
    public HashMap<Room, HashSet<Room>> extractRooms() {
        rooms = new HashMap<>();
        Set<Integer> handled = new HashSet<>();
        HashMap<Integer, Room> idToRoom = new HashMap<>();

        for (Integer currID : myGraphRep.keySet()) {

            Room currRoom = new Room(currID);
            HashSet<Room> s = new HashSet<>();
            if (rooms.containsKey(currRoom)) {
                s = rooms.get(currRoom);
            }

            for (Integer neighborID : myGraphRep.get(currID)) {
                Room neighborRoom = new Room(neighborID);
                if (!s.contains(neighborRoom)) {
                    Door door = new Door(true, false);
                    if (currID - myRows == neighborID) {
                        // neighbor is north of curr (north = A)
                        currRoom.setNorth(neighborRoom, door);
                        neighborRoom.setSouth(currRoom, door);
                    } else if (currID + myRows == neighborID) {
                        // neighbor is south of curr (south = B)
                        currRoom.setSouth(neighborRoom, door);
                        neighborRoom.setNorth(currRoom, door);
                    } else if (currID + 1 == neighborID) {
                        // neighbor is east of curr  (east = C)
                        currRoom.setEast(neighborRoom, door);
                        neighborRoom.setWest(currRoom, door);
                    } else if (currID - 1 == neighborID) {
                        // neighbor is west of curr  (west = D)
                        currRoom.setWest(neighborRoom, door);
                        neighborRoom.setEast(currRoom, door);
                    }
                    s.add(neighborRoom);
                }
            }
            rooms.put(currRoom, s);
        }
        return rooms;
    }


}
