package ads_1.assignment_4;

import java.util.HashMap;
import java.util.Map;

public class WeightedGraph<V> {
    private final Map<V, Vertex<V>> vertices;

    public WeightedGraph() {
        this.vertices = new HashMap<>();
    }

    public void addVertex(V data) {
        vertices.putIfAbsent(data, new Vertex<>(data));
    }

    public void addEdge(V source, V dest, double weight) {
        addVertex(source);
        addVertex(dest);

        Vertex<V> vSource = vertices.get(source);
        Vertex<V> vDest = vertices.get(dest);

        vSource.addAdjacentVertex(vDest, weight);
    }

    public Vertex<V> getVertex(V data) {
        return vertices.get(data);
    }

    public Iterable<Vertex<V>> getVertices() {
        return vertices.values();
    }
}