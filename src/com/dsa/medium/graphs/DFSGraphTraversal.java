package com.dsa.medium.graphs;

import java.util.LinkedList;
import java.util.Stack;

public class DFSGraphTraversal {

    int vertices;
    LinkedList<Integer> adjList[];

    public DFSGraphTraversal(int vertex) {
        this.vertices = vertex;
        adjList = new LinkedList[vertex];
        for (int i = 0; i <vertex ; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int source, int destination){

        //add forward edge
        adjList[source].addFirst(destination);
    }

    public void DFS(){

        boolean[] visited = new boolean[vertices];
        Stack<Integer> stack = new Stack<>();

        for (int i=0; i < vertices; i++){
            if (!visited[i]){
                visited[i] = true;
                stack.push(i);

                while (!stack.isEmpty()){
                    int nodeIndex = stack.pop();
                    LinkedList<Integer> nodeList = adjList[nodeIndex];
                    System.out.print(nodeIndex + " ");

                    for (int j = 0; j < nodeList.size(); j++){
                        int dest = nodeList.get(j);
                        if (!visited[dest]){
                            visited[dest] = true;
                            stack.push(dest);
                        }
                    }
                }
            }
        }
        System.out.println();
    }

    public void DFS(int vertex,boolean[] visited){
        visited[vertex] = true;

        System.out.print(vertex+" ");
        for (int i=0; i < adjList[vertex].size(); i++){
            int destVertex = adjList[vertex].get(i);
            if (!visited[destVertex]){
                DFS(destVertex,visited);
            }
        }
    }

    public void DFSRecursion(int vertex){
        boolean[] visited = new boolean[vertices];
        DFS(vertex,visited);
    }

    public void printGraph(){
        for (int i = 0; i <vertices ; i++) {
            LinkedList<Integer> nodeList = adjList[i];
            if(!nodeList.isEmpty()) {
                System.out.print("source = " + i + " is connected to nodes: ");
                for (int j = 0; j < nodeList.size(); j++) {
                    System.out.print(" " + nodeList.get(j));
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        DFSGraphTraversal graph = new DFSGraphTraversal(6);
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
        graph.DFS();
        graph.DFSRecursion(0);
    }
}
