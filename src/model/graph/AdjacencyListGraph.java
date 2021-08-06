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
 * An adjacency-list implementation of an undirected Graph.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class AdjacencyListGraph<V>{

    /** The adjacency list representation of this Graph. **/
    private final Map<V, Set<Edge<V>>> adjacencyList;

    /** A list of all edges in this Graph. **/
    private final List<Edge<V>> edges;

    /** A lit of all vertices in this Graph. **/
    private final Set<V> vertices;


    /**
     * Constructs a new AdjacencyListGraph.
     */
    public AdjacencyListGraph() {
        adjacencyList = new HashMap<>();
        edges = new ArrayList<>();
        vertices = new HashSet<>();
    }


    /**
     * Adds the edge (a, b) between two vertices a and b.
     *
     * @param from The vertex a in edge (a, b).
     * @param to The vertex b in edge (a, b).
     */
    public void addEdge(final V from, final V to) {
        addEdge(from, to, 0.0);
    }


    /**
     * Adds a weighted edge (a, b) between two vertices a and b.
     *
     * @param from The vertex a in edge (a, b).
     * @param to The vertex b in edge (a, b).
     */
    public void addEdge(final V from, final V to, final double weight) {
        Edge<V> edge = new Edge<>(from, to, weight);
        edges.add(edge);
        vertices.add(to);
        vertices.add(from);
        if (adjacencyList.get(from) == null && adjacencyList.get(to) == null) {
            HashSet<Edge<V>> set1 = new HashSet<>();
            set1.add(edge);
            adjacencyList.put(from, set1);

            HashSet<Edge<V>> set2 = new HashSet<>();
            set2.add(edge.reverse());
            adjacencyList.put(to, set2);

        } else if (adjacencyList.get(from) != null && adjacencyList.get(to) != null) {
            Set<Edge<V>> set1 = adjacencyList.get(from);
            set1.add(edge);
            adjacencyList.put(from, set1);

            Set<Edge<V>> set2 = adjacencyList.get(to);
            set2.add(edge);
            adjacencyList.put(to, set2);
        }
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
    public List<Edge<V>> allEdges() {
        return Collections.unmodifiableList(edges);
    }


    /**
     * Returns a set of all vertices in this Graph.
     *
     * @return A set of all vertices in this Graph.
     */
    public Set<V> allVertices() {
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

}

