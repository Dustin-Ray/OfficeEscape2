package model.graph;

import java.util.*;

/**
 * Constructs a shortest path finder that finds the shortest path tree or
 * partial shortest path tree using Dijkstra's algorithm.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class DijkstraSPFinder<V> {

    /** A mapping of each vertex to its shortest path distance from source. */
    private final Map<V, Double> distToV;

    /** A mapping of each vertex to its predecessor edge in the shortest path. */
    private final Map<V, Edge<V>> edgeToV;

    /** A min-heap priority queue to order traversal by shortest path distance. */
    private final MinHeapPQ<V> perimeter;


    /**
     * Constructs a Dijkstra shortest path finder for the given graph and source
     * vertex.
     */
    public DijkstraSPFinder() {
        edgeToV = new HashMap<>();
        distToV = new HashMap<>();
        perimeter = new MinHeapPQ<>();
    }


    /**
     * Given a graph, a source vertex, and a target vertex, the method computes
     * the shortest path tree between the source and target or a partial
     * shortest path tree if the shortest path between the source and target is
     * found early.
     *
     * @param graph The Graph to get a partial shortest path tree of.
     * @param source The source vertex to begin Dijkstra's algorithm at.
     * @param target The target vertex to terminate Dijkstra's when found.
     * @return The shortest path tree or partial shortest path tree from source
     *     to target
     */
    public Map<V, Edge<V>> shortestPathTree(final Graph<V> graph,
                                            final V source,
                                            final V target) {
        distToV.put(source, 0.0);
        perimeter.offer(source, 0.0);
        while (!perimeter.isEmpty()) {
            V from = perimeter.poll();
            if (from.equals(target)) { // early termination
                break;
            }
            for (Edge<V> edge : graph.edgesFrom(from)) { // relax weights
                V to = edge.to();
                if (!distToV.containsKey(to)) {
                    // memory optimization: initialize vertices as they appear
                    distToV.put(to, Double.POSITIVE_INFINITY);
                }
                relaxEdge(edge, edgeToV, distToV, perimeter);
            }
        }
        return edgeToV;
    }


    /**
     * Updates the distance to vertex B in Edge (A, B) if the shortest path
     * distance to vertex A plus the weight of Edge (A, B) is less than the
     * shortest path distance estimate to vertex B.
     *
     * @param edge The Edge to relax.
     * @param edgeToV A mapping of each vertex to its predecessor vertex along
     *     the shortest path from a source vertex with distance 0.0.
     * @param distToV A mapping of each vertex to its shortest path distance
     *     from a source vertex with distance 0.0.
     * @param perimeter A min-heap priority queue of unvisited vertices.
     */
    public void relaxEdge(final Edge<V> edge,
                          final Map<V, Edge<V>> edgeToV,
                          final Map<V, Double> distToV,
                          final MinHeapPQ<V> perimeter) {
        V from = edge.from();
        V to = edge.to();
        double oldDist = distToV.get(to);
        double newDist = distToV.get(from) + edge.weight();
        if (newDist < oldDist) {
            edgeToV.put(to, edge);
            distToV.put(to, newDist);
            if (perimeter.contains(to)) {
                perimeter.changePriority(to, newDist);
            } else {
                perimeter.offer(to, newDist);
            }
        }
    }


    /**
     * Returns the shortest path (sequence of edges) from the given source to
     * the given target in the given shortest path tree.
     *
     * @param spt A shortest path tree.
     * @param source The source vertex in spt.
     * @param target The target vertex in spt.
     * @return The shortest path from source to target.
     */
    public List<Edge<V>> extractShortestPath(Map<V, Edge<V>> spt, V source,
                                             V target) {
        Edge<V> edge = spt.get(target);
        List<Edge<V>> list = new ArrayList<>();
        if (edge != null) {
            Stack<Edge<V>> stack = new Stack<>();
            stack.push(edge);
            while (!Objects.equals(edge.from(), source)) {
                edge = spt.get(edge.from());
                stack.push(edge);
            }
            while (!stack.isEmpty()) {
                list.add(stack.pop());
            }
        }
        return list;
    }

}
