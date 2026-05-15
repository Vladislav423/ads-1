package ads_1.assignment_4;

import java.util.LinkedList;
import java.util.Queue;

public class BreadthFirstSearch<V> extends Search<V> {

    public BreadthFirstSearch(WeightedGraph<V> graph, Vertex<V> source) {
        super(source);
        bfs(source);
    }

    private void bfs(Vertex<V> source) {
        Queue<Vertex<V>> queue = new LinkedList<>();
        marked.add(source);
        queue.add(source);

        while (!queue.isEmpty()) {
            Vertex<V> v = queue.poll();

            for (Vertex<V> w : v.getAdjacentVertices().keySet()) {
                if (!marked.contains(w)) {
                    edgeTo.put(w, v);
                    marked.add(w);
                    queue.add(w);
                }
            }
        }
    }
}