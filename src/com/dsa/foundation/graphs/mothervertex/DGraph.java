package com.dsa.foundation.graphs.mothervertex;

import java.util.LinkedList;

//Reference: https://www.geeksforgeeks.org/find-a-mother-vertex-in-a-graph/

//Solution Approach: In this approach we run a DFS on whole Graph which is O(V+E). We store the last vertex from which
// DFS was called (which will yield visited track to true for all vertices ). Then we re-initialize the visited track to
// false for all vertices. Finally we run DFS on the last vertex (Stored) - O(V+E), if it visits all vertices it is
// mother vertex or there is no mother vertex in graph. The complete time complexity for this algorithm is O(V + E). The
// argument is that the last vertex visited in the recursive DFS will be the mother vertex.


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

        public void addEdge(int source, int destination) {
            adjList[source].addFirst(destination);
        }

        public void dfsUtil(int vertex, boolean[] visited){

            visited[vertex] = true;

            for (int i=0; i < adjList[vertex].size(); i++){
                int adjVertex = adjList[vertex].get(i);
                if (!visited[adjVertex]){
                    dfsUtil(adjVertex,visited);
                }
            }
        }

        public int motherVertex(){

            boolean[] visited = new boolean[vertices];
            int v = -1;  //Candidate mother vertex initialized to not found yet

            for (int i=0; i < vertices; i++){

                if (!visited[i]){
                    dfsUtil(v,visited);
                    v=i;
                }
            }

            System.out.println("Possible Mother Vertex Detected At:"  + v);
            boolean[] check = new boolean[vertices];
            dfsUtil(v,check);

            for (boolean val: check){
                if (!val){
                    return -1;
                }
            }
            return v;
        }
    }


    public Graph createGraph(int vertices){
        return new Graph(vertices);
    }

    public static void main(String[] args) {
        com.dsa.medium.graphs.MotherVertex mv = new com.dsa.medium.graphs.MotherVertex();
        com.dsa.medium.graphs.MotherVertex.Graph graph = mv.createGraph(7);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(4, 1);
        graph.addEdge(6, 4);
        graph.addEdge(5, 6);
        graph.addEdge(5, 2);
        graph.addEdge(6, 0);
        int motherVertex  = graph.motherVertex();
        if (motherVertex == -1) {
            System.out.println("No mother vertex found");
        }
        else{
            System.out.println("Mother vertex found at "+motherVertex);
        }

    }
}
