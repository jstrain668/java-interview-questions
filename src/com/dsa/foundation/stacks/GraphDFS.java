package com.dsa.foundation.stacks;

import java.util.LinkedList;
import java.util.Stack;

public class GraphDFS {

    public class Graph{
        int vertices;
        LinkedList<Integer>[] adjList;

        public Graph(int vertices){
            this.vertices = vertices;
            adjList = new LinkedList[vertices];
            for (int i=0; i < vertices; i++){
                adjList[i] = new LinkedList<>();
            }
        }

        public void addEdge(int source, int destination){

            //Directed graph
            adjList[source].addFirst(destination);

        }

        public void printGraph(){

            for (int i=0; i < vertices; i++){
                LinkedList<Integer> nodeList = adjList[i];
                System.out.print("Vertex "+i+" connected to ");
                for (int j=0; j < nodeList.size(); j++){
                    System.out.print(nodeList.get(j)+" ");
                }
                System.out.println();
            }
        }

        public void DFS(){
            boolean[] visited = new boolean[vertices];
            Stack<Integer> stack = new Stack<>();

            for (int i=0; i < vertices; i++){
                if (!visited[i]){
                    stack.push(i);
                    visited[i] = true;
                    while (!stack.isEmpty()){
                        int index = stack.pop();
                        LinkedList<Integer> nodeList = adjList[index];
                        System.out.print(index+" ");
                        for (int j=0; j < nodeList.size(); j++){
                            int dest = nodeList.get(j);
                            if (!visited[dest]){
                                stack.push(dest);
                                visited[dest] = true;
                            }
                        }
                    }
                }
            }
            System.out.println();
        }

        public void dfs(int start,boolean[] visited){
            visited[start] = true;
            System.out.print(start+" ");

            for (int i=0; i < adjList[start].size(); i++){
                int dest = adjList[start].get(i);
                if(!visited[dest]){
                    visited[dest] = true;
                    dfs(dest,visited);
                }
            }
        }

        public void dfsRecursion(int startVertex){
            boolean[] visited = new boolean[vertices];
            dfs(startVertex,visited);
        }
    }

    public Graph createGraph(int vertices){
        return new Graph(vertices);
    }

    public static void main(String[] args) {
        GraphDFS gDFS = new GraphDFS();
        Graph graph = gDFS.createGraph(6);
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
        graph.dfsRecursion(0);
    }
}
