package model.graph;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * Manages a graph representation of the maze.
 *
 * @author Reuben Keller
 */
public class GraphManager {

    /** The number of room rows. */
    private final int myRows;

    /** The number of room cols. */
    private final int myCols;

    /** The total number of rooms. */
    private final int myNumRooms;

    /** The Graph to manage. */
    private final AdjacencyListGraph<Integer> graph;

    private KruskalMSTFinder<Integer> mstFinder;

    /** A Random object for generating pseudo-random edge weights. */
    private final Random rand;



    /**
     * Constructs a Graph Manager to manage a Graph containing vertices in a
     * square lattice with the given number of rows and number of columns.
     *
     * @param theRows The number of lattice rows.
     * @param theCols The number of lattice cols.
     */
    public GraphManager(final int theRows, final int theCols) {
        myRows = theRows;
        myCols = theCols;
        myNumRooms = theRows * theCols;
        graph = new AdjacencyListGraph<>();
        rand = new Random();
        generateGraph();
        generateMST();
    }


    /**
     * Generates the Graph for this GraphManager.
     */
    private void generateGraph() {
        for (int j = 0; j < myNumRooms; j++) {
            // horizontal edges
            if ((j + 1) % myRows != 0) {
                double weight = rand.nextDouble();
                graph.addEdge(j, j + 1, weight);
            }
            // vertical edges
            if (j + myCols < myNumRooms) {
                double weight = rand.nextDouble();
                graph.addEdge(j, j + myCols, weight);
            }
        }
    }


    /**
     * Generates the MST of the Graph of this GraphManager.
     */
    private void generateMST() {
        mstFinder = new KruskalMSTFinder<>(graph);
    }

    /**
     * Returns the MST of the Graph of this GraphManager.
     *
     * @return The MST of the Graph of this GraphManager.
     */
    public Set<Edge<Integer>> getMST() {
        return mstFinder.getMST();
    }

    /**
     * Returns the a mapping of connected rooms
     *
     * @return A mapping between connected rooms.
     */
    public Map<Integer, List<Integer>> getConnectedRoomsMap() {
        return mstFinder.getVertexMap();
    }
 }

