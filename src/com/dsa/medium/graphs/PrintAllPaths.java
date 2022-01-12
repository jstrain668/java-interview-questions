package com.dsa.medium.graphs;


import java.util.LinkedList;

//Question and Reference: https://algorithms.tutorialhorizon.com/graph-print-all-paths-between-source-and-destination/

public class PrintAllPaths {

    private void dfsPrint(int start, int end,LinkedList<Integer>[] adjList,boolean[] visited,String path){
        String newPath = path +  "->" + start;
        visited[start] = true;

        for (int i=0; i < adjList[start].size(); i++){
            int adjVertex = adjList[start].get(i);

            if (adjVertex != end && !visited[adjVertex]){
                dfsPrint(adjVertex,end,adjList,visited,newPath);
            } else if (adjVertex == end){
                System.out.println(newPath+ "->"+adjVertex);
            }
        }
        visited[start] = false;

    }

    public void printAllPaths(int n, int[][] edges, int start, int end){

        //Create Adjacency list from edges
        LinkedList<Integer>[] adjList = new LinkedList[n];
        for (int i=0; i < n; i++){
            adjList[i] = new LinkedList<>();
        }

        //Directed graph
        for (int[] edge : edges){
            adjList[edge[0]].addFirst(edge[1]);
        }


        boolean[] visited = new boolean[n];
        dfsPrint(start,end,adjList,visited,"");

    }

    public static void main(String[] args) {
        PrintAllPaths pap = new PrintAllPaths();
        int n = 6;
        int[][] edges = {{0,1},{0,2},{1,2},{1,3},{3,4},{2,3},{4,0},{4,1},{4,5}};

        int start = 0, end = 5;
        System.out.println("Following are all different paths from " + start + " to " + end);
        pap.printAllPaths(n,edges,start,end);

        n = 4;
        int[][] edges1 = {{0,1},{0,2},{0,3},{2,0},{2,1},{1,3}};
        start = 2;
        end = 3;
        System.out.println("Following are all different paths from " + start + " to " + end);
        pap.printAllPaths(n,edges1,start,end);


    }
}
