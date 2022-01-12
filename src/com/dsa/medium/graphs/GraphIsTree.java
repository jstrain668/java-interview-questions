package com.dsa.medium.graphs;


import java.util.LinkedList;


// Objective: Given an undirected graph, Write an algorithm to determine whether its tree or not.
//An undirected graph is a tree if it has properties mentioned below
//
//1. There is no cycle present in the graph.
//2. The graph is connected. (All the vertices in the graph are connected)

//Reference: https://www.geeksforgeeks.org/check-given-graph-tree/
//Reference: https://algorithms.tutorialhorizon.com/check-if-given-undirected-graph-is-a-tree/

//Question: https://www.lintcode.com/problem/178/

public class GraphIsTree {

    private boolean isCycle(int cVertex,LinkedList<Integer>[] adjList,boolean[] visited,int parent){

        visited[cVertex] = true;

        for (int i=0; i < adjList[cVertex].size(); i++){
            int adjVertex = adjList[cVertex].get(i);

            if (parent != adjVertex){
                if (visited[adjVertex]){
                    return true;
                } else{
                    if (isCycle(adjVertex,adjList,visited,cVertex)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean validTree(int n,int[][] edges){

        if ( n == 1 && (edges == null || edges.length == 0 || edges[0].length == 0)){
            return true;
        }

        if ( n > 1 && (edges == null || edges.length == 0 || edges[0].length == 0)){
            return false;
        }

        LinkedList<Integer>[] adjList = new LinkedList[n];

        for (int i=0; i < n; i++){
            adjList[i] = new LinkedList<>();
        }

        for (int[] edge : edges){
            adjList[edge[0]].addFirst(edge[1]);
            adjList[edge[1]].addFirst(edge[0]);
        }

        //Check for a cycle
        boolean[] visited = new boolean[n];
        if (isCycle(0,adjList,visited,-1)){
            return false;
        }

        for (boolean val : visited){
            if (!val){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        GraphIsTree git = new GraphIsTree();
        int vertex = 5;
        int[][] grid = {{0,1},{0,2},{0,3},{1,4}};  //Yes

        if(git.validTree(vertex,grid)){
            System.out.println("Graph is a valid tree");
        } else {
            System.out.println("Graph is not a valid tree");
        }

        GraphIsTree git1 = new GraphIsTree();
        int[][] grid1 = {{0,1},{1,2},{2,3},{1,3},{1,4}};  //No

        if(git1.validTree(vertex,grid1)){
            System.out.println("Graph is a valid tree");
        } else {
            System.out.println("Graph is not a valid tree");
        }

        GraphIsTree git2 = new GraphIsTree();
        int[][] grid2 = {{1,0}}; //Yes

        if(git2.validTree(2,grid2)){
            System.out.println("Graph is a valid tree");
        } else {
            System.out.println("Graph is not a valid tree");
        }
    }
}
