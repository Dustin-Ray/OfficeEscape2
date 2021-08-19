/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.room;

import model.graph.*;
import model.trivia.TriviaManager;

import java.util.*;


/**
 * A helper class to build and connect Rooms, Doors, and Trivia. Uses a Graph
 * representation of a 2D array of Rooms to generate a mapping of each Room to
 * its connected Rooms.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class RoomBuilder {

    /** The integer ID of the starting vertex in the default Graph. */
    public static final int DEFAULT_SOURCE = 0;

    /** The integer ID of the target vertex in the default Graph. */
    public static final int DEFAULT_TARGET = 15;

    /** The number of rows in the default Graph. */
    public static final int DEFAULT_ROWS = 4;

    /** The number of columns in the default Graph */
    public static final int DEFAULT_COLS = 4;

    /** The Graph representation of rooms to manage. */
    private final AdjacencyListGraph<Integer> myGraph;

    /** The KruskalMSTFinder to use on myGraph. */
    private final KruskalMSTFinder<Integer> myMSTFinder;

    /** The MST of myGraph. */
    private Set<Edge<Integer>> myMST;

    /** A Random object for generating pseudo-random edge weights. */
    private final Random myRand;

    /** A mapping of each Room to its connected Rooms. */
    private Map<Room, Set<Room>> rooms;

    /** A helper class to get random trivia. */
    private final TriviaManager myTriviaManager;

    /** An ordered list of Rooms in the optimal path. */
    private final List<Integer> optimalSolution;

    /** The number of rows in the Graph. */
    private final int myNumRows;

    /** The number of columns in the Graph. */
    private final int myNumCols;

    /** The source vertex in the Graph. */
    private final int mySource;

    /** The target vertex in the Graph. */
    private final int myTarget;

    private List<Room> myRoomsList;


    /**
     * Constructs a RoomBuilder for the Graph representation of a 2D array of
     * Rooms with the default number of rooms (16), default number of rows (4),
     * and default number of columns (4).
     */
    public RoomBuilder() {
        this(DEFAULT_ROWS, DEFAULT_COLS, DEFAULT_SOURCE, DEFAULT_TARGET);
    }


    /**
     * Constructs a RoomBuilder for given row and column dimensions, a source
     * vertex, and a target vertex.
     *
     * @param theNumRows The number of Room rows.
     * @param theNumCols The number of Room columns.
     * @param theSource The ID of the source (starting) vertex.
     * @param theTarget The ID of the target vertex.
     * @throws IllegalArgumentException if any of the given parameters are
     *     negative.
     */
    public RoomBuilder(final int theNumRows, final int theNumCols,
                       final int theSource, final int theTarget) {
        if (theNumRows < 0 || theNumCols < 0 || theSource < 0
                || theTarget < 0) {
            throw new IllegalArgumentException(
                    "theNumRows and theNumCols" + " are negative"
            );
        }
        myNumRows = theNumRows;
        myNumCols = theNumCols;
        mySource = theSource;
        myTarget = theTarget;
        myGraph = new AdjacencyListGraph<>();
        myMSTFinder = new KruskalMSTFinder<>();
        myTriviaManager = new TriviaManager();
        myRand = new Random();
        optimalSolution = new ArrayList<>();
        buildGraph();
        generateMST();
        extractRoomsMap();
        buildRoomsList();
        extractOptimalSolution();
    }


    /**
     * Returns the number of rows for this RoomBuilder.
     *
     * @return The number of rows for this RoomBuilder.
     */
    public int getNumRows() {
        return myNumRows;
    }


    /**
     * Returns the number of columns for this RoomBuilder.
     *
     * @return Returns the number of columns for this RoomBuilder.
     */
    public int getNumCols() {
        return myNumCols;
    }


    /**
     * Returns the ID of the source for this RoomBuilder.
     *
     * @return The ID of the source for this RoomBuilder.
     */
    public int getSource() {
        return mySource;
    }


    /**
     * Returns the ID of the target for this RoomBuilder.
     *
     * @return The ID of the target for this RoomBuilder.
     */
    public int getTarget() {
        return myTarget;
    }


    /**
     * Generates the Graph corresponding to a 2D array of Rooms. Each vertex is
     * numbered in integer steps of 1 from left to right and row by row,
     * starting with 0 in the top left corner.
     *
     * e.g., if myNumRows = 3 and myNumCols = 3, then the visual representation
     * of the generated graph is as follows:
     *      0---1---2
     *      |   |   |
     *      3---4---5
     *      |   |   |
     *      6---7---8
     */
    private void buildGraph() {
        for (int j = 0; j < myNumRows * myNumCols; j++) {
            // horizontal edges
            if ((j + 1) % myNumRows != 0) {
                myGraph.addUndirectedEdge(j, j + 1, myRand.nextDouble());
            }
            // vertical edges
            if (j + myNumCols < myNumRows * myNumCols) {
                myGraph.addUndirectedEdge(j, j + myNumCols, myRand.nextDouble());
            }
        }
    }


    /**
     * Returns the Graph representation of the Rooms.
     *
     * @return The Graph representation of the Rooms.
     */
    public Graph<Integer> getGraph() {
        return myGraph;
    }


    /**
     * Generates the MST of the Graph generated by generateGraph() and returns
     * a mapping of each room to its connected rooms.
     */
    private void generateMST() {
        myMST = myMSTFinder.findMST(myGraph);
    }


    /**
     * Returns the MST of the Graph representation of Rooms.
     *
     * @return The MST of the Graph representation of Rooms.
     */
    public Set<Edge<Integer>> getMST() {
        return myMST;
    }


    /**
     * Builds and returns a mapping of each Room to connected Rooms. Sets valid
     * Doors with Trivia between connected Rooms in the process.
     */
    private void extractRoomsMap() {
        Map<Integer, Set<Integer>> connectedVertices;
        connectedVertices = myMSTFinder.getVertexMap();
        rooms = new HashMap<>();
        for (Integer currID : connectedVertices.keySet()) {
            Room curr = new Room(currID);
            Set<Room> neighbors = new HashSet<>();
            if (rooms.containsKey(curr)) {
                neighbors = rooms.get(curr);
            }
            for (Integer neighborID : connectedVertices.get(currID)) {
                Room neighbor = new Room(neighborID);
                if (!neighbors.contains(neighbor)) {
                    setupRoom(curr, neighbor);
                    neighbors.add(neighbor);
                }
            }
            rooms.put(curr, neighbors);
        }
    }


    /**
     * Given a Room and one of its Room neighbors, the method sets up the Room
     * by coupling a Door containing Trivia to both the Room and its neighbor.
     * In the Graph of the 2D array representation of Rooms, A corresponds to
     * north, B corresponds to south, C corresponds to east, and D corresponds
     * to west.
     *
     * @param room The Room to setup.
     * @param neighbor A neighbor of room.
     */
    private void setupRoom(final Room room, final Room neighbor) {
        int currID = room.getRoomID();
        int neighborID = neighbor.getRoomID();
        Door door = new Door(true, false, myTriviaManager.getTrivia());
        if (currID - myNumRows == neighborID) {
            room.setA(neighbor, door);
            neighbor.setB(room, door);
        } else if (currID + myNumRows == neighborID) {
            room.setB(neighbor, door);
            neighbor.setA(room, door);
        } else if (currID + 1 == neighborID) {
            room.setC(neighbor, door);
            neighbor.setD(room, door);
        } else if (currID - 1 == neighborID) {
            room.setD(neighbor, door);
            neighbor.setC(room, door);
        }
    }


    /**
     * Extracts the optimal (shortest) sequence of rooms to go through to get
     * from mySource to myTarget.
     */
    private void extractOptimalSolution() {
        Graph<Integer> kruskalGraph = myMSTFinder.getKruskalGraph();
        DijkstraSPFinder<Integer> spFinder = new DijkstraSPFinder<>();

        Map<Integer, Edge<Integer>> spt;
        spt = spFinder.shortestPathTree(kruskalGraph, mySource, myTarget);

        List<Edge<Integer>> edges;
        edges = spFinder.extractShortestPath(spt, mySource, myTarget);

        for (Edge<Integer> edge : edges) {
            optimalSolution.add(edge.from());
        }

        optimalSolution.add(edges.get(edges.size() - 1).to());
    }


    /**
     * Returns the optimal sequence of Room IDs from the starting Room to the
     * ending Room as a List.
     *
     * @return An ordered List of Room IDs representing the sequence of optimal
     * Rooms to take.
     */
    public List<Integer> getOptimalSolution() {
        return optimalSolution;
    }


    /**
     * Builds a sorted list of Room objects.
     */
    private void buildRoomsList() {
        myRoomsList = new ArrayList<>(rooms.keySet());
        myRoomsList.sort(Comparator.comparingInt(Room::getRoomID));
    }


    /**
     * Returns a list of Room objects in non-decreasing order of Room ID.
     *
     * @return A list of Room objects in non-decreasing order of Room ID.
     */
    public List<Room> getRoomsList() {
        return myRoomsList;
    }

}
