package model.graph;

import java.util.Set;


/**
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public interface Graph<V> {

    /**
     * Adds an undirected edge between the given vertices with default weight
     * 0.0.
     *
     * @param from The vertex a in directed edge (a, b).
     * @param to The vertex b in directed edge (a, b).
     */
    void addUndirectedEdge(final V from, final V to);


    /**
     * Adds a directed edge between the given vertices with default weight 0.0.
     *
     * @param from The vertex a in directed edge (a, b).
     * @param to The vertex b in directed edge (a, b).
     */
    void addDirectedEdge(final V from, final V to);


    /**
     * Returns an unmodifiable set of the edges emanating from a given vertex.
     *
     * @param vertex The vertex to get the outgoing edges of.
     * @return The outgoing edges of vertex.
     * @throws IllegalArgumentException if vertex == null.
     */
    Set<Edge<V>> edgesFrom(final V vertex);



    /**
     * Returns an unmodifiable set of all edges in this AdjacencyListGraph.
     *
     * @return An unmodifiable set of all edges in this AdjacencyListGraph.
     */
    Set<Edge<V>> edges();


    /**
     * Returns an unmodifiable set of all vertices in this AdjacencyListGraph.
     *
     * @return An unmodifiable set of all vertices in this AdjacencyListGraph.
     */
    Set<V> vertices();


    /**
     * Returns the number of vertices in this AdjacencyListGraph.
     *
     * @return The number of vertices in this AdjacencyListGraph.
     */
    int numVertices();


    /**
     * Returns the number of edges in this AdjacencyListGraph.
     *
     * @return The number of edges in this AdjacencyListGraph.
     */
    int numEdges();

}
