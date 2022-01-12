package com.dsa.foundation.graphs.dgraph;

import java.util.LinkedList;

//There are 3 properties to check if a graph is a tree:
//
//(1) The number of edges in the graph is exactly one less than the number of vertices |E| = |V| - 1
//(2) There are no cycles
//(3) The graph is connected

public class DGraphATree {

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

        public int edgeCount(){

            int sum = 0;

            for (int i=0; i < vertices; i++){
                sum += adjList[i].size();
            }

            return sum;
        }

        public boolean isCycleUtil(int vertex,boolean[] visited,boolean[] recurArr){
            visited[vertex] = true;
            recurArr[vertex] = true;

            for (int i=0; i < adjList[vertex].size(); i++){
                int adjVertex = adjList[vertex].get(i);
                if (!visited[adjVertex] && isCycleUtil(adjVertex,visited,recurArr)){
                    return true;
                } else if (recurArr[adjVertex]){
                    return true;
                }
            }
            recurArr[vertex] = false;
            return false;
        }

        public boolean isCycle(){
            boolean[] visited = new boolean[vertices];
            boolean[] recurArr = new boolean[vertices];

            for (int i=0; i < vertices; i++){
                if (isCycleUtil(i,visited,recurArr)){
                    return true;
                }
            }
            return false;
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

        public boolean isConnected(){
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

            return  (count == vertices);
        }

        public boolean isTree(){

            if (edgeCount() != vertices -1){
                return false;
            }

            if (isCycle()){
                return false;
            }

            if (!isConnected()){
                return false;
            }
            return true;
        }
    }

    public Graph createGraph(int vertices){
        return new Graph(vertices);
    }

    public static void main(String[] args) {
        DGraphATree dgat = new DGraphATree();
        Graph graph = dgat.createGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        //graph.addEdge(3, 2);
        if (graph.isTree()){
            System.out.println("Directed graph is a tree");
        } else{
            System.out.println("Directed graph is not a tree");
        }
    }

}
