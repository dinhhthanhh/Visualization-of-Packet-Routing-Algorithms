package application;

import java.util.*;

class BellmanFord {
    private Set<Node> nodes;

    BellmanFord() {
        nodes = new HashSet<>();
    }

    void addEdge(Node source, Node destination, double weight) {
        nodes.add(source);
        nodes.add(destination);
        source.edges.add(new Edge(source, destination, weight));
    }

    String findShortestPath(Node start, Node end) {
        Map<Node, Double> distances = new HashMap<>();
        Map<Node, Node> predecessors = new HashMap<>();

        for (Node node : nodes) {
            distances.put(node, Double.POSITIVE_INFINITY);
        }
        distances.put(start, 0.0);

        for (int i = 0; i < nodes.size() - 1; i++) {
            for (Node node : nodes) {
                for (Edge edge : node.edges) {
                    if (distances.get(node) + edge.weight < distances.get(edge.destination)) {
                        distances.put(edge.destination, distances.get(node) + edge.weight);
                        predecessors.put(edge.destination, node);
                    }
                }
            }
        }

        for (Node node : nodes) {
            for (Edge edge : node.edges) {
                if (distances.get(node) + edge.weight < distances.get(edge.destination)) {
                    return "Graph contains a negative weight cycle";
                }
            }
        }

        if (distances.get(end) == Double.POSITIVE_INFINITY) {
            return "No path exists between " + start.name + " and " + end.name;
        }

        StringBuilder path = new StringBuilder(end.name);
        Node currentNode = end;
        while (predecessors.containsKey(currentNode)) {
            currentNode = predecessors.get(currentNode);
            path.insert(0, currentNode.name + " -> ");
        }

        return "Path: " + path + "\nTotal cost: " + distances.get(end);
    }
}

