package model.graph;

import org.junit.jupiter.api.BeforeEach;
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


    private final String[] expectedFrom = {
            "A", "B", "A", "D", "B", "C", "B", "F", "D", "E", "E", "F"
    };

    private final String[] expectedTo = {
            "B", "A", "D", "A", "C", "B", "F", "B", "E", "D", "F", "E"
    };

    private final String[] expectedVertices = {
            "A", "B", "C", "D", "E", "F"
    };

    private final AdjacencyListGraph<String> stringGraph;


    private static final double WEIGHT= 3.141594;


    public AdjacencyListGraphTest() {
        stringGraph = new AdjacencyListGraph<>();
        for (int i = 0; i < expectedFrom.length; i++) {
            stringGraph.addUndirectedEdge(expectedFrom[i], expectedTo[i], WEIGHT);
        }
    }

    @Test
    void addDirectedEdge_onDuplicateEdges_returnsCorrectEdges() {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        AdjacencyListGraph<String> expectedGraph = new AdjacencyListGraph<>();
        for (int i = 0; i < expectedFrom.length; i++) {
            graph.addDirectedEdge(expectedFrom[i], expectedTo[i]);
            expectedGraph.addUndirectedEdge(expectedFrom[i], expectedTo[i]);
        }
        assertEquals(expectedGraph.edges(), graph.edges());
    }


    @Test
    void addDirectedEdge_onEmptyGraph_addsDirectedEdge() {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        Set<Edge<Integer>> expected = new HashSet<>();
        expected.add(new Edge<>(1, 2, 0.0));
        graph.addDirectedEdge(1, 2, 0.0);
        assertEquals(expected, graph.edges());
    }

    @Test
    void addUndirectedEdge_onEmptyGraph_addsUndirectedEdge() {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>();
        Set<Edge<Integer>> expected = new HashSet<>();
        Edge<Integer> edge = new Edge<>(1, 2);
        expected.add(edge);
        expected.add(edge.reverse());
        graph.addUndirectedEdge(1, 2, 0.0);
        assertEquals(expected, graph.edges());
    }


    @Test
    void allEdges_afterManyAddDirectedEdge_returnsCorrectEdges() {
        Set<Edge<String>> expected = new HashSet<>();
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>();
        for (int i = 0; i < expectedFrom.length; i++) {
            graph.addDirectedEdge(expectedFrom[i], expectedTo[i], WEIGHT);
            expected.add(new Edge<>(expectedFrom[i], expectedTo[i], WEIGHT));
        }
        assertEquals(expected, graph.edges());
    }


    @Test
    void allEdges_afterManyAddUndirectedEdge_returnsCorrectEdges() {
        Set<Edge<String>> expected = new HashSet<>();
        for (int i = 0; i < expectedFrom.length; i++) {
            expected.add(new Edge<>(expectedFrom[i], expectedTo[i], WEIGHT));
        }
        assertEquals(expected, stringGraph.edges());
    }


    @Test
    void edgesFrom_onVertex_returnsCorrectOutgoingEdges() {
        Set<Edge<String>> expected = new HashSet<>();
        expected.add(new Edge<>("B", "A", WEIGHT));
        expected.add(new Edge<>("B", "F", WEIGHT));
        expected.add(new Edge<>("B", "C", WEIGHT));
        assertEquals(expected, stringGraph.edgesFrom("B"));
    }


    @Test
    void edgesFrom_givenNull_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> stringGraph.edgesFrom(null));
    }


    @Test
    void allVertices_onNonEmptyGraph_returnsCorrectVertices() {
        Set<String> expected = new HashSet<>();
        for (String vertex : expectedVertices) {
            expected.add(vertex);
        }
        assertEquals(expected, stringGraph.vertices());
    }
//
//
    @Test
    void numVertices() {
        assertEquals(6, stringGraph.numVertices());
    }


    @Test
    void numEdges() {
        assertEquals(12, stringGraph.numEdges());
    }

    @Test
    void equals() {
        AdjacencyListGraph<String> expectedGraph = new AdjacencyListGraph<>();
        for (int i = 0; i < expectedFrom.length; i++) {
            expectedGraph.addUndirectedEdge(expectedFrom[i], expectedTo[i], WEIGHT);
        }
        assertEquals(expectedGraph, stringGraph);
    }
}