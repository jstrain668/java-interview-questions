package com.dsa.medium.graphs;

import java.util.LinkedList;

public class MotherVertex {

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

        // A function used by DFS
        void DFSUtil(int v,boolean visited[])
        {
            // Mark the current node as visited
            visited[v] = true;
            // Recur for all the vertices adjacent to this vertex
            LinkedList<Integer> nodeList = adjList[v];
            for (int i=0; i < nodeList.size(); i++)
            {
                int adjVertex = nodeList.get(i);
                if (!visited[adjVertex])
                    DFSUtil(adjVertex, visited);
            }
        }


        // The function to do DFS traversal. It uses recursive DFSUtil()
        public int motherVertex()
        {
            // Mark all the vertices as not visited(set as false by default in java)
            boolean visited[] = new boolean[vertices];

            // To store last finished vertex (or mother vertex)
            int v = -1;

            for (int i = 0; i < vertices; i++)
            {
                if (!visited[i])
                {
                    DFSUtil(i, visited);
                    v = i;
                }
            }

            System.out.println("Possible Mother Vertex Detected At:"  + v);
            // If there exist mother vertex (or vertices)
            // in given graph, then v must be one
            // (or one of them)

            // Now check if v is actually a mother
            // vertex (or graph has a mother vertex).
            // We basically check if every vertex
            // is reachable from v or not.


            // Do DFS beginning from v to check
            // if all vertices are reachable from
            // it or not.
            boolean[] check = new boolean[vertices];
            DFSUtil(v, check);
            for(boolean val : check)
            {
                if (!val)
                {
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
        MotherVertex mv = new MotherVertex();
        Graph graph = mv.createGraph(7);
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
