package model.graph;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Implements unit tests for KruskalMSTFinder.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class KruskalMSTFinderTest {

    /** The 'from' vertices of Edges in the known graph. */
    private static final int[] GRAPH_FROM = {
            1, 1, 2, 2, 3, 4, 5, 4, 7, 5, 8, 6
    };

    /** The 'to' vertices of Edges in the known graph. */
    private static final int[] GRAPH_TO = {
            2, 4, 3, 5, 6, 5, 6, 7, 8, 8, 9, 9
    };

    /** The weights of Edges in the known graph. */
    private static final double[] GRAPH_WEIGHTS = {
            2.0, 2.0, 3.0, 2.0, 4.0, 4.0, 1.0, 3.0, 1.0, 2.0, 3.0, 5.0
    };

    /** The 'from' vertices of Edges in the expected MST. */
    private static final int[] MST_FROM = {
            2, 8, 1, 2, 5, 5, 7, 1
    };

    /** The 'to' vertices of Edges in the MST for testing. */
    private static final int[] MST_TO = {
            3, 9, 4, 5, 8, 6, 8, 2
    };

    /** The weights of Edges in the MST for testing. */
    private static final double[] MST_WEIGHTS = {
            3.0, 3.0, 2.0, 2.0, 2.0, 1.0, 1.0, 2.0
    };

    /** The graph to run method from KruskalMSTFinder on. */
    private final AdjacencyListGraph<Integer> graph;

    /** The expected MST of the graph. */
    private final Set<Edge<Integer>> mst;

    /** The expected mapping of vertices to connected vertices in the MST. */
    private final Map<Integer, Set<Integer>> vertexMap;

    /** The object containing methods to test. */
    private final KruskalMSTFinder<Integer> mstFinder;


    /**
     * Constructs a KruskalMSTFinderTest, initializing the expected results of
     * tests.
     */
    public KruskalMSTFinderTest() {
        graph = new AdjacencyListGraph<>();
        mst = new HashSet<>();
        vertexMap = new HashMap<>();
        mstFinder = new KruskalMSTFinder<>();
        buildGraph();
        buildMSTAndVertexMap();
    }


    /**
     * Builds the graph to test KruskalMSTFinder with.
     */
    private void buildGraph() {
        for (int i = 0; i < GRAPH_FROM.length; i++) {
            graph.addDirectedEdge(
                    GRAPH_FROM[i],
                    GRAPH_TO[i],
                    GRAPH_WEIGHTS[i]
            );
        }
    }


    /**
     * Builds the expected MST and vertex map to test KruskalMSTFinder with.
     */
    private void buildMSTAndVertexMap() {
        for (int i = 0; i < MST_FROM.length; i++) {
            int from  = MST_FROM[i];
            int to = MST_TO[i];
            mst.add(new Edge<>(from, to, MST_WEIGHTS[i]));
            vertexMap.computeIfAbsent(from, k -> new HashSet<>());
            vertexMap.get(from).add(to);
            vertexMap.computeIfAbsent(to, k -> new HashSet<>());
            vertexMap.get(to).add(from);
        }
    }


    /**
     * Checks that sortEdgesByWeight() returns a list of edges in a graph
     * sorted by non-decreasing order in weight.
     */
    @Test
    void sortEdgesByWeight_givenGraph_returnsCorrectlySortedEdges() {
        String msg = "sortEdgesByWeight() should return a list of edges " +
                "in ascending order by weight";
        List<Edge<Integer>> expectedEdges = new ArrayList<>(graph.edges());
        expectedEdges.sort(Comparator.comparingDouble(Edge::weight));
        assertEquals(expectedEdges, mstFinder.sortEdgesByWeight(graph), msg);
    }

    /**
     * Checks that makeDisjointSets() makes a disjoint set for each vertex
     * in a given graph and returns a correct UnionFindDisjointSet data
     * structure.
     */
    @Test
    void makeDisjointSets_givenGraph_returnsCorrectUnionFindDisjointSet() {
        String msg = "makeDisjointSets() should make a disjoint set for each" +
                "vertex in the given graph";
        UnionFindDisjointSet<Integer> expected;
        expected = new UnionFindDisjointSet<>();
        UnionFindDisjointSet<Integer> actual;
        actual = mstFinder.makeDisjointSets(graph);
        for (Integer vertex : graph.vertices()) {
            expected.makeSet(vertex);
            assertEquals(expected.findSet(vertex), actual.findSet(vertex), msg);
        }
    }


    /**
     * Checks that addToVertexMap() produces a correct mapping of each vertex
     * to connected vertices in an MST.
     */
    @Test
    void addToVertexMap_returnsCorrectVertexMap() {
        String msg = "addToVertexMap() does not map each vertex to its" +
                "connected vertices in the MST";
        for (int i = 0; i < MST_FROM.length; i++) {
            int from  = MST_FROM[i];
            int to = MST_TO[i];
            mstFinder.addToVertexMap(from, to);
        }
        assertEquals(vertexMap, mstFinder.getVertexMap(), msg);
    }


    /**
     * Checks that findMST() on a Graph returns the correct MST.
     */
    @Test
    void findMST_onNonEmptyGraph_returnsCorrectMST() {
        String msg = "findMST() does not return the correct MST";
        assertEquals(mst, mstFinder.findMST(graph), msg);
    }


    /**
     * Checks that getVertexMap() returns the correct vertex map of vertices in
     * the MST.
     */
    @Test
    void getVertexMap_afterFindMST_returnsVertexMapOfMST() {
        String msg = "getVertexMap() after findMST() should return the" +
                " vertex map for the MST";
        mstFinder.findMST(graph);
        Map<Integer, Set<Integer>> actual = mstFinder.getVertexMap();
        assertEquals(vertexMap, mstFinder.getVertexMap(), msg);
    }

}