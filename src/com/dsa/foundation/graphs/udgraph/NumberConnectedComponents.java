package com.dsa.foundation.graphs.udgraph;

import java.util.LinkedList;

public class NumberConnectedComponents {

    public int countComponents(int n, int[][] edges) {
        if (n <= 0 || edges == null || edges.length == 0 || edges[0].length == 0){
            return 0;
        }

        //Adjacency list
        LinkedList<Integer>[] graph = new LinkedList[n];
        for (int i=0; i < n; i++){
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : edges){
            graph[edge[0]].addFirst(edge[1]);
            graph[edge[1]].addFirst(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int count = 0;

        for (int i=0; i < n; i++){
            if (!visited[i]){
                count++;
                dfs(i,graph,visited);
            }
        }
        return count;
    }

    private void dfs(int cVertex,LinkedList<Integer>[] graph,boolean[] visited){
        visited[cVertex] = true;

        for (int i=0; i < graph[cVertex].size(); i++){
            int adjVertex = graph[cVertex].get(i);
            if (!visited[adjVertex]){
                dfs(adjVertex,graph,visited);
            }
        }
    }

    //Undirected graph
    public void addEdge(int[][] edges,int src, int dest){

        if ((src < 0) || (src >= edges.length) || (dest < 0) || (dest >= edges[0].length) || (src >= edges[0].length) || (dest >= edges.length))
            return;

        edges[src][dest] = 1;
    }

    public static void main(String[] args) {
        NumberConnectedComponents ncc = new NumberConnectedComponents();
        int n = 5;
        int[][] edges = {{0,1},{1,2},{3,4}};
        //ncc.addEdge(edges,0, 1);
        //ncc.addEdge(edges,1, 2);
        //ncc.addEdge(edges,3, 4);
        System.out.println("Graph DFS:");
        int count = ncc.countComponents(n,edges);
        System.out.println(
                "\nNumber of Connected Components: "
                        + count);

        NumberConnectedComponents ncc1 = new NumberConnectedComponents();
        int[][] edges1 = {{1,0},{2,3},{3,4}};
        //ncc1.addEdge(edges1,1, 0);
        //ncc1.addEdge(edges1,2, 3);
        //ncc1.addEdge(edges1,3, 4);
        System.out.println("Graph DFS:");
        int count1 = ncc.countComponents(n,edges1);
        System.out.println(
                "\nNumber of Connected Components: "
                        + count1);

    }
}
