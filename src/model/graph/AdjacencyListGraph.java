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
 * An adjacency-list implementation of a graph. Each vertex u in the
 * adjacency-list is mapped to a set of edges (u, v), where each v shares an
 * edge with u. Can be used to build an undirected or directed and weighted or
 * unweighted graph. All edges have a default weight of 0.0 (for an unweighted
 * graph) unless otherwise assigned.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class AdjacencyListGraph<V> implements Graph<V> {

    /** The default value weight for edges in this AdjacencyListGraph. */
    private static final double DEFAULT_WEIGHT = 0.0;

    /** An adjacency-list representation of this AdjacencyListGraph. */
    private final Map<V, Set<Edge<V>>> adjacencyList;

    /** The set of edges in this AdjacencyListGraph. */
    private final Set<Edge<V>> edges;

    /** The list of all vertices in this AdjacencyListGraph. */
    private final Set<V> vertices;


    /**
     * Constructs a new AdjacencyListGraph.
     */
    public AdjacencyListGraph() {
        adjacencyList = new HashMap<>();
        edges = new HashSet<>();
        vertices = new HashSet<>();
    }


    /**
     * Adds a directed edge between the given vertices with default weight 0.0.
     *
     * @param from The vertex a in directed edge (a, b).
     * @param to The vertex b in directed edge (a, b).
     */
    public void addDirectedEdge(final V from, final V to) {
        addDirectedEdge(from, to, DEFAULT_WEIGHT);
    }


    /**
     * Adds a directed edge between the given vertices with the given weight.
     *
     * @param from The vertex a in directed edge (a, b)
     * @param to The vertex b in directed edge (a, b)
     * @param weight The weight of the edge.
     * @throws IllegalArgumentException if from == null or to == null, or if
     *     from and to are not equal.
     */
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


    /**
     * Adds an undirected edge between the given vertices with default weight
     * 0.0.
     *
     * @param from The vertex a in directed edge (a, b).
     * @param to The vertex b in directed edge (a, b).
     */
    public void addUndirectedEdge(final V from, final V to) {
        addUndirectedEdge(from, to, DEFAULT_WEIGHT);
    }


    /**
     * Adds an undirected edge between the given vertices with the given weight.
     *
     * @param from The vertex a in undirected edge {a, b}.
     * @param to The vertex b in undirected edge {a, b}.
     */
    public void addUndirectedEdge(final V from, final V to, final double weight) {
        addDirectedEdge(from, to, weight);
        addDirectedEdge(to, from, weight);
    }


    /**
     * Returns an unmodifiable set of the edges emanating from a given vertex.
     *
     * @param vertex The vertex to get the outgoing edges of.
     * @return The outgoing edges of vertex.
     * @throws IllegalArgumentException if vertex == null.
     */
    public Set<Edge<V>> edgesFrom(final V vertex) {
        if (vertex == null) {
            throw new IllegalArgumentException();
        }
        return Collections.unmodifiableSet(adjacencyList.get(vertex));
    }


    /**
     * Returns an unmodifiable set of all edges in this AdjacencyListGraph.
     *
     * @return An unmodifiable set of all edges in this AdjacencyListGraph.
     */
    public Set<Edge<V>> edges() {
        return Collections.unmodifiableSet(edges);
    }


    /**
     * Returns an unmodifiable set of all vertices in this AdjacencyListGraph.
     *
     * @return An unmodifiable set of all vertices in this AdjacencyListGraph.
     */
    public Set<V> vertices() {
        return Collections.unmodifiableSet(vertices);
    }


    /**
     * Returns the number of vertices in this AdjacencyListGraph.
     *
     * @return The number of vertices in this AdjacencyListGraph.
     */
    public int numVertices() {
        return vertices.size();
    }


    /**
     * Returns the number of edges in this AdjacencyListGraph.
     *
     * @return The number of edges in this AdjacencyListGraph.
     */
    public int numEdges() {
        return edges.size();
    }


    /**
     * Returns true if the number of Edges in this AdjacencyListGraph is zero
     * and false otherwise.
     *
     * @return true if edges.size() == 0 and false otherwise
     */
    public boolean isEmpty() {
        return edges.size() == 0;
    }


    /**
     * Overrides the equals method to make equality dependent on having the
     * same adjacency-list, vertices, and edges.
     *
     * @param other The object to check for equality with.
     * @return true if this AdjacencyListGraph is equal to other, and false
     *     otherwise.
     */
    @Override
    public boolean equals(final Object other) {
        boolean result = false;
        if ((other != null) && (other.getClass().equals(this.getClass()))) {
            @SuppressWarnings("unchecked")
            AdjacencyListGraph<V> o = (AdjacencyListGraph<V>) other;
            result = (adjacencyList.equals(o.adjacencyList)
                    && vertices.equals(o.vertices)
                    && edges.equals(o.edges));
        }
        return result;
    }


    /**
     * Hashes this AdjacencyListGraph based on its adjacency-list, set of
     * vertices, and set of edges.
     *
     * @return The integer hashed value of this AdjacencyListGraph.
     */
    @Override
    public int hashCode() {
        return Objects.hash(adjacencyList, vertices, edges);
    }

}

