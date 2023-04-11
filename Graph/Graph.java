package Lab.Graph;

import Lab.LinkedList.LinkedList;
import Lab.LinkedList.Node;

public class Graph {
    private final int V; // Number of vertices in the graph
    private LinkedList<Edge> edges; // List of edges in the graph

    Graph(int V) {
        this.V = V;
        edges = new LinkedList<Edge>();
    }

    public int getV() {
        return V;
    }

    LinkedList<Edge> getEdges() {
        return edges;
    }

    LinkedList<Edge> getEdges(int src) {
        LinkedList<Edge> edges = new LinkedList<Edge>();
        for (int i = 0; i < this.edges.size(); i++) {
            Node<Edge> node = this.edges.get(i);
            if(node == null)
                break;
            Edge edge = node.data;
            if(edge.src == src)
                edges.add(edge);
        }
        return edges;
    }

    public void addEdge(int src, int dest, int weight) {
        Edge edge = new Edge(src, dest, weight);
        edges.add(edge);
    }

    public void printGraph() {
        for (int i = 0; i < edges.size(); i++) {
            Node<Edge> node = edges.get(i);
            if(node == null)
                break;
            Edge edge = node.data;
            System.out.println("Edge-" + i + " source: " + edge.src + " destination: " + edge.dest + " weight: " + edge.weight);
        }
    }

}
