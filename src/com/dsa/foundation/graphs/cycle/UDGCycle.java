package com.dsa.foundation.graphs.cycle;

import java.util.LinkedList;

//Reference: https://algorithms.tutorialhorizon.com/graph-detect-cycle-in-undirected-graph-using-dfs/

//Approach to find cycles in undirected graphs
//
//This problem can be solved in multiple ways, like topological sort, DFS, disjoint sets, in this article we will see
//this simplest among all, using DFS.
//
//Do DFS from every vertex.
//During DFS, for any current vertex ‘x’ (currently visiting vertex) if there an adjacent vertex ‘y’ is present which is
//already visited and ‘y’ is not a direct parent of ‘x’ then there is a cycle in graph.
//Why not parent:
//Let’s assume, vertex ‘x’ and ‘y’ and we have edge between them. x—-y.
//Now do DFS from ‘x’, once you reach to ‘y’, will do the DFS from ‘y’ and adjacent vertex is ‘x’ and since its already
//visited so there should be cycle but actually there is no cycle since ‘x’ is a parent of ‘y’. That is why we will
//ignore visited vertex if it is parent of current vertex.

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

        boolean isCycleUtil(int currVertex, boolean [] visited, int parent){

            //add this vertex to visited vertex
            visited[currVertex] = true;

            //visit neighbors except its direct parent
            for (int i = 0; i <adjList[currVertex].size() ; i++) {
                int adjVertex = adjList[currVertex].get(i);
                //check the adjacent vertex from current vertex
                if(adjVertex!=parent) {
                    //if destination vertex is not its direct parent then
                    if(visited[adjVertex]){
                        //if here means this destination vertex is already visited
                        //means cycle has been detected
                        return true;
                    }
                    else{
                        //recursion from destination node
                        if (isCycleUtil(adjVertex, visited, currVertex)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        public boolean isCycle() {
            //visited array
            boolean[] visited = new boolean[vertices];

            //do DFS, from each vertex
            for (int i = 0; i <vertices ; i++) {
                if(!visited[i]){
                    if(isCycleUtil(i, visited, -1)){
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
