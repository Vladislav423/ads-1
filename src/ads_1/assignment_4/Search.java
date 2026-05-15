package ads_1.assignment_4;

import java.util.*;

public abstract class Search<V> {
    protected final Vertex<V> source;
    protected final Set<Vertex<V>> marked;
    protected final Map<Vertex<V>, Vertex<V>> edgeTo;

    public Search(Vertex<V> source) {
        this.source = source;
        this.marked = new HashSet<>();
        this.edgeTo = new HashMap<>();
    }

    public boolean hasPathTo(Vertex<V> v) {
        return marked.contains(v);
    }

    public Iterable<Vertex<V>> pathTo(Vertex<V> v) {
        if (!hasPathTo(v)) return null;
        LinkedList<Vertex<V>> path = new LinkedList<>();
        for (Vertex<V> x = v; !x.equals(source); x = edgeTo.get(x)) {
            path.push(x);
        }
        path.push(source);
        return path;
    }
}