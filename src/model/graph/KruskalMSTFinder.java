package model.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

/**
 * Generates a minimum-spanning-tree (MST).
 *
 * @author Reuben Keller
 */
public class KruskalMSTFinder<V> {

    /** The Graph to find a Kruskal MST of. */
    private final AdjacencyListGraph<V> myGraph;


    /**
     * Constructs a Kruskal MST finder for the given graph.
     *
     * @param theGraph The Graph to find a Kruskal MST of.
     */
    public KruskalMSTFinder(final AdjacencyListGraph<V> theGraph) {
        myGraph = theGraph;
        findMST();
    }


    /**
     * Finds and returns a MST of the weighted graph.
     *
     * @return The MST of the weighted graph.
     */
    public HashSet<Edge<V>> findMST() {
        // sort edges in the graph by weight in ascending order
        List<Edge<V>> edges = new ArrayList<>(myGraph.allEdges());
        edges.sort(Comparator.comparingDouble(Edge::weight));
        UnionFindDisjointSet<V> disjointSets = new UnionFindDisjointSet<>();

        // start with each vertex as a set
        int numVertices = 0;
        for (V vertex : myGraph.allVertices()) {
            disjointSets.makeSet(vertex);
            numVertices += 1;
        }

        HashSet<Edge<V>> mst = new HashSet<>();
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
                disjointSets.union(from, to);
            }
        }
        return mst;
    }

}
