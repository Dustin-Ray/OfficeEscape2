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
 * Generates the minimum-spanning-tree (MST) of a weighted graph using
 * Kruskal's algorithm.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class KruskalMSTFinder<V> {

    /** The graph to find a Kruskal MST of. */
    private final Graph<V> myGraph;

    /** The Kruskal MST generated. */
    private final Set<Edge<V>> mst;

    /** A mapping of each vertex in the MST to its connected vertices. */
    private final Map<V, List<V>> vertexMap;

    /** The data structure maintaining the disjoint sets for the MST .*/
    private final UnionFindDisjointSet<V> disjointSets;

    /** The Edges in myGraph, sorted in non-decreasing order of weight. */
    private List<Edge<V>> edges;


    /**
     * Constructs a Kruskal MST finder for the given Graph.
     *
     * @param theGraph The Graph to find a Kruskal MST of.
     */
    public KruskalMSTFinder(final Graph<V> theGraph) {
        myGraph = theGraph;
        mst = new HashSet<>();
        vertexMap = new HashMap<>();
        disjointSets = new UnionFindDisjointSet<>();
        findMST();
    }


    /**
     * Sets up the list of Edges in non-decreasing order by weight.
     */
    private void sortEdgesByWeight() {
        edges = new ArrayList<>(myGraph.edges());
        edges.sort(Comparator.comparingDouble(Edge::weight));
    }


    /**
     * Creates a disjoint set for each vertex in the Graph.
     */
    private void makeDisjointSets() {
        for (V vertex : myGraph.vertices()) {
            disjointSets.makeSet(vertex);
        }
    }


    /**
     * Adds the given vertices to the vertex map if they're not already in it.
     *
     * @param from The vertex a in edge (a, b).
     * @param to The vertex b in edge (a, b).
     */
    private void addToVertexMap(final V from, final V to) {
        vertexMap.computeIfAbsent(from, k -> new ArrayList<>());
        vertexMap.get(from).add(to);
        vertexMap.computeIfAbsent(to, k -> new ArrayList<>());
        vertexMap.get(to).add(from);
    }


    /**
     * Uses Kruskal's algorithm to find an MST in a Graph. The method first
     * sorts a list of edges in the Graph to get them in non-decreasing order
     * by weight and makes a disjoint set for each vertex in the Graph. The
     * method then iterates through the ordered list of edges, finding the
     * disjoint set that each vertex in each Edge belongs to. If two vertices
     * belong to different disjoint sets, then their sets are unioned together
     * (since doing so will not create a cycle) and their edge is added to the
     * MST.
     */
    private void findMST() {
        sortEdgesByWeight();
        makeDisjointSets();
        // iterate through ordered edges to find a potential MST
        for (Edge<V> edge : edges) {
            if (mst.size() == myGraph.numVertices()- 1) {
                // stop iterating early if |E| = |V| - 1
                break;
            }
            V from = edge.from();
            V to = edge.to();
            int fromMST = disjointSets.findSet(from);
            int toMST = disjointSets.findSet(to);
            if (fromMST != toMST) {
                mst.add(edge);
                disjointSets.union(from, to);
                addToVertexMap(from, to);
            }
        }
    }


    /**
     * Returns the set of Edges in the Kruskal MST.
     *
     * @return The set of Edges in the Kruskal MST.
     */
    public Set<Edge<V>> getMST() {
        return mst;
    }


    /**
     * Returns a mapping of each vertex in the MST to its connected vertices.
     *
     * @return A mapping of each vertex in the MST to its connected vertices.
     */
    public Map<V, List<V>> getVertexMap () {
        return vertexMap;
    }

}
