package model.graph;


import java.util.*;

/**
 * An adjacency-list implementation of an undirected Graph.
 *
 * @author Reuben Keller
 */
public class AdjacencyListGraph<V>{

    /** The adjacency list representation of this Graph. **/
    private final Map<V, Set<Edge<V>>> adjacencyList;

    /** A list of all edges in this Graph. **/
    private final List<Edge<V>> edges;

    /** The total number of vertices in this Graph. **/
    private int numVertices;

    /** The total number of edges in this Graph. **/
    private int numEdges;


    /**
     * Constructs a new Graph.
     */
    public AdjacencyListGraph() {
        adjacencyList = new HashMap<>();
        edges = new ArrayList<>();
        numVertices = 0;
        numEdges = 0;
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
        if (adjacencyList.get(from) == null) {
            HashSet<Edge<V>> set = new HashSet<>();
            set.add(edge);
            adjacencyList.put(from, set);
        } else {
            Set<Edge<V>> set = adjacencyList.get(from);
            set.add(edge);
            adjacencyList.put(from, set);
        }
        numEdges += 1;
        numVertices += 2;
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
        return Collections.unmodifiableSet(adjacencyList.keySet());
    }


    /**
     * Returns the total number of vertices in this Graph.
     *
     * @return The number of vertices in this Graph.
     */
    public int numVertices() {
        return numVertices;
    }


    /**
     * Returns the total number of edges in this Graph.
     *
     * @return The number of edges in this Graph.
     */
    public int numEdges() {
        return numEdges;
    }

}
