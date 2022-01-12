package com.dsa.foundation.graphs.tree;

//Reference: https://www.programcreek.com/2014/05/graph-valid-tree-java/
//Reference: https://www.geeksforgeeks.org/check-given-graph-tree/

import java.util.LinkedList;

public class UDGraphTree {

    public boolean validTree(int n, int[][] edges) {
        if ( n <=0 || edges == null || edges.length == 0 || edges[0].length == 0){
            return true;
        }

        LinkedList<Integer>[] graph = new LinkedList[n];
        for (int i=0; i < n; i++){
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : edges){
            graph[edge[0]].addFirst(edge[1]);
            graph[edge[1]].addFirst(edge[0]);
        }

        boolean[] visited = new boolean[n];
        if (dfsCyclic(0,graph,visited,-1)){
            return false;
        }

        for (int i=0; i < n; i++){
            if (!visited[i]){
                return false;
            }
        }
        return true;
    }

    private boolean dfsCyclic(int cVertex,LinkedList<Integer>[] graph,boolean[] visited,int parent){
        //add this vertex to visited vertex
        visited[cVertex] = true;

        //visit neighbors except its direct parent
        for (int i = 0; i <graph[cVertex].size() ; i++) {
            int adjVertex = graph[cVertex].get(i);
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
                    if (dfsCyclic(adjVertex, graph,visited, cVertex)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        UDGraphTree gt = new UDGraphTree();
        int vertex = 5;
        int[][] grid = {{1,0},{0,2},{0,3},{3,4}};

        if(gt.validTree(vertex,grid)){
            System.out.println("Graph is a valid tree");
        } else {
            System.out.println("Graph is not a valid tree");
        }

        UDGraphTree gt1 = new UDGraphTree();
        int[][] grid1 = {{1,0},{0,2},{2,1},{0,3},{3,4}};

        if(gt1.validTree(vertex,grid1)){
            System.out.println("Graph is a valid tree");
        } else {
            System.out.println("Graph is not a valid tree");
        }

        UDGraphTree gt2 = new UDGraphTree();
        int[][] grid2 = {{0,1}};

        if(gt2.validTree(3,grid2)){
            System.out.println("Graph is a valid tree");
        } else {
            System.out.println("Graph is not a valid tree");
        }
    }
}
