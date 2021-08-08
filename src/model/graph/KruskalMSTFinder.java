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
 * Generates the minimum-spanning-tree (MST) of a weighted graph.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class KruskalMSTFinder<V> {

    /** The Graph to find a Kruskal MST of. */
    private final AdjacencyListGraph<V> myGraph;

    /** The Kruskal MST generated. */
    private final Set<Edge<V>> mst;

    /** A map where each vertex is associated with its connected vertices. */
    private final Map<V, List<V>> vertexMap;


    /**
     * Constructs a Kruskal MST finder for the given graph.
     *
     * @param theGraph The Graph to find a Kruskal MST of.
     */
    public KruskalMSTFinder(final AdjacencyListGraph<V> theGraph) {
        myGraph = theGraph;
        mst = new HashSet<>();
        vertexMap = new HashMap<>();
        findMST();
    }


    /**
     * Finds and returns a MST of the weighted graph.
     */
    private void findMST() {
        // sort edges in the graph by weight in ascending order
        List<Edge<V>> edges = new ArrayList<>(myGraph.edges());
        edges.sort(Comparator.comparingDouble(Edge::weight));
        UnionFindDisjointSet<V> disjointSets = new UnionFindDisjointSet<>();

        // start with each vertex as a set
        int numVertices = 0;
        for (V vertex : myGraph.vertices()) {
            disjointSets.makeSet(vertex);
            numVertices += 1;
        }

        // iterate through ordered edges to find a potential MST
        for (Edge<V> edge : edges) {
            // stop iterating early if |E| = |V| - 1
            if (mst.size() == numVertices - 1) {
                break;
            }
            // get the two vertices connected by this edge
            V from = edge.from();
            V to = edge.to();
            // get representative ID for each vertex
            int fromMST = disjointSets.findSet(from);
            int toMST = disjointSets.findSet(to);
            if (fromMST != toMST) {
                // IDs don't match (i.e., the vertices are from different sets)
                mst.add(edge);
                vertexMap.computeIfAbsent(from, k -> new ArrayList<>());
                vertexMap.get(from).add(to);
                vertexMap.computeIfAbsent(to, k -> new ArrayList<>());
                vertexMap.get(to).add(from);
                disjointSets.union(from, to);
            }
        }
    }


    /**
     * Returns the set of Edges in the Kruskal MST.
     *
     * @return The set of edges in the Kruskal MST.
     */
    public Set<Edge<V>> getMST() {
        return mst;
    }


    /**
     * Returns a mapping of each vertex to its connected vertices.
     *
     * @return A mapping of each vertex to its connected vertices.
     */
    public Map<V, List<V>> getVertexMap () {
        return vertexMap;
    }

}
