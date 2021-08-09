package model.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Implements unit tests for Edge.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class EdgeTest {

    private static final int FROM = 5;

    private static final int TO = 7;

    private static final double WEIGHT = 3.5;

    private Edge<Integer> edge;

    private final Edge<Integer> weightedEdge;


    public EdgeTest() {
        edge = new Edge<>(FROM, TO);
        weightedEdge = new Edge<>(FROM, TO, WEIGHT);
    }


    @Test
    void from_returnsCorrectFrom() {
        assertEquals(FROM, edge.from());
    }


    @Test
    void to() {
        assertEquals(TO, edge.to());
    }


    @Test
    void weight_onUnweightedEdge_returnsCorrect() {
        assertEquals(0.0, edge.weight());
    }


    @Test
    void weight_onWeightedEdge_returnsCorrect() {
        assertEquals(WEIGHT, weightedEdge.weight());
    }


    @Test
    void from_afterReverseOnEdge_returnsCorrect() {
        edge = edge.reverse();
        assertEquals(TO, edge.from());
    }


    @Test
    void to_afterReverseOnEdge_returnsCorrect() {
        edge = edge.reverse();
        assertEquals(FROM, edge.to());
    }


    @Test
    void toString_forUnweightedEdge_returnsCorrect() {
        String expected = "(" + FROM + ", " + TO + ", " + 0.0 + ")";
        assertEquals(expected, edge.toString());
    }


    @Test
    void toString_forWeightedEdge_returnsCorrect() {
        String expected = "(" + FROM + ", " + TO + ", " + WEIGHT + ")";
        assertEquals(expected, weightedEdge.toString());
    }

}