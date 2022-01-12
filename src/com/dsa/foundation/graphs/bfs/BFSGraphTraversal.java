package com.dsa.foundation.graphs.bfs;

import java.util.LinkedList;
import java.util.Queue;

public class BFSGraphTraversal {

    int vertex;
    LinkedList<Integer>[] list;

    public BFSGraphTraversal(int vertex){
        this.vertex = vertex;
        list = new LinkedList[vertex];
        for (int i=0; i < vertex; i++){
            list[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source,int dest){
        //uni-directed graph
        list[source].addFirst(dest);
    }

    public void printGraph(){

        for (int i=0; i < vertex; i++){
            LinkedList<Integer> nodeList = list[i];
            if (!nodeList.isEmpty()){
                System.out.print("Source "+i+" connected to nodes ");
                for (int j=0; j < nodeList.size(); j++){
                    System.out.print(" "+nodeList.get(j));
                }
            }
            System.out.println();
        }
    }

    public void BFS(int startVertex){

        boolean[] visited = new boolean[vertex];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);
        visited[startVertex] = true;

        while (!queue.isEmpty()){
            int vertexToVisit = queue.poll();
            //visited[vertexToVisit] = true;
            System.out.print(+vertexToVisit+" ");
            LinkedList<Integer> nodeList = list[vertexToVisit];

            for (int i=0; i < nodeList.size(); i++){
                int dest = nodeList.get(i);
                if (!visited[dest]){
                    queue.add(dest);
                    visited[dest] = true;
                }
            }
        }
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
