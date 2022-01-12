package com.dsa.foundation.graphs.shortestpath;

import java.util.LinkedList;
import java.util.Queue;

//Reference: https://www.geeksforgeeks.org/print-all-shortest-paths-between-given-source-and-destination-in-an-undirected-graph/

//Find the length of the shortest path between two vertices in an unweighted and undirected graph
//Reference: https://stackoverflow.com/questions/49734206/finding-the-shortest-path-between-two-pointsbfs

//The modified BFS solution works for directed and unweighted graphs. Note solution will not work for weighted graphs,
//use Djisktra or Bellman-Ford or another.
public class UDGraph {

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

            if (source < 0 || source >= this.vertices || destination < 0 || destination >= this.vertices)
            {
                return;
            }

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

        public LinkedList shortest(int from, int to) {

            Queue<Integer> q = new LinkedList<>();
            LinkedList<Integer> result = new LinkedList<>();

            if (from == to){
                return result;
            }

            int[] prev = new int[vertices];
            for (int i=0; i < vertices; i++){
                prev[i] = -1;
            }

            q.add(from);
            while (!q.isEmpty()){
                int cVertex = q.poll();

                for (int i=0; i < adjList[cVertex].size(); i++){
                    int adjVertex = adjList[cVertex].get(i);

                    if (prev[adjVertex] == -1){
                        prev[adjVertex] = cVertex;

                        if (adjVertex == to){
                            while (adjVertex != from){
                                result.addFirst(adjVertex);
                                adjVertex = prev[adjVertex];
                            }

                            return result;
                        }
                        q.add(adjVertex);
                    }
                }
            }
            return result;
        }
    }

    public Graph createGraph(int vertices){
        return new Graph(vertices);
    }

    public static void main(String[] args) {
        UDGraph udg = new UDGraph();

        Graph g = udg.createGraph(9);

        g.addEdge(0, 5);
        g.addEdge(0, 7);
        g.addEdge(1, 5);
        g.addEdge(1, 4);
        g.addEdge(1, 2);
        g.addEdge(2, 1);
        g.addEdge(2, 4);
        g.addEdge(2, 3);
        g.addEdge(3, 4);
        g.addEdge(3, 2);
        g.addEdge(4, 5);
        g.addEdge(4, 1);
        g.addEdge(4, 2);
        g.addEdge(4, 3);
        g.addEdge(5, 0);
        g.addEdge(5, 1);
        g.addEdge(5, 4);
        g.addEdge(6, 7);
        g.addEdge(7,6);
        g.addEdge(7,0);

        LinkedList<Integer> result;

        for (int a = 0; a < 9; a++) {
            System.out.print("--- ");
            System.out.println(a);

            for (int b = 0; b < 9; b++) {
                result = g.shortest(a,b);
                if (result.size()>0){
                    System.out.println("Shortest path between "+a+" and "+b+" "+g.shortest(a, b));
                    System.out.println("Length of shortest path: "+result.size());
                } else {
                    System.out.println("There is no path between "+a+" and "+b);
                }
            }
        }

        UDGraph udg1 = new UDGraph();

        Graph g1 = udg.createGraph(9);

        g1.addEdge( 0, 1);
        g1.addEdge( 0, 2);
        g1.addEdge( 1, 3);
        g1.addEdge( 1, 4);
        g1.addEdge( 2, 3);
        g1.addEdge( 3, 5);
        g1.addEdge( 4, 5);

        // Given source and destination
        int src = 0;
        int dest = 5;


        LinkedList<Integer> result1 = g1.shortest(src,dest);
        System.out.println("Shortest path between "+src+" and "+dest+" "+result1);
        System.out.println("Length of shortest path: "+result1.size());

    }
}
