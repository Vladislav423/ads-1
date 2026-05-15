package ads_1.assignment_4;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraSearch<V> extends Search<V> {
    private final Map<Vertex<V>, Double> distTo;

    public DijkstraSearch(WeightedGraph<V> graph, Vertex<V> source) {
        super(source);
        distTo = new HashMap<>();

        for (Vertex<V> v : graph.getVertices()) {
            distTo.put(v, Double.POSITIVE_INFINITY);
        }
        distTo.put(source, 0.0);

        dijkstra(source);
    }

    private void dijkstra(Vertex<V> source) {
        PriorityQueue<Vertex<V>> pq = new PriorityQueue<>(Comparator.comparingDouble(distTo::get));
        pq.add(source);

        while (!pq.isEmpty()) {
            Vertex<V> v = pq.poll();
            marked.add(v);

            for (Map.Entry<Vertex<V>, Double> entry : v.getAdjacentVertices().entrySet()) {
                Vertex<V> w = entry.getKey();
                double weight = entry.getValue();

                if (!marked.contains(w)) {
                    relax(v, w, weight, pq);
                }
            }
        }
    }

    private void relax(Vertex<V> v, Vertex<V> w, double weight, PriorityQueue<Vertex<V>> pq) {
        if (distTo.get(w) > distTo.get(v) + weight) {
            distTo.put(w, distTo.get(v) + weight);
            edgeTo.put(w, v);

            pq.remove(w);
            pq.add(w);
        }
    }

    public double getDistTo(Vertex<V> v) {
        return distTo.getOrDefault(v, Double.POSITIVE_INFINITY);
    }
}