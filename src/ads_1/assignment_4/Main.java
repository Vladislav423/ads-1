package ads_1.assignment_4;

public class Main {
    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph<>();

        graph.addEdge("A", "B", 2.0);
        graph.addEdge("A", "C", 4.0);
        graph.addEdge("B", "C", 1.0);
        graph.addEdge("B", "D", 7.0);
        graph.addEdge("C", "E", 3.0);
        graph.addEdge("E", "D", 2.0);
        graph.addEdge("D", "F", 1.0);

        Vertex<String> startVertex = graph.getVertex("A");
        Vertex<String> targetVertex = graph.getVertex("D");

        System.out.println("=== Breadth-First Search (BFS) ===");
        BreadthFirstSearch<String> bfs = new BreadthFirstSearch<>(graph, startVertex);
        if (bfs.hasPathTo(targetVertex)) {
            System.out.print("Path from A to D: ");
            bfs.pathTo(targetVertex).forEach(v -> System.out.print(v.getData() + " "));
            System.out.println();
        }

        System.out.println("\n=== Dijkstra Search ===");
        DijkstraSearch<String> dijkstra = new DijkstraSearch<>(graph, startVertex);
        if (dijkstra.hasPathTo(targetVertex)) {
            System.out.print("Shortest path from A to D: ");
            dijkstra.pathTo(targetVertex).forEach(v -> System.out.print(v.getData() + " "));
            System.out.println("\nTotal weight (Distance): " + dijkstra.getDistTo(targetVertex));
        }
    }
}