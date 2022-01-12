package com.dsa.medium.graphs;

import java.util.LinkedList;

public class UDGCycle {

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

        //Undirected graph
        public void addEdge(int source, int destination) {
            adjList[source].addFirst(destination);
            adjList[destination].addFirst(source);
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

        boolean isCycleUtil(int currVertex, boolean[] visited, int parent){
            visited[currVertex] = true;

            for (int i=0; i < adjList[currVertex].size(); i++) {
                int adjVertex = adjList[currVertex].get(i);
                if (adjVertex != parent) {
                    if (visited[adjVertex]) {
                        return true;
                    } else {
                        if (isCycleUtil(adjVertex, visited, currVertex)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public boolean isCycle() {
            boolean[] visited = new boolean[vertices];

            for (int i=0; i < vertices; i++){
                if (!visited[i]) {
                    if (isCycleUtil(i, visited, -1)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }

    public Graph createGraph(int vertices){
        return new Graph(vertices);
    }

    public static void main(String[] args) {
        UDGCycle udgc = new UDGCycle();
        Graph graph = udgc.createGraph(6);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 5);
        graph.addEdge(5, 2);
        boolean result = graph.isCycle();
        System.out.println("is Cycle present: " + result);
    }
}
