package model.graph;

import java.util.*;

public class Dijkstra<V> {


    public Dijkstra() {
    }

    public Map<V, Edge<V>> shortestPathTree(final Graph<V> graph, final V source, final V target) {
        Map<V, Edge<V>> edgeToV = new HashMap<>();
        Map<V, Double> distToV = new HashMap<>();
        MinHeapPQ<V> perimeter = new MinHeapPQ<>();
        distToV.put(source, 0.0);
        perimeter.offer(source, 0.0);
        while(!perimeter.isEmpty()) {
            V from = perimeter.poll();
            if (from.equals(target)) {
                // terminate as soon as target is found
                break;
            }
            // relax weights
            for (Edge<V> edge : graph.edgesFrom(from)) {
                V to = edge.to();
                // initialize vertices as they appear to save space
                if (!distToV.containsKey(to)) {
                    distToV.put(to, Double.POSITIVE_INFINITY);
                }
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
        }
        return edgeToV;
    }

    public List<Edge<V>> extractShortestPath(Map<V, Edge<V>> spt, V source, V target) {
        Edge<V> edge  = spt.get(target);
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
