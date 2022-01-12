package com.dsa.medium.graphs;

import java.util.LinkedList;
import java.util.Queue;

//Question: https://leetcode.com/problems/find-if-path-exists-in-graph/

public class HasPath {

    public boolean validPath(int n, int[][] edges, int start, int end) {

        if (n <= 0){
            return false;
        }

        if (n > 0 && edges == null || edges.length == 0){
            return true;
        }

        if (start == end){
            return true;
        }

        //Create and populate adjacency list
        LinkedList<Integer>[] adjList = new LinkedList[n];
        for (int i=0; i < n; i++){
            adjList[i] = new LinkedList<>();
        }

        for (int[] edge : edges){
            adjList[edge[0]].addFirst(edge[1]);
            adjList[edge[1]].addFirst(edge[0]);
        }

        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;

        while (!queue.isEmpty()){
            int cVertex = queue.poll();

            for (int i=0; i < adjList[cVertex].size(); i++){
                int adjVertex = adjList[cVertex].get(i);

                if (adjVertex == end){
                    return true;
                }

                if (!visited[adjVertex]){
                    visited[adjVertex] = true;
                    queue.add(adjVertex);
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        HasPath hp = new HasPath();
        int n= 3;
        int[][] edges = {{0,1},{1,2},{2,0}};

        System.out.println("Has path "+hp.validPath(n,edges,0,2));

        n= 6;
        int[][] edges1 = {{0,1},{0,2},{3,5},{5,4},{4,3}};

        System.out.println("Has path "+hp.validPath(n,edges1,0,5));

        n= 1;
        int[][] edges2 = {{}};

        System.out.println("Has path "+hp.validPath(n,edges2,0,0));

    }
}
