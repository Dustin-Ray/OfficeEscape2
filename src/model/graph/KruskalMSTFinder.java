package model.graph;

import java.util.*;

/**
 * Generates a minimum-spanning-tree (MST).
 *
 * @author Reuben Keller
 */
public class KruskalMSTFinder<V> {

    /** The Graph to find a Kruskal MST of. */
    private final AdjacencyListGraph<V> myGraph;

    private final Set<Edge<V>> mst;

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
        List<Edge<V>> edges = new ArrayList<>(myGraph.allEdges());
        edges.sort(Comparator.comparingDouble(Edge::weight));
        UnionFindDisjointSet<V> disjointSets = new UnionFindDisjointSet<>();

        // start with each vertex as a set
        int numVertices = 0;
        for (V vertex : myGraph.allVertices()) {
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
                //
                vertexMap.computeIfAbsent(from, k -> new ArrayList<>());
                vertexMap.get(from).add(to);
                vertexMap.computeIfAbsent(to, k -> new ArrayList<>());
                vertexMap.get(to).add(from);
                //
                disjointSets.union(from, to);
            }
        }
    }

    public Set<Edge<V>> getMST() {
        return mst;
    }

    public Map<V, List<V>> getVertexMap () {
        return vertexMap;
    }

}
