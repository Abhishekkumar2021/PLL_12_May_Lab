package Lab.SC_Components;

import java.io.PrintWriter;

public class Graph {
    private int edges;
    private int vertices;
    private int[][] adjMatrix;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = 0;
        this.adjMatrix = new int[vertices][vertices];

        // Initialize the adjacency matrix
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                adjMatrix[i][j] = 0;
            }
        }
    }

    public void addEdge(int u, int v) {
        adjMatrix[u][v] = 1;
        edges++;
    }

    public int getEdges() {
        return edges;
    }

    public int getVertices() {
        return vertices;
    }

    public int[][] getAdjMatrix() {
        return adjMatrix;
    }

    public void printAdjMatrix() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void printAdjList() {
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + ": ");
            for (int j = 0; j < vertices; j++) {
                if (adjMatrix[i][j] == 1) {
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }

    // Reverse the graph
    public Graph reverse() {
        Graph reverse = new Graph(vertices);
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                if (adjMatrix[i][j] == 1) {
                    reverse.addEdge(j, i);
                }
            }
        }
        return reverse;
    }

    // Run buildStack on the graph
    public void buildStack(int u, boolean[] visited, int[] stack, Wrapper<Integer> topWrapper) {
        visited[u] = true;
        for (int i = 0; i < vertices; i++) {
            if (adjMatrix[u][i] == 1 && !visited[i]) {
                buildStack(i, visited, stack, topWrapper);
            }
        }
        topWrapper.setValue(topWrapper.getValue() + 1);
        stack[topWrapper.getValue()] = u;
    }

    // Run DFS on the graph 
    public void DFS(int u, boolean[] visited){
        visited[u] = true;
        System.out.print(u + " ");
        for (int i = 0; i < vertices; i++) {
            if (adjMatrix[u][i] == 1 && !visited[i]) {
                DFS(i, visited);
            }
        }
    }

    // Assign the SCCs
    public void assignSCC(int u, boolean[] visited, int[] component, int count){
        visited[u] = true;
        component[u] = count;
        for (int i = 0; i < vertices; i++) {
            if (adjMatrix[u][i] == 1 && !visited[i]) {
                assignSCC(i, visited, component, count);
            }
        }
    }

    // Show graph using .dot file
    public void showGraph(String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.println("digraph {");
            writer.println("node [shape = circle];");
            writer.println("rankdir=\"LR\";");
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (adjMatrix[i][j] == 1) {
                        writer.println(i + " -> " + j);
                    }
                }
            }
            writer.println("}");
            writer.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
