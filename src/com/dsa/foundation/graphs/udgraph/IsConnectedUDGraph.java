package com.dsa.foundation.graphs.udgraph;

//Reference: https://algorithms.tutorialhorizon.com/check-if-given-undirected-graph-is-connected-or-not/

import java.util.LinkedList;

public class IsConnectedUDGraph {

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

        public void isConnected(){

            //created visited array
            boolean[] visited = new boolean[vertices];

            //start the DFS from vertex 0
            DFS(0, visited);

            //check if all the vertices are visited, if yes then graph is connected
            int count = 0;
            for (int i = 0; i <visited.length ; i++) {
                if(visited[i])
                    count++;
            }
            if(vertices==count){
                System.out.println("Given graph is connected");
            }else{
                System.out.println("Given graph is not connected");
            }
        }

        public void DFS(int source, boolean[] visited){

            //mark the vertex visited
            visited[source] = true;

            //travel the neighbors
            for (int i = 0; i <adjList[source].size() ; i++) {
                int adjVertex = adjList[source].get(i);
                if(!visited[adjVertex]){
                    //make recursive call from neighbor
                    DFS(adjVertex, visited);
                }
            }
        }

    }

    public Graph createGraph(int vertices){
        return new Graph(vertices);
    }

    public static void main(String[] args) {
        IsConnectedUDGraph isg = new IsConnectedUDGraph();
        Graph graph = isg.createGraph(5);
        graph.addEdge(0,1);
        graph.addEdge(1,3);
        graph.addEdge(3,2 );
        graph.isConnected();
        graph.addEdge(3, 4);
        graph.isConnected();
    }
}