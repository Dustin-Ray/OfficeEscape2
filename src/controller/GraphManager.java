package controller;

import model.graph.AdjacencyListGraph;
import model.graph.Edge;
import model.graph.KruskalMSTFinder;

import java.util.HashSet;
import java.util.Random;

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
    private AdjacencyListGraph<Integer> graph;

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
    }


    /**
     * Generates the Graph for this GraphManager.
     */
    public void generateGraph() {
        for (int j = 0; j < myNumRooms; j++) {
            // horizontal edges
            if ((j + 1) % myRows != 0) {
                double weight = rand.nextDouble();
                graph.addEdge(j, j + 1, weight);
                // graph.addEdge(j + 1, j, weight);
            }
            // vertical edges
            if (j + myCols < myNumRooms) {
                double weight = rand.nextDouble();
                graph.addEdge(j, j + myCols, weight);
                // graph.addEdge(j + myCols, j, weight);
            }
        }
    }


    /**
     * Generates the MST of the Graph of this GraphManager.
     */
    public void generateMST() {
        KruskalMSTFinder mst = new KruskalMSTFinder(graph);
        HashSet<Edge> mstEdges = mst.findMST();
        System.out.println(mstEdges);
    }
}

