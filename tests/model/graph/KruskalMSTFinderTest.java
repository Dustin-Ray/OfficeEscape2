package model.graph;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Implements unit tests for KruskalMSTFinder.
 *
 * @author Reuben Keller
 * @version Summer 2021
 */
public class KruskalMSTFinderTest {

    private final AdjacencyListGraph<Integer> graph;

    private final KruskalMSTFinder<Integer> mstFinder;

    private final Set<Edge<Integer>> mst;

    private final Map<Integer, List<Integer>> vertexMap;


    public KruskalMSTFinderTest() {
        graph = new AdjacencyListGraph<>();
        int[] graphFrom = {1, 1, 2, 2, 3, 4, 5, 4, 7, 5, 8, 6};
        int[] graphTo = {2, 4, 3, 5, 6, 5, 6, 7, 8, 8, 9, 9};
        double[] graphWeights = {2.0, 2.0, 3.0, 2.0, 4.0, 4.0, 1.0, 3.0, 1.0, 2.0, 3.0, 5.0};
        for (int i = 0; i < graphFrom.length; i++) {
            graph.addDirectedEdge(graphFrom[i], graphTo[i], graphWeights[i]);
        }
        mstFinder = new KruskalMSTFinder<>(graph);
        mst = new HashSet<>();
        vertexMap = new HashMap<>();
        int[] mstFrom = {2, 8, 1, 2, 5, 5, 7, 1};
        int[] mstTo = {3, 9, 4, 5, 8, 6, 8, 2};
        double[] mstWeights = {3.0, 3.0, 2.0, 2.0, 2.0, 1.0, 1.0, 2.0};
        for (int i = 0; i < mstFrom.length; i++) {
            int from = mstFrom[i];
            int to = mstTo[i];
            double weight = mstWeights[i];
            mst.add(new Edge<>(from, to, weight));
            vertexMap.computeIfAbsent(from, k -> new ArrayList<>());
            vertexMap.get(from).add(to);
            Collections.sort(vertexMap.get(from));
            vertexMap.computeIfAbsent(to, k -> new ArrayList<>());
            vertexMap.get(to).add(from);
            Collections.sort(vertexMap.get(to));
        }
    }


    @Test
    void getMST() {
        assertEquals(mst, mstFinder.getMST());
    }


    @Test
    void getVertexMap() {
        Map<Integer, List<Integer>> actual = mstFinder.getVertexMap();
        for (Integer key : actual.keySet()) {
            Collections.sort(actual.get(key));
        }
        assertEquals(vertexMap, mstFinder.getVertexMap());
    }
}