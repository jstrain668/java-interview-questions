package com.dsa.foundation.queue;

import com.dsa.foundation.stacks.GraphDFS;

import java.util.LinkedList;
import java.util.Queue;

public class GraphBFS {

    public class Graph {
        int vertices;
        LinkedList<Integer>[] adjList;

        public Graph(int vertices) {
            this.vertices = vertices;
            adjList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination) {

            //Directed graph
            adjList[source].addFirst(destination);

        }

        public void printGraph() {

            for (int i = 0; i < vertices; i++) {
                LinkedList<Integer> nodeList = adjList[i];
                System.out.print("Vertex " + i + " connected to ");
                for (int j = 0; j < nodeList.size(); j++) {
                    System.out.print(nodeList.get(j) + " ");
                }
                System.out.println();
            }
        }

        public void BFS(int startVertex){
            boolean[] visited = new boolean[vertices];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(startVertex);

            while (!queue.isEmpty()){
                int index = queue.poll();
                visited[index] = true;
                System.out.print(index+" ");
                LinkedList<Integer> nodeList = adjList[index];

                for (int i=0; i < nodeList.size(); i++){
                    int dest = nodeList.get(i);
                    if (!visited[dest]){
                        queue.add(dest);
                        visited[dest] = true;
                    }
                }
            }
        }

    }

    public Graph createGraph(int vertices){
        return new Graph(vertices);
    }

    public static void main(String[] args) {
        GraphBFS gBFS = new GraphBFS();
        Graph graph = gBFS.createGraph(6);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 4);
        graph.addEdge(2, 3);
        graph.addEdge(4, 0);
        graph.addEdge(4, 1);
        graph.addEdge(4, 5);
        graph.printGraph();
        graph.BFS(0);
    }
}
