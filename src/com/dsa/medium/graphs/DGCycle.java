package com.dsa.medium.graphs;

import java.util.LinkedList;

public class DGCycle {
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

        public void addEgde(int source, int destination) {
            adjList[source].addFirst(destination);
        }

        public boolean isCycle() {
           boolean[] visited = new boolean[vertices];
           boolean[] recurArr = new boolean[vertices];

           for (int i=0; i < vertices; i++){
               if (isCycleUtil(i,visited,recurArr)){
                   return true;
               }
           }
           return false;
        }

        public boolean isCycleUtil(int vertex, boolean[] visited, boolean[] recursiveArr) {
            visited[vertex] = true;
            recursiveArr[vertex] = true;

            for (int i = 0; i < adjList[vertex].size(); i++) {
                int dest = adjList[vertex].get(i);
                if (!visited[dest] && isCycleUtil(dest, visited, recursiveArr)) {
                    return true;
                } else if (recursiveArr[dest]) {
                    return true;
                }
            }
            recursiveArr[vertex] = false;
            return false;
        }
    }

    public Graph createGraph(int vertices){
        return new Graph(vertices);
    }

    public static void main(String[] args) {
        DGCycle cycle = new DGCycle();
        int vertices = 4;
        Graph graph = cycle.createGraph(vertices);
        graph.addEgde(0, 1);
        graph.addEgde(1, 2);
        graph.addEgde(2, 3);
        //graph.addEgde(3, 1);
        boolean result = graph.isCycle();
        System.out.println("is Cycle present: " + result);
    }
}
