package application;

import java.io.*;
import java.util.*;
public class Graph {
    private LinkedList<Node> nodes = new LinkedList<>();
    private Dijkstra adj = new Dijkstra(true);

    String addNode(double x,double y,String name){
        Node temp = new Node(x,y,name);
        nodes.add(temp);
        return ("Node Added Successfully");
    }
    Node getNode(String from){
        for(Node i:nodes){
            if(i.name.equals(from)){
                return i;
            }
        }
        return null;
    }
    String SearchNode(String node) {
        for(Node i:nodes){
            if(i.name.equals(node)){
                return ("X coordinate:"+i.x+"\n"+"Y coordinate:"+i.y);
            }
        }
        return ("Node not Found");
    }

    String DeleteNode(String node){
        for(Node n:nodes){
            if(n.name.equals(node)){
//                nodes.remove(n);
                adj.DeleteNo(n);
                nodes.remove(n);
                return  "Node Deleted";
            }
        }
        return "Node Doesn't exist";
    }

    String ModifyNode(String node,double x,double y){
        for(Node i:nodes){
            if(i.name.equals(node)){
                i.x = x;
                i.y = y;
                return ("Node Modified");
            }
        }
        return ("Node not Found");
    }

    String addEdge(String from,String to,double weight){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.name.equals(from)){
                fromNode = i;
            }
            if(i.name.equals(to)){
                toNode = i;
            }
        }
        if(fromNode == null)
            return ("Form node not found");
        else if(toNode == null)
            return ("To node not Found");
        else {
            adj.addEdge(fromNode, toNode, weight);
            return ("Edge added Successfully");
        }
    }

    String SearchEdge(String from,String to){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.name.equals(from)){
                fromNode = i;
            }
            if(i.name.equals(to)){
                toNode = i;
            }
        }
        if(fromNode == null || toNode == null) {
            return ("Edge Not Found");
        }
        else
        {
            if(adj.hasEdge(fromNode, toNode)){
                return ("Edge Found \n"+"Weight is "+adj.Weight(fromNode,toNode));
            }
            else
                return ("Edge Not Found");
        }
    }

    String ModifyEdge(String from,String to,double weight){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.name.equals(from)){
                fromNode = i;
            }
            if(i.name.equals(to)){
                toNode = i;
            }
        }
        if(fromNode == null || toNode == null)
            return ("Edge not Found");
        else {
            adj.ModifyEdgeWeight(fromNode, toNode,weight);
            return ("Edge Modified Successfully");
        }
    }
    String DeleteEdge(String from,String to){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.name.equals(from)){
                fromNode = i;
            }
            if(i.name.equals(to)){
                toNode = i;
            }
        }
        if(fromNode == null || toNode == null){
            return ("Edge not Found");
        }
        else if(fromNode == toNode){
            return ("Both Nodes are same!");
        }
        else {

            if(adj.DeleteEd(fromNode, toNode)){
                return ("Edge deleted");
            }
            else
                return ("Edge Not Found");
        }
    }

    String getPath(String from,String to){
        String output;
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.name.equals(from)){
                fromNode = i;
            }
            if(i.name.equals(to)){
                toNode =i;
            }
        }
        output = adj.DijkstraShortestPath(fromNode,toNode);
        adj.resetNodesVisited();
        return output;
    }

    String importFrom(String path) {
        FileReader fr;
        try {
            fr = new FileReader(path);
        } catch (FileNotFoundException fe) {
            return "File not Found";
        }
        Scanner myReader = new Scanner(fr);
        try {
            if (!myReader.hasNextInt()) {
                return "Invalid Input: Missing number of nodes";
            }
            int no_of_nodes = myReader.nextInt();

            for (int i = 0; i < no_of_nodes; i++) {
                if (!myReader.hasNext()) {
                    return "Invalid Input: Missing node name";
                }
                String name = myReader.next();

                if (!myReader.hasNextDouble()) {
                    return "Invalid Input: Missing x coordinate";
                }
                double x = myReader.nextDouble();

                if (!myReader.hasNextDouble()) {
                    return "Invalid Input: Missing y coordinate";
                }
                double y = myReader.nextDouble();

                addNode(x, y, name);
            }

            if (!myReader.hasNextInt()) {
                return "Invalid Input: Missing number of edges";
            }
            int no_of_edges = myReader.nextInt();

            for (int i = 0; i < no_of_edges; i++) {
                if (!myReader.hasNext()) {
                    return "Invalid Input: Missing source node name";
                }
                String from = myReader.next();

                if (!myReader.hasNext()) {
                    return "Invalid Input: Missing destination node name";
                }
                String to = myReader.next();

                if (!myReader.hasNextDouble()) {
                    return "Invalid Input: Missing edge weight";
                }
                double weight = myReader.nextDouble();

                addEdge(from, to, weight);
            }
        } catch (NumberFormatException e) {
            return "Invalid Input: Number format error";
        } finally {
            myReader.close();
        }
        return "Values Imported";
    }

    String exportTo(String path) throws IOException {

        FileWriter fw;
        ArrayList<Edge> edgeArrayList = new ArrayList<>();
        adj.copyEdge(edgeArrayList);

        try{
            fw = new FileWriter(path);
        } catch (IOException e) {
            return "File not Found";
        }
        fw.write(nodes.size()+"");
        fw.write("\n");
        for(Node n : nodes){
            fw.write(n.x+" "+n.y+" "+n.name+"\n");
        }
        fw.write(edgeArrayList.size()+"");
        fw.write("\n");
        for(Edge e :edgeArrayList){
            fw.write(e.source.name+" "+e.destination.name+" "+e.weight+"\n");
        }
        fw.close();
        return "File Exported";
    }

    LinkedList<Node> getNodes(){
        return nodes;
    }
    Dijkstra getAdj(){
        return adj;
    }

    Stack<Node> getNodePath(String from,String to){
        Node fromNode=null,toNode=null;
        for (Node i :nodes) {
            if(i.name.equals(from)){
                fromNode = i;
            }
            if(i.name.equals(to)){
                toNode = i;
            }
        }
        return adj.animatePath(fromNode,toNode);
    }

}