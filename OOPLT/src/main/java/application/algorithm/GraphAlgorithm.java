package application.algorithm;

import application.model.Node;
import application.model.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

abstract class GraphAlgorithm {
    protected boolean directed;
    protected Set<Node> nodes;

    // Constructor chung
    public GraphAlgorithm() {
        this.nodes = new HashSet<>();
    }

    // Phương thức chung để thêm node
    public void addNode(Node... n) {
        // We're using a var arg method so we don't have to call
        // addNode repeatedly
        nodes.addAll(Arrays.asList(n));
    }

    public void addEdge(Node source, Node destination, double weight) {

        nodes.add(source);
        nodes.add(destination);

        // We're using addEdgeHelper to make sure we don't have duplicate edges
        addEdgeHelper(source, destination, weight);

        if (!directed && source != destination) {
            addEdgeHelper(destination, source, weight);
        }
    }
    private void addEdgeHelper(Node a, Node b, double weight) {

        for (Edge edge : a.edges) {
            if (edge.source == a && edge.destination == b) {
                // Update the value in case it's a different one now
                edge.weight = weight;
                return;
            }
        }
        // If it hasn't been added already
        a.edges.add(new Edge(a, b, weight));
    }
    public void ModifyEdgeWeight(Node a, Node b, double weight) {

        for (Edge edge : a.edges) {
            if (edge.source == a && edge.destination == b) {
                // Update the value
                edge.weight = weight;
                return;
            }
        }
    }
    public void copyEdge(ArrayList<Edge> edges){
        for(Node node : nodes){
            edges.addAll(node.edges);
        }
    }

    // Phương thức chung để xóa một edge
//    public boolean DeleteEd(Node a, Node b) {
//        for (Edge edge : a.edges) {
//            if (edge.source == a && edge.destination == b) {
//                a.edges.remove(edge);
//                return true;
//            }
//        }
//        return false;
//    }
//
//    // Phương thức chung để xóa node và các cạnh liên quan
//    public void DeleteNo(Node from) {
//        for (Node node : nodes) {
//            for (Edge edge : node.edges) {
//                if (edge.source == from || edge.destination == from) {
//                    node.edges.remove(edge);
//                }
//            }
//        }
//        nodes.remove(from);
//    }
    public boolean DeleteEd(Node a, Node b) {
        for (Edge edge : a.edges) {
            if (edge.source == a && edge.destination == b) {
                // Update the value in case it's a different one now
                a.edges.remove(edge);
                return true;
            }
        }
        return false;
    }
    public void DeleteNo(Node from){
        for(Node node : nodes){
            for (Edge edge : node.edges) {
//                System.out.println(edge.source.name+" "+edge.destination.name+" "+edge.weight);
                if (edge.source == from || edge.destination == from) {
                    node.edges.remove(edge);
//                    System.out.println(edge.source.name+" "+edge.destination.name+" "+edge.weight);
                }
            }
        }
        nodes.remove(from);
    }
    // Kiểm tra xem có cạnh giữa hai node không
    public boolean hasEdge(Node source, Node destination) {
        LinkedList<Edge> edges = source.edges;
        for (Edge edge : edges) {
            if (edge.destination == destination) {
                return true;
            }
        }
        return false;
    }

    // Lấy trọng số cạnh giữa hai node
    public double Weight(Node source, Node destination) {
        LinkedList<Edge> edges = source.edges;
        for (Edge edge : edges) {
            if (edge.destination == destination) {
                return edge.weight;
            }
        }
        return 0d;
    }
    // Các phương thức chung có thể được định nghĩa ở đây.
    // Phương thức này sẽ được triển khai bởi các lớp con.
    //public abstract String getShortestPath(Node start, Node end);
}
