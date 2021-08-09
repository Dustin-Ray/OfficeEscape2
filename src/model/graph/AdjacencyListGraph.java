/*
University of Washington, Tacoma
TCSS 360 Software Development and Quality Assurance Techniques

Instructor: Tom Capaul
Academic Quarter: Summer 2021
Assignment: Group Project
Team members: Dustin Ray, Raz Consta, Reuben Keller
 */

package model.graph;

import java.util.*;

/**
 * An adjacency-list implementation of a weighted Graph.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class AdjacencyListGraph<V>{

    /** The adjacency list representation of this Graph. **/
    private final Map<V, Set<Edge<V>>> adjacencyList;


    private final Set<Edge<V>> edges;

    /** A lit of all vertices in this Graph. **/
    private final Set<V> vertices;


    /**
     * Constructs a new AdjacencyListGraph.
     */
    public AdjacencyListGraph() {
        adjacencyList = new HashMap<>();
        edges = new HashSet<>();
        vertices = new HashSet<>();
    }

    public void addDirectedEdge(final V from, final V to) {
        addDirectedEdge(from, to, 0.0);
    }


    public void addDirectedEdge(final V from, final V to, final double weight) {
        Edge<V> edge = new Edge<>(from, to, weight);
        edges.add(edge);
        vertices.add(from);
        vertices.add(to);
        Set<Edge<V>> set;
        if (adjacencyList.get(from) == null) {
            set = new HashSet<>();
            set.add(edge);
            adjacencyList.put(from, set);
        }
        set = adjacencyList.get(from);
        set.add(edge);
        adjacencyList.put(from, set);
    }


    public void addUndirectedEdge(final V from, final V to) {
        addUndirectedEdge(from, to, 0.0);
    }

    /**
     * Adds a weighted undirected edge (a, b) between two vertices a and b.
     *
     * @param from The vertex a in edge (a, b).
     * @param to The vertex b in edge (a, b).
     */
    public void addUndirectedEdge(final V from, final V to, final double weight) {
        addDirectedEdge(from, to, weight);
        addDirectedEdge(to, from, weight);
    }


    /**
     * Returns the outgoing edges from a given vertex.
     *
     * @param vertex The vertex to get the outgoing edges of.
     * @return The outgoing edges of from
     */
    public Set<Edge<V>> edgesFrom(final V vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException();
        }
        return Collections.unmodifiableSet(adjacencyList.get(vertex));
    }


    /**
     * Returns a list of all edges in this Graph.
     *
     * @return A list of all edges in this Graph.
     */
    public Set<Edge<V>> edges() {
        return Collections.unmodifiableSet(edges);
    }


    /**
     * Returns a set of all vertices in this Graph.
     *
     * @return A set of all vertices in this Graph.
     */
    public Set<V> vertices() {
        return Collections.unmodifiableSet(vertices);
    }


    /**
     * Returns the total number of vertices in this Graph.
     *
     * @return The number of vertices in this Graph.
     */
    public int numVertices() {
        return vertices.size();
    }


    /**
     * Returns the total number of edges in this Graph.
     *
     * @return The number of edges in this Graph.
     */
    public int numEdges() {
        return edges.size();
    }


    @Override
    public boolean equals(final Object other) {
        boolean result = false;
        if ((other != null) && (other.getClass().equals(this.getClass()))) {
            AdjacencyListGraph<V> o = (AdjacencyListGraph<V>) other;
            result = (adjacencyList.equals(o.adjacencyList)
                    && vertices.equals(o.vertices)
                    && edges.equals(o.edges));
        }
        return result;
    }

}

