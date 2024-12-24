package application.algorithm;

import application.model.Node;
import application.model.Edge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class GraphAlgorithm {
    protected boolean directed;
    public Set<Node> nodes;

    // Constructor chung
    public GraphAlgorithm() {
        this.nodes = new HashSet<>();
    }

    public GraphAlgorithm(boolean directed) {
        this.directed = directed;
        nodes = new HashSet<>();
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

    public void copyEdge(ArrayList<Edge> edges){
        for(Node node : nodes){
            edges.addAll(node.edges);
        }
    }

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
    //public abstract String getShortestPath(Node start, Node end);

}
