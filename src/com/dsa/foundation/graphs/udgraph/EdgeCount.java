package com.dsa.foundation.graphs.udgraph;

//Reference: https://www.geeksforgeeks.org/count-number-edges-undirected-graph/

import java.util.LinkedList;

public class EdgeCount {


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

        // Returns count of edge in undirected graph
        public int countEdges(){

            int sum = 0;

            // traverse all vertex
            for (int i=0; i < vertices; i++){
                // add all edge that are linked to the
                // current vertex
                sum += adjList[i].size();
            }

            return sum = sum/2;
        }

    }

    public Graph createGraph(int vertices){
        return new Graph(vertices);
    }

    public static void main(String[] args) {
        EdgeCount ec = new EdgeCount();
        Graph graph = ec.createGraph(9);

        graph.addEdge(0, 1);
        graph.addEdge(0, 7);
        graph.addEdge(1, 2);
        graph.addEdge(1, 7);
        graph.addEdge(2, 3);
        graph.addEdge(2, 8);
        graph.addEdge(2, 5);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(6, 8);
        graph.addEdge(7, 8);

        System.out.println(graph.countEdges());
    }
}
