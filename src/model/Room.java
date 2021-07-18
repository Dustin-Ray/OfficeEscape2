package model;

/**
 * Maintains room information.
 *
 * @author Reuben Keller
 */
public class Room {

    private final Door northDoor;

    private final Door southDoor;

    private final Door eastDoor;

    private final Door westDoor;

    private Room northRoom;

    private Room southRoom;

    private Room eastRoom;

    private Room westRoom;

    private final int myID;

    private final int myRows;

    private final int myCols;


    public Room(final int theID, final int theRows, final int theCols) {
        northDoor = new Door();
        southDoor = new Door();
        eastDoor = new Door();
        westDoor = new Door();
        myID = theID;
        myRows = theRows;
        myCols = theCols;
    }

    public int getRoomID() {
        return myID;
    }

    public boolean northIsValid(final int id) {
        return myID - myCols == id;
    }

    public boolean northIsValid() {
        return roomIsValid(northRoom);
    }

    public boolean southIsValid(final int id) {
        return myID + myCols == id;
    }

    public boolean southIsValid() {
        return roomIsValid(southRoom);
    }

    public boolean eastIsValid(final int id) {
        return myID - 1 == id;
    }

    public boolean eastIsValid() {
        return roomIsValid(eastRoom);
    }

    public boolean westIsValid(final int id) {
        return myID + 1 == id;
    }

    public boolean westIsValid() {
        return roomIsValid(westRoom);
    }

    private boolean roomIsValid(final Room room) {
        return room != null;
    }

    public void setValid(final int id) {
        if (northIsValid(id)) {
            northDoor.setDoor(true);
            northRoom = new Room(id, myRows, myCols);
        }
        if (southIsValid(id)) {
            southDoor.setDoor(true);
            southRoom = new Room(id, myRows, myCols);
        }
        if (eastIsValid(id)) {
            eastDoor.setDoor(true);
            eastRoom = new Room(id, myRows, myCols);
        }
        if (westIsValid(id)) {
            westDoor.setDoor(true);
            westRoom = new Room(id, myRows, myCols);
        }
    }

    public Door getNorthDoor() {
        return northDoor;
    }


    /** returns a list of doors for this room */
    public void getDoors() {
    }


    /**Sets desired door to the room number passed in*/
    public void setDoors(final int roomNumber) {

    }



}
