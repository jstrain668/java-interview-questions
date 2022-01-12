package com.dsa.foundation.graphs.udgraph;

import java.util.LinkedList;
import java.util.Queue;

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


        // A BFS based function to check whether d is reachable from s.
        public boolean isReachable(int s, int d)
        {
            // Base case
            if (s == d)
                return true;

            // Mark all the vertices as not visited
            boolean[] visited = new boolean[vertices];

            // Create a queue for BFS
            Queue<Integer> queue = new LinkedList<>();

            // Mark the current node as visited and enqueue it
            visited[s] = true;
            queue.add(s);

            while (!queue.isEmpty()) {
                // Dequeue a vertex from queue and print it
                s = queue.poll();

                // Get all adjacent vertices of the dequeued vertex s
                // If a adjacent has not been visited, then mark it
                // visited  and enqueue it
                for (int i=0; i<adjList[s].size();i++) {

                    // If this adjacent node is the destination node,
                    // then return true
                    if (adjList[s].get(i) == d)
                        return true;

                    // Else, continue to do BFS
                    if (!visited[adjList[s].get(i)]) {
                        visited[adjList[s].get(i)] = true;
                        queue.add(adjList[s].get(i) );
                    }
                }
            }

            // If BFS is complete without visiting d
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

        int u = 3, v = 1;
        if (graph.isReachable(u, v))
            System.out.println("There is a path from "+u+" to "+v);
        else
            System.out.println("There is no path from "+u+" to "+v);
    }
}
