package application.algorithm;

import java.util.*;

import application.model.*;

public class BellmanFord extends GraphAlgorithm {

    public BellmanFord() {
        super();  // Gọi constructor của lớp cha
    }

    public  BellmanFord (boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
    }

    public Stack<String> getNodePath(application.model.Node source, application.model.Node target) {
        Stack<String> path = new Stack<>();
        Map<Node, Double> distance = new HashMap<>();
        Map<Node, Node> predecessor = new HashMap<>();

        for (Node node : nodes) {
            distance.put(node, Double.POSITIVE_INFINITY);
        }
        distance.put(source, 0.0);

        // Relax tất cả các cạnh |V| - 1 lần
        for (int i = 0; i < nodes.size() - 1; i++) {
            for (Node node : nodes) {
                for (Edge edge : node.getEdge()) {
                    if (distance.get(node) + edge.getWeight() < distance.get(edge.getDestination())) {
                        distance.put(edge.getDestination(), distance.get(node) + edge.getWeight());
                        predecessor.put(edge.getDestination(), node);
                    }
                }
            }
        }

        for (Node node : nodes) {
            for (Edge edge : node.getEdge()) {
                if (distance.get(node) + edge.getWeight() < distance.get(edge.getDestination())) {
                    return null;
                }
            }
        }

        Node current = target;
        while (current != null) {
            path.push(current.getName());
            current = predecessor.get(current);
        }

        return path;
    }
}