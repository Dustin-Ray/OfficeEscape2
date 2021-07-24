//package controller;
//
//import model.room.Room;
//
//import java.util.*;
//
///**
// * Manages a collection of Rooms.
// *
// * @author Reuben Keller
// */
//public class RoomManager {
//
//    private final Map<Integer, List<Integer>> myRooms;
//    private final int myRows;
//    private final int myCols;
//
//
//    public RoomManager(final Map<Integer, List<Integer>> theRooms,
//                       final int theRows,
//                       final int theCols) {
//
//        myRooms = theRooms;
//        myRows = theRows;
//        myCols = theCols;
//    }
//
//
//    public List<Room> extractRooms() {
//        List<Room> extractedRooms = new ArrayList<>();
//        HashMap<Integer, Room> idToRoom = new HashMap<>();
//        Set<Integer> handled  = new HashSet<>();
//        for (Integer currID : myRooms.keySet()) {
//            Room curr = new Room(currID, myRows, myCols);
//            if (idToRoom.containsKey(currID)) {
//                curr = idToRoom.get(currID);
//            }
//            for (Integer neighborID : myRooms.get(currID)) {
//                if (!handled.contains(neighborID)) {
//                    Room neighbor = new Room(neighborID, myRows, myCols);
//                    if (curr.isValidWest(neighborID)) {
//                        curr.setWestRoom(neighbor);
//                        neighbor.setEastRoom(curr);
//                    } else if (curr.isValidEast(neighborID)) {
//                        curr.setEastRoom(neighbor);
//                        neighbor.setWestRoom(curr);
//                    } else if (curr.isValidNorth(neighborID)) {
//                        curr.setNorthRoom(neighbor);
//                        neighbor.setSouthRoom(curr);
//                    } else if (curr.isValidSouth(neighborID)) {
//                        curr.setSouthRoom(neighbor);
//                        neighbor.setNorthRoom(curr);
//                    }
//                    idToRoom.put(neighborID, neighbor);
//                }
//            }
//            handled.add(currID);
//            idToRoom.put(currID, curr);
//            extractedRooms.add(curr);
//        }
//        return extractedRooms;
//    }
//}
