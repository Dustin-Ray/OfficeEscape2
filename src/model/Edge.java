package model;

import java.util.Objects;


/**
 * Implements a weighted Edge in a Graph.
 *
 * @author Reuben Keller
 */
public class Edge<T> {

    /** The vertex a in an edge (a, b). */
    private final T myFrom;

    /** The vertex b in an edge (a, b). */
    private final T myTo;

    /** The weight of this edge. */
    private final double myWeight;


    /**
     * Constructs an edge between the given vertices with default weight 0.0.
     *
     * @param theFrom The vertex a in an edge (a, b).
     * @param theTo The vertex b in an edge (a, b).
     */
    public Edge(final T theFrom, final T theTo) {
        this(theFrom, theTo, 0.0);
    }


    /**
     * Constructs an edge with the given weight between the given vertices.
     *
     * @param theFrom The vertex a in an edge (a, b).
     * @param theTo The vertex b in an edge (a, b).
     * @param theWeight The weight of the edge.
     */
    public Edge(T theFrom, T theTo, double theWeight) {
        myFrom = theFrom;
        myTo = theTo;
        myWeight = theWeight;
    }


    /**
     * Returns the vertex this edge is coming from.
     *
     * @return The vertex this edge is coming from.
     */
    public T from() {
        return myFrom;
    }


    /**
     * Returns the vertex this Edge is going to.
     *
     * @return The vertex this Edge is going to.
     */
    public T to() {
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
    public Edge<T> reverse() {
        return new Edge<>(myTo, myFrom, myWeight);
    }


    @Override
    public String toString() {
        return "(" + myFrom + ", " + myTo + ", " + myWeight + ")";
    }


    @Override
    public boolean equals(final Object other) {
        boolean result = false;
        if ((other != null) && (other.getClass().equals(this.getClass()))) {
            Edge<T> o = (Edge<T>) other;
            result = (this.myWeight == o.myWeight
                    && (Objects.equals(this.myFrom, o.myFrom))
                    && (Objects.equals(this.myTo, o.myTo)));
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(myFrom, myTo, myWeight);
    }

}