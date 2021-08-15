/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Dustin Ray, Raz Consta, Reuben Keller
 */

package model.graph;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * Implements unit tests for AdjacencyListGraph.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class AdjacencyListGraphTest {

    /** An array of expected 'from' vertices. */
    private static final String[] EXPECTED_FROM = {
            "A", "B", "A", "D", "B", "C", "B", "F", "D", "E", "E", "F"
    };

    /** An array of expected 'to' vertices. */
    private static final String[] EXPECTED_TO = {
            "B", "A", "D", "A", "C", "B", "F", "B", "E", "D", "F", "E"
    };

    /** A default weight to test with. */
    private static final double WEIGHT= 3.141594;

    /** An empty AdjacencyListGraph to test methods on. */
    private final AdjacencyListGraph<String> testGraph;


    /**
     * Constructs an AdjacencyListGraph to test.
     */
    public AdjacencyListGraphTest() {
        testGraph = new AdjacencyListGraph<>();
    }


    /**
     * Checks that addDirectedEdge() adds the correct edge with default weight
     * 0.0
     */
    @Test
    void addDirectedUnweightedEdge_onEmptyGraph_addsCorrectEdge() {
        Set<Edge<String>> expected = new HashSet<>();
        expected.add(new Edge<>("A", "B", 0.0));
        testGraph.addDirectedEdge("A", "B");
        assertEquals(expected, testGraph.edges());
    }


    /**
     * Checks that addUndirectedEdge(a, b, weight) adds two Edges (a, b) and
     * (b, a) with the given weight.
     */
    @Test
    void addDirectedWeightedEdge_onEmptyGraph_addsCorrectEdge() {
        Set<Edge<String>> expected = new HashSet<>();
        expected.add(new Edge<>("A", "B", WEIGHT));
        testGraph.addDirectedEdge("A", "B", WEIGHT);
        assertEquals(expected, testGraph.edges());
    }


    /**
     * Checks that addUndirectedEdge(a, b) adds two Edges (a, b) and (b, a)
     * with default weight 0.0 to the graph.
     */
    @Test
    void addUndirectedUnweightedEdge_onEmptyGraph_addsCorrectEdges() {
        String msg = "addUndirectedEdge(a, b) should add two edges (a, b)" +
                " and (b, a), both with default weight 0.0";
        Set<Edge<Integer>> expected = new HashSet<>();
        Edge<Integer> edge = new Edge<>(1, 2);
        expected.add(edge);
        expected.add(edge.reverse());
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        graph.addUndirectedEdge(1, 2);
        assertEquals(expected, graph.edges(), msg);
    }


    /**
     * Checks that addUndirectedEdge(a, b, weight) adds two Edges (a, b) and
     * (b, a) with the given weight.
     */
    @Test
    void addUndirectedWeightedEdge_onEmptyGraph_addsCorrectEdges() {
        String msg = "addUndirectedEdge(a, b, weight) should add two edges" +
                "(a, b) and (b, a), both with the given weight";
        Set<Edge<Integer>> expected = new HashSet<>();
        Edge<Integer> edge = new Edge<>(1, 2, WEIGHT);
        expected.add(edge);
        expected.add(edge.reverse());
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        graph.addUndirectedEdge(1, 2, WEIGHT);
        assertEquals(expected, graph.edges(), msg);
    }


    /**
     * Checks that addUndirectedEdge adds a single correct edge to the
     * unique edge set after each call.
     */
    @Test
    void edges_afterAddUndirectedEdge_isSameAsAfterAddDirectedEdge() {
        String msg = "each call to addUndirectedEdge() should only add a " +
                "single Edge to the set of unique edges";
        AdjacencyListGraph<String> expectedGraph = new AdjacencyListGraph<>();
        for (int i = 0; i < EXPECTED_FROM.length; i++) {
            testGraph.addUndirectedEdge(EXPECTED_FROM[i], EXPECTED_TO[i]);
            expectedGraph.addDirectedEdge(EXPECTED_FROM[i], EXPECTED_TO[i]);
        }
        assertEquals(expectedGraph.edges(), testGraph.edges(), msg);
        assertEquals(expectedGraph.numEdges(), testGraph.numEdges(), msg);
    }


    /**
     * Checks that edgesFrom() throws IllegalArgumentException when given a
     * null vertex.
     */
    @Test
    void edgesFrom_givenNullVertex_throwsException() {
        String msg = "edgesFrom() should throw an exception given null vertex";
        assertThrows(IllegalArgumentException.class,
                () -> testGraph.edgesFrom(null), msg);
    }


    /**
     * Checks that edgesFrom() on a non-null vertex in an undirected
     * AdjacencyListGraph returns the correct outgoing edges.
     */
    @Test
    void edgesFrom_onVertexInUndirectedGraph_returnsCorrectOutgoingEdges() {
        String msg = "edgesFrom() on vertex should return all outgoing edges";
        Set<Edge<String>> expected = new HashSet<>();
        for (int i = 0; i < EXPECTED_FROM.length; i++) {
            testGraph.addUndirectedEdge(
                    EXPECTED_FROM[i],
                    EXPECTED_TO[i],
                    WEIGHT
            );
        }
        expected.add(new Edge<>("B", "A", WEIGHT));
        expected.add(new Edge<>("B", "F", WEIGHT));
        expected.add(new Edge<>("B", "C", WEIGHT));
        assertEquals(expected, testGraph.edgesFrom("B"), msg);
    }


    /**
     * Checks whether an AdjacencyListGraph has the expected Edges after each
     * call to addDirectedEdge(a, b, weight).
     */
    @Test
    void addDirectedEdge_calledManyTimes_returnsCorrectEdgesAfterEachCall() {
        String msg = "addDirectedEdge() does not correctly update edges";
        Set<Edge<String>> expected = new HashSet<>();
        for (int i = 0; i < EXPECTED_FROM.length; i++) {
            testGraph.addDirectedEdge(
                    EXPECTED_FROM[i],
                    EXPECTED_TO[i],
                    WEIGHT
            );
            expected.add(new Edge<>(
                    EXPECTED_FROM[i],
                    EXPECTED_TO[i],
                    WEIGHT)
            );
            assertEquals(expected, testGraph.edges(), msg);
        }
    }


    /**
     * Checks whether an AdjacencyListGraph has the expected Edges after each
     * call to addUndirectedEdge(a, b, weight).
     */
    @Test
    void addUndirectedEdge_calledManyTimes_returnsCorrectEdgesAfterEachCall() {
        String msg = "addUndirectedEdge() does not correctly update edges";
        Set<Edge<String>> expected = new HashSet<>();
        for (int i = 0; i < EXPECTED_FROM.length; i++) {
            Edge<String> edge = new Edge<>(
                    EXPECTED_FROM[i],
                    EXPECTED_TO[i],
                    WEIGHT
            );
            expected.add(edge);
            expected.add(edge.reverse());

            testGraph.addUndirectedEdge(
                    EXPECTED_FROM[i],
                    EXPECTED_TO[i],
                    WEIGHT
            );
            assertEquals(expected, testGraph.edges(), msg);
        }
    }


    /**
     * Checks that edges() returns an empty set for an empty AdjacencyListGraph
     */
    @Test
    void edges_onEmptyGraph_returnsEmptySet() {
        String msg = "edges() should return an empty set for an empty graph";
        assertEquals(new HashSet<>(), testGraph.edges(), msg);
    }




    /**
     * Checks that vertices() on an empty AdjacencyListGraph returns an empty
     * set.
     */
    @Test
    void vertices_onEmptyGraph_returnsEmptySet() {
        String msg = "vertices() should return an empty set on an empty graph";
        assertEquals(new HashSet<>(), testGraph.vertices(), msg);
    }


    /**
     * Checks that vertices() on a non-empty AdjacencyListGraph returns the
     * correct vertices.
     */
    @Test
    void vertices_onNonEmptyGraph_returnsCorrectVertices() {
        String msg = "vertices() should return all the vertices in the graph";
        testGraph.addUndirectedEdge("P", "Q");
        testGraph.addUndirectedEdge("Q", "S");
        testGraph.addUndirectedEdge("R", "T");
        assertEquals(new HashSet<>(Arrays.asList("P", "Q", "R", "S", "T")),
                testGraph.vertices(), msg);
    }


    /**
     * Checks that numVertices() returns zero for an empty AdjacencyListGraph.
     */
    @Test
    void numVertices_onEmptyGraph_returnsZero() {
        String msg = "numVertices() should return zero for an empty graph";
        assertEquals(0, testGraph.numVertices(), msg);
    }


    /**
     * Checks that numVertices() returns the correct number of vertices for a
     * non-empty AdjacencyListGraph.
     */
    @Test
    void numVertices_onNonEmptyGraph_returnsCorrectNumberOfVertices() {
        String msg = "numVertices() does not return correct" +
                "number of vertices";
        testGraph.addUndirectedEdge("X", "Y");
        testGraph.addUndirectedEdge("Z", "Y");
        assertEquals(3, testGraph.numVertices(), msg);
    }


    /**
     * Checks that numEdges() returns zero for an empty AdjacencyListGraph.
     */
    @Test
    void numEdges_onEmptyGraph_returnsZero() {
        String msg = "numEdges() should return zero for an empty graph";
        assertEquals(0, testGraph.numEdges(), msg);
    }


    /**
     * Checks that numEdges() returns the correct number of edges for a
     * non-empty AdjacencyListGraph.
     */
    @Test
    void numEdges_onNonEmptyUndirectedGraph_returnsCorrectNumberOfEdges() {
        String msg = "numEdges() does not return correct number of edges";
        for (int i = 0; i < EXPECTED_FROM.length; i++) {
            testGraph.addUndirectedEdge(
                    EXPECTED_FROM[i],
                    EXPECTED_TO[i],
                    WEIGHT
            );
        }
        assertEquals(12, testGraph.numEdges(), msg);
    }


    /**
     * Checks that isEmpty() returns true for an empty AdjacencyListGraph.
     */
    @Test
    void isEmpty_onEmptyGraph_returnsTrue() {
        String msg = "isEmpty() should return true for an empty graph";
        assertTrue(testGraph.isEmpty(), msg);
    }


    /**
     * Checks that isEmpty() returns false for a non-empty AdjacencyListGraph.
     */
    @Test
    void isEmpty_onNonEmptyGraph_returnsFalse() {
        String msg = "isEmpty() should return false for a non-empty graph";
        testGraph.addDirectedEdge("A", "B", WEIGHT);
        assertFalse(testGraph.isEmpty(), msg);
    }


    /**
     * Checks that equals() returns true for equal undirected
     * AdjacencyListGraphs
     */
    @Test
    void equals_onEqualUndirectedGraphs_returnsTrue() {
        String msg = "equals should return true for equal undirected graphs";
        AdjacencyListGraph<String> expectedGraph = new AdjacencyListGraph<>();
        for (int i = 0; i < EXPECTED_FROM.length; i++) {
            expectedGraph.addUndirectedEdge(
                    EXPECTED_FROM[i],
                    EXPECTED_TO[i],
                    WEIGHT
            );
            testGraph.addUndirectedEdge(
                    EXPECTED_FROM[i],
                    EXPECTED_TO[i],
                    WEIGHT
            );
        }
        assertEquals(expectedGraph, testGraph, msg);
    }


    /**
     * Checks that equals() returns true for equal directed
     * AdjacencyListGraphs.
     */
    @Test
    void equals_onEqualDirectedGraphs_returnsTrue() {
        String msg = "equals() should return true for equal directed graphs";
        AdjacencyListGraph<String> expectedGraph = new AdjacencyListGraph<>();
        for (int i = 0; i < EXPECTED_FROM.length; i++) {
            expectedGraph.addDirectedEdge(
                    EXPECTED_FROM[i],
                    EXPECTED_TO[i],
                    WEIGHT
            );
            testGraph.addDirectedEdge(
                    EXPECTED_FROM[i],
                    EXPECTED_TO[i],
                    WEIGHT
            );
        }
        assertEquals(expectedGraph, testGraph, msg);
    }


    /**
     * Checks that hashCode() returns same code for two equal undirected
     * AdjacencyListGraphs.
     */
    @Test
    void hashCode_onEqualUndirectedGraphs_returnsSameCode() {
        String msg = "hashCode() should return same code for equal graphs";
        AdjacencyListGraph<String> expectedGraph = new AdjacencyListGraph<>();
        expectedGraph.addUndirectedEdge("A", "B", 1.3);
        expectedGraph.addUndirectedEdge("B", "C", 9.2);
        testGraph.addUndirectedEdge("A", "B", 1.3);
        testGraph.addUndirectedEdge("B", "C", 9.2);
        assertEquals(expectedGraph.hashCode(), testGraph.hashCode(), msg);
    }


    /**
     * Checks that hashCode() returns the same code for two equal directed
     * AdjacencyListGraphs.
     */
    @Test
    void hashCode_onEqualDirectedGraphs_returnsSameCode() {
        String msg = "hashCode() should return same code for equal graphs";
        AdjacencyListGraph<String> expectedGraph = new AdjacencyListGraph<>();
        expectedGraph.addDirectedEdge("P", "Q", 3.7);
        expectedGraph.addDirectedEdge("Q", "R", 4.2);
        testGraph.addDirectedEdge("P", "Q", 3.7);
        testGraph.addDirectedEdge("Q", "R", 4.2);
        assertEquals(expectedGraph.hashCode(), testGraph.hashCode(), msg);
    }

}