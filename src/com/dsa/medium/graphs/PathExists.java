package com.dsa.medium.graphs;

import java.util.LinkedList;
import java.util.Queue;

//Reference: https://www.geeksforgeeks.org/find-if-there-is-a-path-between-two-vertices-in-an-undirected-graph/

public class PathExists {

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

        public boolean isReachable(int s,int d){

            if (s==d){
                return true;
            }

            boolean[] visited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();

            visited[s] = true;
            queue.add(s);

            while (!queue.isEmpty()){
                int vertex = queue.poll();

                for (int i=0; i < adjList[vertex].size(); i++){
                    int adjVertex = adjList[vertex].get(i);

                    if (adjVertex == d){
                        return true;
                    }

                    if (!visited[adjVertex]){
                        visited[adjVertex] = true;
                        queue.add(adjVertex);
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
        PathExists pe = new PathExists();
        Graph graph = pe.createGraph(4);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        int u = 1, v = 3;
        if (graph.isReachable(u, v))
            System.out.println("There is a path from "+u+" to "+v);
        else
            System.out.println("There is no path from "+u+" to "+v);
    }
}
