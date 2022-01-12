package com.dsa.foundation.graphs.udgraph;

// Objective: Given an undirected graph, Write an algorithm to determine whether its tree or not.
//An undirected graph is a tree if it has properties mentioned below
//
//1. There is no cycle present in the graph.
//2. The graph is connected. (All the vertices in the graph are connected)

//Reference: https://www.geeksforgeeks.org/check-given-graph-tree/
//Reference: https://algorithms.tutorialhorizon.com/check-if-given-undirected-graph-is-a-tree/

import java.util.LinkedList;

public class UDGraphATree {

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

        public void printGraph(){
            for (int i = 0; i <vertices ; i++) {
                LinkedList<Integer> nodeList = adjList[i];
                System.out.println("Vertex connected from vertex: "+i);

                for (int j = 0; j <nodeList.size() ; j++) {
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

        public void checkifTree(){

            printGraph();

            //If cycle is not present and graph is connected, its a tree

            //create visited array
            boolean[] visited = new boolean[vertices];

            //DFS for original graph start from first vertex
            boolean isCycle = isCycleUtil(0, visited, -1);

            if(isCycle){
                //graph is disconnected, its not tree
                System.out.println("Given Graph is not Tree");
                return;
            }

            //check the visited array to determine graph is connected or not
            for (int i = 0; i <vertices ; i++) {
                if(!visited[i]) {
                    System.out.println("Given Graph is not Tree");
                    return;
                }
            }
            //if here, means graph is tree
            System.out.println("Given Graph is Tree");
        }
    }

    public Graph createGraph(int vertices){
        return new Graph(vertices);
    }

    public static void main(String[] args) {
        UDGraphATree isTree = new UDGraphATree();
        Graph graph = isTree.createGraph(5);
        graph.addEdge(1,0);
        graph.addEdge(3,1);
        graph.addEdge(3,2);
        graph.addEdge(3,4);
        graph.checkifTree();  //Yes
        System.out.println("----------------------------");
        UDGraphATree isTree1 = new UDGraphATree();
        Graph graph1 = isTree1.createGraph(5);
        graph1.addEdge(1,0);
        graph1.addEdge(3,1);
        graph1.addEdge(3,2);
        graph1.addEdge(3,4);
        graph1.addEdge(4,0);
        graph1.checkifTree(); //No
        System.out.println("----------------------------");
        UDGraphATree isTree2 = new UDGraphATree();
        Graph graph2 = isTree2.createGraph(5);
        graph2.addEdge(1,0);
        graph2.addEdge(3,1);
        graph2.addEdge(3,2);
        graph2.checkifTree(); //No

        System.out.println("----------------------------");
        UDGraphATree isTree3 = new UDGraphATree();
        Graph graph3 = isTree3.createGraph(3);
        graph3.addEdge(0,1);
        graph3.checkifTree(); //No

        System.out.println("----------------------------");
        UDGraphATree isTree4 = new UDGraphATree();
        Graph graph4 = isTree4.createGraph(5);
        graph4.addEdge(1,0);
        graph4.addEdge(0, 2);
        graph4.addEdge(0, 3);
        graph4.addEdge(3, 4);
        graph4.checkifTree(); //Yes

        System.out.println("----------------------------");
        UDGraphATree isTree5 = new UDGraphATree();
        Graph graph5 = isTree5.createGraph(5);
        graph5.addEdge(1,0);
        graph5.addEdge(0, 2);
        graph5.addEdge(2, 1);
        graph5.addEdge(0, 3);
        graph5.addEdge(3, 4);
        graph5.checkifTree();//No

        System.out.println("----------------------------");
        UDGraphATree isTree6 = new UDGraphATree();
        Graph graph6 = isTree6.createGraph(2);
        graph6.addEdge(1,0);
        graph6.checkifTree();//Yes
    }
}
