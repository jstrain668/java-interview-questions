package com.dsa.medium.graphs;

import java.util.LinkedList;
import java.util.Stack;

//Reference: https://www.codespeedy.com/a-java-program-for-topological-sorting/

public class TopoSort {
    public class Graph {
        int vertices;
        LinkedList<Integer>[] adjList;

        Graph(int vertices) {
            this.vertices = vertices;
            adjList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new LinkedList<>();
            }
        }

        //Directed graph
        public void addEdge(int source, int destination) {

            if (source < 0 || source >= this.vertices || destination < 0 || destination >= this.vertices) {
                return;
            }
            adjList[source].addFirst(destination);
        }

        public void printGraph() {
            for (int i = 0; i < vertices; i++) {
                LinkedList<Integer> nodeList = adjList[i];
                System.out.println("Vertex connected from vertex: " + i);

                for (int j = 0; j < nodeList.size(); j++) {
                    System.out.print("->" + nodeList.get(j));
                }
                System.out.println();
            }
        }

        public void topologicalSortUtil(int cVertex,boolean[] visited,Stack<Integer> stack){
            visited[cVertex] = true;

            for (int i=0; i < adjList[cVertex].size(); i++){
                int adjVertex = adjList[cVertex].get(i);
                if (!visited[adjVertex]){
                    topologicalSortUtil(adjVertex,visited,stack);
                }
            }
            stack.push(cVertex);
        }

       public void topologicalSort(){
            boolean[] visited = new boolean[vertices];
            Stack<Integer> stack = new Stack<>();

            for (int i=0; i < vertices; i++){
                if (!visited[i]){
                    topologicalSortUtil(i,visited,stack);
                }
            }

            while (!stack.isEmpty()){
                System.out.print(stack.pop()+" ");
            }
       }

    }

    public Graph createGraph(int vertices){
        return new Graph(vertices);
    }

    public static void main(String[] args) {
        // Create a graph given in the above diagram
        TopoSort dg = new TopoSort();
        Graph g = dg.createGraph(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        System.out.println("After performing the Topological Sort, the given graph is:");
        g.topologicalSort();
    }
}
