package com.dsa.medium.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class BFSGraphTraversal {
    int vertices;
    LinkedList<Integer>[] adjList;

    public BFSGraphTraversal(int vertex){
        this.vertices = vertex;
        adjList = new LinkedList[vertex];
        for (int i=0; i < vertices; i++){
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source,int dest){
        //uni-directed graph
        adjList[source].addFirst(dest);
    }

    public void printGraph(){

        for (int i=0; i < vertices; i++){
            LinkedList<Integer> nodeList = adjList[i];
            if (!nodeList.isEmpty()){
                System.out.print("Source "+i+" connected to nodes ");
                for (int j=0; j < nodeList.size(); j++){
                    System.out.print(" "+nodeList.get(j));
                }
            }
            System.out.println();
        }
    }

    public void BFS(int sVertex){
        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(sVertex);

        while(!queue.isEmpty()){
            int currVertex = queue.poll();
            visited[currVertex] = true;
            System.out.print(currVertex+" ");

            //Get the neighbours of current vertex
            LinkedList<Integer> neighList = adjList[currVertex];
            for (int i=0; i < neighList.size(); i++){
                int neighbour = neighList.get(i);
                if (!visited[neighbour]){
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
        System.out.println();
    }

    public static void main(String args[]) {
        BFSGraphTraversal g = new BFSGraphTraversal(6);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        g.addEdge(3, 4);
        g.addEdge(2, 3);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(4, 5);
        g.printGraph();
        g.BFS(0);

    }
}
