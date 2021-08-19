/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Implements unit tests for Edge.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class EdgeTest {

    /** A default value for the vertex a in edge (a, b). */
    private static final int FROM = 5;

    /** A default value for the vertex b in edge (a, b). */
    private static final int TO = 7;

    /** A default test weight for an edge. */
    private static final double WEIGHT = 3.5;

    /** An unweighted edge (with default weight 0.0). */
    private Edge<Integer> unweightedEdge;

    /** A weighted edge. */
    private Edge<Integer> weightedEdge;


    /**
     * Constructs weighted and unweighted Edges to use in testing.
     */
    public EdgeTest() {
        unweightedEdge = new Edge<>(FROM, TO);
        weightedEdge = new Edge<>(FROM, TO, WEIGHT);
    }


    /**
     * Checks that the unweighted Edge constructor throws
     * IllegalArgumentException when given a null vertex.
     */
    @Test
    void unweightedConstructor_givenNullVertex_throwsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Edge<>(null, TO));
        assertThrows(IllegalArgumentException.class,
                () -> new Edge<>(FROM, null));
    }


    /**
     * Checks that the unweighted Edge constructor throws
     * IllegalArgumentException when attempting to construct an Edge with a
     * self-loop.
     */
    @Test
    void unweightedConstructor_givenRepeatedVertex_throwsException() {
        String msg = "cannot construct an Edge that creates a self-loop";
        assertThrows(IllegalArgumentException.class,
                () -> new Edge<>(FROM, FROM),
                msg);
    }


    /**
     * Checks that the weighted Edge constructor throws
     * IllegalArgumentException when given a null vertex.
     */
    @Test
    void weightedConstructor_givenNullVertex_throwsException() {
        String msg = "cannot construct an Edge with a null vertex";
        assertThrows(IllegalArgumentException.class,
                () -> new Edge<>(null, TO, WEIGHT),
                msg);
        assertThrows(IllegalArgumentException.class,
                () -> new Edge<>(FROM, null, WEIGHT),
                msg);
    }


    /**
     * Checks that the weighted Edge constructor throws
     * IllegalArgumentException when attempting to construct an Edge with a
     * self-loop.
     */
    @Test
    void weightedConstructor_givenRepeatedVertex_throwsException() {
        String msg = "cannot construct an Edge that creates a self-loop";
        assertThrows(IllegalArgumentException.class,
                () -> new Edge<>(TO, TO),
                msg);
    }


    /**
     * Checks whether method from() returns vertex a for Edge (a, b).
     */
    @Test
    void from_onEdge_returnsCorrectFrom() {
        String msg = "method from() does not return vertex a for Edge (a, b)";
        assertEquals(FROM, unweightedEdge.from(), msg);
        assertEquals(FROM, weightedEdge.from(), msg);
    }


    /**
     * Checks whether method to() returns vertex b for Edge (a, b).
     */
    @Test
    void to_onEdge_returnsCorrectTo() {
        String msg = "method to() does not return vertex b for Edge (a, b)";
        assertEquals(TO, unweightedEdge.to(), msg);
        assertEquals(TO, weightedEdge.to(), msg);
    }


    /**
     * Checks whether method weight() returns default weight 0.0 for an Edge
     * with unassigned weight.
     */
    @Test
    void weight_onUnweightedEdge_returnsCorrect() {
        String msg = "method weight() on unweighted edge does not return 0.0";
        assertEquals(0.0, unweightedEdge.weight(), msg);
    }


    /**
     * Checks whether method weight() returns the correct weight for an Edge
     * with assigned weight.
     */
    @Test
    void weight_onWeightedEdge_returnsCorrect() {
        String msg = "method weight() on weighted edge does not return" +
                " assigned weight";
        assertEquals(WEIGHT, weightedEdge.weight(), msg);
    }


    /**
     * Checks whether method reverse() correctly assigns vertex a in Edge
     * (a, b).
     */
    @Test
    void from_afterReverseOnEdge_returnsCorrect() {
        String msg = "method from() after reverse() returns incorrect vertex";
        unweightedEdge = unweightedEdge.reverse();
        weightedEdge = weightedEdge.reverse();
        assertEquals(TO, unweightedEdge.from(), msg);
        assertEquals(TO, weightedEdge.from(), msg);
    }


    /**
     * Checks whether method reverse() correctly assigns vertex b in Edge
     * (a, b).
     */
    @Test
    void to_afterReverseOnEdge_returnsCorrect() {
        String msg = "method to() after reverse() returns incorrect vertex";
        unweightedEdge = unweightedEdge.reverse();
        weightedEdge = weightedEdge.reverse();
        assertEquals(FROM, unweightedEdge.to(), msg);
        assertEquals(FROM, weightedEdge.to(), msg);
    }


    /**
     * Checks whether method toString() returns correct String for unweighted
     * Edge.
     */
    @Test
    void toString_onUnweightedEdge_returnsCorrect() {
        String msg = "toString on unweighted Edge returns incorrect String";
        String expected = "(" + FROM + ", " + TO + ", " + 0.0 + ")";
        assertEquals(expected, unweightedEdge.toString(), msg);
    }


    /**
     * Checks whether method toString() returns correct String for weighted
     * Edge.
     */
    @Test
    void toString_onWeightedEdge_returnsCorrect() {
        String msg = "toString on weighted Edge returns correct String";
        String expected = "(" + FROM + ", " + TO + ", " + WEIGHT + ")";
        assertEquals(expected, weightedEdge.toString(), msg);
    }


    /**
     * Checks whether equals() returns true for equal unweighted Edges.
     */
    @Test
    void equals_onEqualWeightedEdges_returnsTrue() {
        String msg = "equals() on weighted equal weighted Edges returns false";
        assertEquals(weightedEdge, new Edge<>(FROM, TO, WEIGHT), msg);
    }


    /**
     * Checks whether equals() returns true for equal weighted Edges.
     */
    @Test
    void equals_onEqualUnweightedEdges_returnsTrue() {
        String msg = "equals() on equal unweighted Edges returns false";
        assertEquals(unweightedEdge, new Edge<>(FROM, TO));
    }


    /**
     * Checks whether hashCode() returns the same integer code for equal
     * weighted Edges.
     */
    @Test
    void hashCode_onEqualWeightedEdges_returnsSameCode() {
        String msg = "hashCode() on equal weighted edges returns different code";
        assertEquals(weightedEdge.hashCode(),
                new Edge<>(FROM, TO, WEIGHT).hashCode(), msg);
    }


    /**
     * Checks whether hashCode() returns same integer code for equal
     * weighted Edges.
     */
    @Test
    void hashCode_onEqualUnweightedEdges_returnsSameCode() {
        String msg = "hashCode() on equal unweighted edges returns " +
                "different code";
        assertEquals(unweightedEdge.hashCode(),
                new Edge<>(FROM, TO).hashCode(), msg);
    }

}