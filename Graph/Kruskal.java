package Lab.Graph;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import Lab.LinkedList.LinkedList;
import Lab.LinkedList.Node;

public class Kruskal {
    private Graph graph;
    private int V;
    private int[] parent;
    private int[] rank;
    private LinkedList<Edge> MST;

    Kruskal(Graph graph) {
        this.graph = graph;
        this.V = graph.getV();
        this.parent = new int[V];
        this.rank = new int[V];
        this.MST = new LinkedList<Edge>();
        makeSet();
    }

    public void makeSet() {
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }
    }

    // Find with path compression
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // Union by rank
    public void union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        if (xRoot == yRoot)
            return;
        if (rank[xRoot] < rank[yRoot]) {
            parent[xRoot] = yRoot;
        } else if (rank[xRoot] > rank[yRoot]) {
            parent[yRoot] = xRoot;
        } else {
            parent[yRoot] = xRoot;
            rank[xRoot] = rank[xRoot] + 1;
        }
    }

    public int kruskal() {
        int minCost = 0;
        LinkedList<Edge> edges = graph.getEdges();
        edges.sort();
        for (int i = 0; i < edges.size(); i++) {
            Node<Edge> node = edges.get(i);
            if (node == null)
                break;
            Edge edge = node.data;
            int x = find(edge.src);
            int y = find(edge.dest);
            if (x != y) {
                MST.add(edge);
                minCost += edge.weight;
                union(x, y);
            }
        }
        return minCost;
    }

    public LinkedList<Edge> getMST() {
        return MST;
    }

    public void showGraph(String filename) {
        // Create a file with the graphviz commands
        File file = new File("./PLL_AS6/" + filename + ".dot");
        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Write the graphviz commands to the file
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("graph G {\n");
            writer.write("node [shape = circle];\n");
            LinkedList<Edge> edges = graph.getEdges();
            for (int i = 0; i < edges.size(); i++) {
                Node<Edge> node = edges.get(i);
                if (node == null)
                    break;
                Edge edge = node.data;
                writer.write(edge.src + " -- " + edge.dest + " [label = " + edge.weight + "];\n");
            }
            writer.write("}");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showMST(String filename) {
        // Create a file with the graphviz commands
        File file = new File("./PLL_AS6/" + filename + ".dot");
        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Write the graphviz commands to the file
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("graph G {\n");
            writer.write("node [shape = circle];\n");
            for (int i = 0; i < MST.size(); i++) {
                Node<Edge> node = MST.get(i);
                if (node == null)
                    break;
                Edge edge = node.data;
                writer.write(edge.src + " -- " + edge.dest + " [label = " + edge.weight + "];\n");
            }
            writer.write("}");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showBothTogether(String filename) {
        // Create a file with the graphviz commands
        File file = new File("./PLL_AS6/" + filename + ".dot");
        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Write the graphviz commands to the file
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("graph G {\n");
            writer.write("node [shape = circle];\n");
            LinkedList<Edge> edges = graph.getEdges();
            for (int i = 0; i < edges.size(); i++) {
                Node<Edge> node = edges.get(i);
                if (node == null)
                    break;
                Edge edge = node.data;
                if (MST.contains(edge))
                    writer.write(edge.src + " -- " + edge.dest + " [label = " + edge.weight + ", color = red];\n");
                else
                    writer.write(edge.src + " -- " + edge.dest + " [label = " + edge.weight + "];\n");
            }
            writer.write("}");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAdjaceyList(String filename) {
        // Create a file with the graphviz commands
        File file = new File("./PLL_AS6/" + filename + ".dot");
        if (file.exists())
            file.delete();
        try {
            file.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Write the graphviz commands to the file
        try {
            FileWriter writer = new FileWriter(file);
            writer.write("digraph G {\n");
            writer.write("node [shape = rectangle];\n");
            writer.write("rankdir=\"LR\";\n");
            writer.write("nodesep=0;\n");
            
            LinkedList<Edge> allEdges = graph.getEdges();
            int n = allEdges.size();
            for(int j=0; j<n; j++){
                Edge edge = allEdges.get(j).data;
                graph.addEdge(edge.dest, edge.src, edge.weight);
            }
            for (int i = 0; i < graph.getV(); i++) {
                LinkedList<Edge> edges = graph.getEdges(i);
                writer.write(String.valueOf(i) + String.valueOf(i) + " [label=\"" + i + "\", width=1, height=1.2];\n");

                // Giving labels to nodes
                for (int j = 0; j < edges.size(); j++) {
                    Node<Edge> node = edges.get(j);
                    if (node == null)
                        break;
                    Edge edge = node.data;
                    writer.write(String.valueOf(i) + String.valueOf(edge.dest) + " [label=\" "
                            + String.valueOf(edge.dest) + "  |  " + String.valueOf(edge.weight) + " \"];\n");
                }

                // Starting edge
                Node<Edge> node = edges.get(0);
                if (node != null) {
                    Edge edge = node.data;
                    writer.write(String.valueOf(i) + String.valueOf(i) + " -> " + String.valueOf(i)
                            + String.valueOf(edge.dest) + ";\n");
                }

                // Connecting nodes
                for (int j = 0; j < edges.size() - 1; j++) {
                    Node<Edge> curr = edges.get(j);
                    Node<Edge> next = edges.get(j + 1);
                    if (curr == null || next == null)
                        break;
                    writer.write(String.valueOf(i) + String.valueOf(curr.data.dest) + " -> " + String.valueOf(i)
                            + String.valueOf(next.data.dest) + ";\n");
                }
            }

            writer.write("}");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        File file = new File("./PLL_AS6/Kruskal_Input.txt");
        if (!file.exists()) {
            System.out.println("File not found");
            return;
        }
        try {
            Scanner sc = new Scanner(file);
            int testCase = sc.nextInt();
            for (int i = 0; i < testCase; i++) {
                int V = sc.nextInt();
                int E = sc.nextInt();
                Graph graph = new Graph(V);
                for (int j = 0; j < E; j++) {
                    int src = sc.nextInt();
                    int dest = sc.nextInt();
                    int weight = sc.nextInt();
                    graph.addEdge(src, dest, weight);
                }
                Kruskal kruskal = new Kruskal(graph);
                kruskal.kruskal();
                kruskal.showGraph("Kruskal_Graph_" + i);
                kruskal.showMST("Kruskal_MST_" + i);
                kruskal.showBothTogether("Kruskal_BothTogether_" + i);
                kruskal.showAdjaceyList("Kruskal_AdjacencyList_" + i);
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
