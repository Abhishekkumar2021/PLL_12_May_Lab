package Lab.SC_Components;

import java.io.IOException;
import java.io.PrintWriter;

public class Kosaraju {
    private Graph graph;
    private Graph reverse;

    public Kosaraju(int vertices) {
        this.graph = new Graph(vertices);
    }

    public void addEdge(int u, int v) {
        graph.addEdge(u, v);
    }

    public Graph getGraph() {
        return graph;
    }

    public Graph getReverse() {
        return reverse;
    }
    
    // default argument
    public void run() {
        /*
         * 1. Run DFS on the original graph sort the vertices   in order of finish time
         * 2. Reverse the graph
         * 3. Run DFS on the reversed graph to get the SCCs
         * 4. Print the SCCs
         * 5. Print the number of SCCs
        */

        // Run DFS on the original graph
        int n = graph.getVertices();
        int[] stack = new int[n];
        int top = -1;
        Wrapper<Integer> topWrapper = new Wrapper<Integer>(top);
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        graph.buildStack(0, visited, stack, topWrapper);

        // Reverse the graph
        reverse = graph.reverse();

        // Run DFS on the reversed graph to get the SCCs
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        top = topWrapper.getValue();
        int count = 0;

        int[] component = new int[n];
        while(top >= 0){
            int u = stack[top];
            top--;
            if(!visited[u]){
                component[u] = count++;
                System.out.print("SCC " + count + ": ");
                reverse.DFS(u, visited);
                System.out.println();
            }
        }

        // Print the number of SCCs
        System.out.println("Number of SCCs: " + count);

    }

    public void showGraph(String filename) {
        graph.showGraph(filename);
    }

    public void showComponents(String filename) {
        int n = graph.getVertices();
        int[] component = new int[n];
        int count = 1;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                component[i] = count;
                reverse.assignSCC(i, visited, component, count);
                count++;
            }
        }

        String[] colors = new String[count];
        for (int i = 0; i < count; i++) {
            int r = (int) (Math.random() * 255);
            int g = (int) (Math.random() * 255);
            int b = (int) (Math.random() * 255);
            colors[i] = String.format("\"#%02x%02x%02x%02x\"", r, g, b, 150); // %02x means 2 digits in hex
        }

        try {
            PrintWriter writer = new PrintWriter(filename, "UTF-8");
            writer.println("digraph G {");
            for(int i=1; i<=count; i++){
                for(int j=0; j<n; j++){
                    if(component[j] == i){
                        writer.println(j + " [style=filled, fillcolor=" + colors[i-1] + "];");
                    }
                }
            }

            int[][] adjMatrix = graph.getAdjMatrix();

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (adjMatrix[i][j] == 1) {
                        writer.println(i + " -> " + j + ";");
                    }
                }
            }
            writer.println("}");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Kosaraju kosaraju = new Kosaraju(8);

        kosaraju.addEdge(0, 1);
        kosaraju.addEdge(1, 2);
        kosaraju.addEdge(2, 0);
        kosaraju.addEdge(1, 3);
        kosaraju.addEdge(3, 4);
        kosaraju.addEdge(3, 4);
        kosaraju.addEdge(4, 5);
        kosaraju.addEdge(5, 6);
        kosaraju.addEdge(6, 4);
        kosaraju.addEdge(6, 7);
        kosaraju.addEdge(4, 7);

        kosaraju.run();

        Graph graph = kosaraju.getGraph();
        graph.showGraph("Java/Lab/SC_Components/Kosaraju.dot");
        Graph reverse = kosaraju.getReverse();
        reverse.showGraph("Java/Lab/SC_Components/KosarajuReverse.dot");
        kosaraju.showComponents("Java/Lab/SC_Components/KosarajuComponents.dot");
        
    }
}
