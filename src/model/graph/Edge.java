/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Raz Consta, Reuben Keller, Dustin Ray
 */

package model.graph;

import java.util.Objects;

/**
 * Implements a directed Edge (a, b) between two distinct vertices a and b. The
 * default weight of an Edge is 0.0.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class Edge<V> {

    /** The default weight of this Edge. */
    private static final double DEFAULT_WEIGHT = 0.0;

    /** The vertex this Edge is directed from. */
    private final V myFrom;

    /** The vertex this Edge is directed to. */
    private final V myTo;

    /** The assigned weight of this Edge. */
    private final double myWeight;


    /**
     * Constructs a directed Edge between the given vertices with default
     * weight 0.0.
     *
     * @param theFrom The vertex this Edge is directed from.
     * @param theTo The vertex this Edge is directed to.
     * @throws IllegalArgumentException if from == null or to == null, or if
     *     from and to are equal.
     */
    public Edge(final V theFrom, final V theTo) {
        this(theFrom, theTo, DEFAULT_WEIGHT);
    }


    /**
     * Constructs an edge with the given weight between the given vertices.
     *
     * @param theFrom The vertex this Edge is directed from.
     * @param theTo The vertex this Edge is directed to.
     * @param theWeight The weight of this Edge.
     * @throws IllegalArgumentException if from == null or to == null, or if
     *     from and to are equal.
     */
    public Edge(V theFrom, V theTo, double theWeight) {
        checkEdge(theFrom, theTo);
        myFrom = theFrom;
        myTo = theTo;
        myWeight = theWeight;
    }


    /**
     * Checks if the edge for the given vertices is valid.
     *
     * @param from The vertex the edge emanates from.
     * @param to The vertex the edge terminates on.
     * @throws IllegalArgumentException if from == null or to == null, or if
     *     from and to are equal.
     */
    private void checkEdge(final V from, final V to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("attempted to add an edge" +
                    "with a null vertex");
        }
        if (from.equals(to)) {
            throw new IllegalArgumentException("attempted to add a self-loop");
        }
    }


    /**
     * Returns the vertex that this Edge is directed from.
     *
     * @return The vertex this Edge is directed from.
     */
    public V from() {
        return myFrom;
    }


    /**
     * Returns the vertex this Edge is directed to.
     *
     * @return The vertex this Edge is directed to.
     */
    public V to() {
        return myTo;
    }


    /**
     * Returns the weight of this Edge.
     *
     * @return The weight of this Edge.
     */
    public double weight() {
        return myWeight;
    }


    /**
     * Returns the reverse of this Edge.
     *
     * @return The reverse of this Edge.
     */
    public Edge<V> reverse() {
        return new Edge<>(myTo, myFrom, myWeight);
    }


    /**
     * Returns the String representation of this Edge.
     *
     * @return The String representation of this Edge.
     */
    @Override
    public String toString() {
        return "(" + myFrom + ", " + myTo + ", " + myWeight + ")";
    }


    /**
     * Overrides the equals method to make equality between Edges dependent on
     * having the same weight and vertices.
     *
     * @param other The Object to check for equality with.
     * @return true if this Edge is equal to other and false otherwise.
     */
    @Override
    public boolean equals(final Object other) {
        boolean result = false;
        if ((other != null) && (other.getClass().equals(this.getClass()))) {
            Edge<V> o = (Edge<V>) other;
            result = (this.myWeight == o.myWeight
                    && myFrom.equals(o.myFrom)
                    && myTo.equals(o.myTo));
        }
        return result;
    }


    /**
     * Hashes this Edge based on its weight and vertices.
     *
     * @return The integer hashcode of this Edge.
     */
    @Override
    public int hashCode() {
        return Objects.hash(myFrom, myTo, myWeight);
    }

}