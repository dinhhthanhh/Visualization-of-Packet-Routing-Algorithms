package application.model;

import application.algorithm.*;

import java.util.*;
public class Graph {
    private final LinkedList<Node> nodes = new LinkedList<>();
    private final GraphAlgorithm ga = new GraphAlgorithm(true);
    private final Dijkstra adj = new Dijkstra();
    private final BellmanFord bf = new BellmanFord();
    private final Flooding fl = new Flooding();

    public void addNode(double x, double y, String name){
        Node temp = new Node(x,y,name);
        nodes.add(temp);
        System.out.println("Node added Successfully");
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
            // Kiểm tra xem cạnh đã tồn tại chưa
            for (Edge edge : fromNode.edges) {
                if (edge.destination.equals(toNode)) {
                    System.out.println("Edge from " + from + " to " + to + " already exists");
                }
            }

            // Thêm cạnh mới nếu chưa tồn tại
            ga.addEdge(fromNode, toNode, weight);
            adj.addEdge(fromNode, toNode, weight);
            bf.addEdge(fromNode, toNode, weight);
            System.out.println ("Edge added Successfully");
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
        for (Node i : nodes) {
            if(i.getName().equals(from)){
                fromNode = i;
            }
            if(i.getName().equals(to)){
                toNode = i;
            }
        }
        output = adj.DijkstraShortestPath(fromNode,toNode);
        adj.resetNodesVisited();
        return output;
    }

    public LinkedList<Node> getNodes(){
        return nodes;
    }
    public GraphAlgorithm getga(){
        return ga;
    }
    public Dijkstra getAdj(){
        return adj;
    }
    public BellmanFord getbf(){
        return bf;
    }
    public Flooding getfl(){
        return fl;
    }

    public Stack<Node> getNodePathdijkstra(String from, String to){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.getName().equals(from)){
                fromNode = i;
            }
            if(i.getName().equals(to)){
                toNode = i;
            }
        }
        return adj.animatePathadj(fromNode,toNode);
    }
    public Stack<Node> getNodePathbellmanford(String from, String to){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.getName().equals(from)){
                fromNode = i;
            }
            if(i.getName().equals(to)){
                toNode = i;
            }
        }
        return bf.animatePathbf(fromNode,toNode);
    }
    public Stack<Node> getNodePathflooding(String from, String to){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.getName().equals(from)){
                fromNode = i;
            }
            if(i.getName().equals(to)){
                toNode = i;
            }
        }
        return fl.animatePathfl(fromNode,toNode);
    }
}