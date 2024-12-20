package application;

import java.util.*;

class Flooding {
    private Set<Node> nodes;

    Flooding() {
        nodes = new HashSet<>();
    }

    void addEdge(Node source, Node destination, double weight) {
        nodes.add(source);
        nodes.add(destination);
        source.edges.add(new Edge(source, destination, weight));
    }

    String floodingAlgorithm(Node start) {
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();

        queue.add(start);
        visited.add(start);

        StringBuilder result = new StringBuilder("Flooding order: ");

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            result.append(current.name).append(" -> ");

            for (Edge edge : current.edges) {
                Node neighbor = edge.destination;
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        if (result.length() > 17) {
            result.setLength(result.length() - 4); // Remove trailing arrow
        }
        return result.toString();
    }
}
