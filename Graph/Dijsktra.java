package Lab.Graph;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import Lab.LinkedList.LinkedList;
import Lab.LinkedList.Node;
import Lab.PriorityQueue.PriorityQueue;
import Lab.PriorityQueue.Pair;

public class Dijsktra {
    Graph graph;
    int V;
    int[] parent;
    int[] distance;
    boolean[] visited;
    LinkedList<Edge> Path;
    int src;
    int dest;

    Dijsktra(Graph graph, int src, int dest) {
        this.graph = graph;
        this.V = graph.getV();
        this.parent = new int[V];
        this.distance = new int[V];
        this.visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            distance[i] = Integer.MAX_VALUE;
            visited[i] = false;
            parent[i] = -1;
        }
        this.src = src;
        this.dest = dest;
    }

    public void dijsktra() {
        PriorityQueue queue = new PriorityQueue(V);
        distance[src] = 0;
        queue.insert(new Pair(src, 0));
        while (!queue.isEmpty()) {
            Pair pair = queue.remove();
            int u = pair.first;
            visited[u] = true;
            LinkedList<Edge> list = graph.getEdges(u);
            for (int i = 0; i < list.size(); i++) {
                Node<Edge> node = list.get(i);
                int v = node.data.dest;
                int weight = node.data.weight;
                if (!visited[v] && distance[v] > distance[u] + weight) {
                    distance[v] = distance[u] + weight;
                    parent[v] = u;
                    queue.insert(new Pair(v, distance[v]));
                }
            }
        }
    }

    public void getPath() {
        Path = new LinkedList<Edge>();
        int u = dest;
        while (parent[u] != -1) {
            Path.add(new Edge(parent[u], u, distance[u] - distance[parent[u]]));
            u = parent[u];
        }
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
            // label the src and dest nodes
            writer.write(src + " [label = " + src + ", color = red, style=filled, fillcolor=yellow];\n");
            writer.write(dest + " [label = " + dest + ", color = red, style=filled, fillcolor=yellow];\n");
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

    public void showPath(String filename) {
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
            writer.write(src + " [label = " + src + ", color = red, style=filled, fillcolor=yellow];\n");
            writer.write(dest + " [label = " + dest + ", color = red, style=filled, fillcolor=yellow];\n");
            for (int i = 0; i < Path.size(); i++) {
                Node<Edge> node = Path.get(i);
                if (node == null)
                    break;
                Edge edge = node.data;
                writer.write(edge.src + " -- " + edge.dest + " [label = " + edge.weight + ",color = red];\n");
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
            writer.write(src + " [label = " + src + ", color = red, style=filled, fillcolor=yellow];\n");
            writer.write(dest + " [label = " + dest + ", color = red, style=filled, fillcolor=yellow];\n");
            LinkedList<Edge> edges = graph.getEdges();
            for (int i = 0; i < edges.size(); i++) {
                Node<Edge> node = edges.get(i);
                if (node == null)
                    break;
                Edge edge = node.data;
                if (Path.contains(edge))
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

    public static void main(String args[]){
        File file = new File("./PLL_AS6/Dijsktra_Input.txt");
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
                int source = sc.nextInt();
                int dest = sc.nextInt();
                Dijsktra dijsktra = new Dijsktra(graph, source, dest);
                dijsktra.dijsktra();
                dijsktra.getPath();
                dijsktra.showGraph("Dijsktra_Graph_" + i);
                dijsktra.showPath("Dijsktra_Path_" + i);
                dijsktra.showBothTogether("Dijsktra_BothTogether_" + i);
                dijsktra.showAdjaceyList("Dijsktra_AdjacencyList_" + i);
                
            }
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}