package model.room;

/**
 * Maintains room information.
 *
 * @author Reuben Keller
 */
public class Room extends AbstractRoom {

//    private Door northDoor;
//    private Door southDoor;
//    private Door eastDoor;
//    private Door westDoor;
//    private Room northRoom;
//    private Room southRoom;
//    private Room eastRoom;
//    private Room westRoom;
//
//    private final int myID;
//    private final int myRows;
//    private final int myCols;

    public Room(final int theID,
                final int theRows,
                final int theCols) {
        super(theID, theRows, theCols);
//        myID = theID;
//        myRows = theRows;
//        myCols = theCols;
    }

//    public int getRoomID() {
//        return myID;
//    }
//    public void setNorthRoom(final Room room) {
//        northRoom = room;
//        northDoor = new Door(true, false);
//    }
//
//    public void setSouthRoom(final Room room) {
//        southRoom = room;
//        southDoor = new Door(true, false);
//    }
//
//    public void setWestRoom(final Room room) {
//        westRoom = room;
//        westDoor = new Door(true, false);
//    }
//
//    public void setEastRoom(final Room room) {
//        eastRoom = room;
//        eastDoor = new Door(true, false);
//    }
//
//
//
//    public Room getNorthRoom() {
//        return northRoom;
//    }
//    public Room getSouthRoom() {
//        return southRoom;
//    }
//    public Room getWestRoom() {
//        return westRoom;
//    }
//    public Room getEastRoom() {
//        return eastRoom;
//    }
//    public Door getNorthDoor() {
//        return northDoor;
//    }
//    public Door getSouthDoor() {
//        return southDoor;
//    }
//    public Door getEastDoor() {
//        return eastDoor;
//    }
//    public Door getWestDoor() {
//        return westDoor;
//    }
//
//    public boolean northRoomValid() {
//        return roomIsValid(northRoom);
//    }
//    public boolean eastRoomValid() {
//        return roomIsValid(eastRoom);
//    }
//    public boolean southRoomValid() {
//        return roomIsValid(southRoom);
//    }
//    public boolean westRoomValid() {
//        return roomIsValid(westRoom);
//    }
//    private boolean roomIsValid(final Room room) {
//        return room != null;
//    }
//
//    /**
//     * Checks if the Room with the given id is north of this Room.
//     *
//     * @param id The integer representing the Room to check.
//     * @return true if the Room with id is north of this Room.
//     */
//    public boolean isValidNorth(final int id) {
//        return myID - myCols == id;
//    }
//
//
//    /**
//     * Checks if the Room with the given id is south of this Room.
//     *
//     * @param id The integer representing the Room to check.
//     * @return true if the Room with id is south of this Room.
//     */
//    public boolean isValidSouth(final int id) {
//        return myID + myCols == id;
//    }
//
//
//    /**
//     * Checks if the Room with the given id is east of this Room.
//     *
//     * @param id The integer representing the Room to check.
//     * @return true if the Room with id is east of this Room.
//     */
//    public boolean isValidEast(final int id) {
//        return myID + 1 == id;
//    }
//
//    /**
//     * Checks if the Room with the given id is west of this Room.
//     *
//     * @param id The integer representing the Room to check.
//     * @return true if the Room with id is west of this Room.
//     */
//    public boolean isValidWest(final int id) {
//        return myID - 1 == id;
//    }
//
//    @Override
//    public String toString() {
//        // (N, S, W, E)
//        StringBuilder sb = new StringBuilder();
//        sb.append(myID);
//        sb.append(" = (");
//        sb.append("north = ");
//        if (northRoomValid()) {
//            sb.append(northRoom.getRoomID() + ", ");
//        } else {
//            sb.append("null, ");
//        }
//        sb.append("south = ");
//        if (southRoomValid()) {
//            sb.append(southRoom.getRoomID() + ", ");
//        } else {
//            sb.append("null, ");
//        }
//        sb.append("west = ");
//        if (westRoomValid()) {
//            sb.append(westRoom.getRoomID() + ", ");
//        } else {
//            sb.append("null, ");
//        }
//        sb.append("east = ");
//        if (eastRoomValid()) {
//            sb.append(eastRoom.getRoomID());
//        } else {
//            sb.append("null");
//        }
//        sb.append(")");
//        return sb.toString();
//    }
}