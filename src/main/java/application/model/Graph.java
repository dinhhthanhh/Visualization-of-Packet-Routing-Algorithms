package application.model;

import application.algorithm.Dijkstra;
import application.algorithm.GraphAlgorithm;

import java.util.*;
public class Graph {
    private final LinkedList<Node> nodes = new LinkedList<>();

    public void addNode(double x, double y, String name){
        Node temp = new Node(x,y,name);
        nodes.add(temp);
    }
    public Node getNode(String from){
        for(Node i:nodes){
            if(i.name.equals(from)){
                return i;
            }
        }
        return null;
    }
    public String SearchNode(String node) {
        for(Node i:nodes){
            if(i.getName().equals(node)){
                return ("X coordinate:"+i.x+"\n"+"Y coordinate:"+i.y);
            }
        }
        return ("Node not Found");
    }
    private final Dijkstra adj = new Dijkstra(true);
    private final GraphAlgorithm ga = new GraphAlgorithm(true);
    public void DeleteNode(String node){
        for(Node n:nodes){
            if(n.getName().equals(node)){
//                nodes.remove(n);
                ga.DeleteNo(n);
                nodes.remove(n);
                return;
            }
        }
    }

    public List<Edge> getEdges() {
        List<Edge> edges = new ArrayList<>();
        for (Node node : nodes) {
            edges.addAll(node.getEdge());
        }
        return edges;
    }

    public void addEdge(String from, String to, double weight){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.getName().equals(from)){
                fromNode = i;
            }
            if(i.getName().equals(to)){
                toNode = i;
            }
        }
        if(fromNode == null)
            System.out.println("Form node not found");
        else if(toNode == null)
            System.out.println("To node not Found");
        else {
            ga.addEdge(fromNode, toNode, weight);
            System.out.println ("Edge added Successfully");
            syncNodesAndEdges();
        }
    }

    public void syncNodesAndEdges() {
        // Đồng bộ các node
        for (Node node : ga.nodes) {
            adj.addNode(node);
//            bellmanFord.addNode(node);
//            flooding.addNode(node);
        }

        // Đồng bộ các cạnh
        ArrayList<Edge> edges = new ArrayList<>();
        ga.copyEdge(edges);  // Sao chép các cạnh từ ga

        for (Edge edge : edges) {
            adj.addEdge(edge.source, edge.destination, edge.weight);
//            bellmanFord.addEdge(edge.source, edge.destination, edge.weight);
//            flooding.addEdge(edge.source, edge.destination, edge.weight);
        }
    }

    public void renameNode(String oldName, String newName) {
        Node targetNode = getNode(oldName);
        if (targetNode == null) {
            return;
        }

        if (getNode(newName) != null) {
            return;
        }

        targetNode.name = newName;
    }

    public void DeleteEdge(String from, String to){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.getName().equals(from)){
                fromNode = i;
            }
            if(i.getName().equals(to)){
                toNode = i;
            }
        }
        if(fromNode == null || toNode == null){
            System.out.println ("Edge not Found");
        }
        else if(fromNode == toNode){
            System.out.println("Both Nodes are same!");
        }
        else {

            if(ga.DeleteEd(fromNode, toNode)){
                System.out.println ("Edge deleted");
            }
            else
                System.out.println("Edge Not Found");
        }
    }

    public String getPath(String from, String to){
        String output;
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.getName().equals(from)){
                fromNode = i;
            }
            if(i.getName().equals(to)){
                toNode =i;
            }
        }
        output = adj.DijkstraShortestPath(fromNode,toNode);
        adj.resetNodesVisited();
        return output;
    }

    public LinkedList<Node> getNodes(){
        return nodes;
    }
    public Dijkstra getAdj(){
        return adj;
    }

    public Stack<Node> getNodePath(String from, String to){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.getName().equals(from)){
                fromNode = i;
            }
            if(i.getName().equals(to)){
                toNode = i;
            }
        }
        return adj.animatePath(fromNode,toNode);
    }


}