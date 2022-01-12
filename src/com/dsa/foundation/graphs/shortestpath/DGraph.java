package com.dsa.foundation.graphs.shortestpath;

//Find the length of the shortest path between two vertices in an unweighted and directed graph

import java.util.LinkedList;
import java.util.Queue;

public class DGraph {

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

        public LinkedList shortest(int from, int to)
        {
            Queue<Integer> queue = new LinkedList<Integer>();
            LinkedList<Integer> res = new LinkedList<Integer>();

            int prev[] = new int[vertices];

            if (from == to) {
                return res;
            }
            queue.add(from);

            for (int i = 0; i < vertices; i++) {
                prev[i] = -1;
            }

            while (queue.size() != 0) {
                int currVertex = queue.poll();

                for (int i=0; i < adjList[currVertex].size(); i++){
                    int adjVertex = adjList[currVertex].get(i);

                    if (prev[adjVertex] == -1) {                // unvisited?
                        prev[adjVertex] = currVertex;           // store previous vertex

                        if (adjVertex == to) {                  // found the path from source to dest!
                            while (adjVertex != from) {         // build result list ...
                                res.addFirst(adjVertex);
                                adjVertex = prev[adjVertex];
                            }

                            return res;                 // ... and return it
                        }
                        queue.add(adjVertex);
                    }
                }
            }
            return res;
        }
    }

    public Graph createGraph(int vertices){
        return new Graph(vertices);
    }

    public static void main(String[] args) {

        DGraph dg = new DGraph();
        Graph g = dg.createGraph(8);

        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(3, 7);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(4, 7);
        g.addEdge(5, 6);
        g.addEdge(6, 7);


        // Given source and destination
        int src = 0;
        int dest = 7;


        LinkedList<Integer> result = g.shortest(src,dest);
        System.out.println("Shortest path between "+src+" and "+dest+" "+result);
        System.out.println("Length of shortest path: "+result.size());

    }
}
