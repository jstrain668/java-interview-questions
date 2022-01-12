package com.dsa.medium.graphs;

//Reference: https://www.geeksforgeeks.org/connected-components-in-an-undirected-graph/
//Reference: https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/323-number-of-connected-components-in-an-undirected-graph.html

//Question: Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write
// a function to find the number of connected components in an undirected graph.

//Solution Approach: DFS visits all connected vertices of the given vertex.
// When iterating over all vertices, whenever we see unvisited node, it is because it was not visited by DFS done on
// vertices so far. That means it is not connected to any previous nodes visited so far i.e it was not part of previous
// components. Hence this node belongs to new component.this means, before visiting this node, we just finished visiting
// all nodes  previous component and that component is now complete.so we need to increment component counter as we
// completed  a component
//
//The idea is to use a variable count to store the number of connected components and do the following steps:
//Initialize all vertices as unvisited.
//For all the vertices check if a vertex has not been visited, then perform DFS on that vertex and increment the
// variable count by 1.

//Also see https://www.geeksforgeeks.org/connected-components-in-an-undirected-graph/

import java.util.*;

public class NumberConnectedComponents {

    public void init(Map<Integer,List<Integer>> adj,int[][] edges, int n){

        for (int i=0; i < n; i++){
            adj.put(i,new ArrayList<>());
        }

        for (int[] edge : edges){
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
    }

    public int countComponents(int n, int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        init(adj, edges, n);
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i ++) {
            if (!visited[i]) {
                count ++;
                dfs(adj, i, visited);
            }
        }
        return count;
    }

    public void dfs(Map<Integer,List<Integer>> adj,int cVertex,boolean[] visited){
        visited[cVertex] = true;

        for (int i=0; i < adj.get(cVertex).size(); i++){
            int adjVertex = adj.get(cVertex).get(i);
            if (!visited[adjVertex]){
                dfs(adj,adjVertex,visited);
            }
        }
    }

    public List<List<Integer>> getConnectedComponents(int n,int[][] edges){

        LinkedList<Integer>[] adjList = new LinkedList[n];
        for (int i=0; i < n; i++){
            adjList[i] = new LinkedList<>();
        }

        for (int[] edge : edges){
            adjList[edge[0]].addFirst(edge[1]);
            adjList[edge[1]].addFirst(edge[0]);
        }

        boolean[] visited = new boolean[n];
        List<List<Integer>> conLists = new ArrayList<>();
        for (int i=0; i < n; i++){
            if (!visited[i]){
                List<Integer> conList = new ArrayList<>();
                dfs(i,adjList,visited,conList);
                conLists.add(conList);
            }
        }
        return conLists;
    }

    private void dfs(int cVertex,LinkedList<Integer>[] adjList,boolean[] visited,List<Integer> conList){

        visited[cVertex] = true;
        conList.add(cVertex);

        for (int i=0; i < adjList[cVertex].size(); i++){
            int adjVertex = adjList[cVertex].get(i);

            if (!visited[adjVertex]){
                dfs(adjVertex,adjList,visited,conList);
            }
        }
    }

    public static void main(String[] args) {
        NumberConnectedComponents ncc = new NumberConnectedComponents();
        int n = 5;
        int[][] edges = {{0,1},{1,2},{3,4}};
        System.out.println("Graph DFS:");
        int count = ncc.countComponents(n,edges);
        System.out.println("Number of Connected Components: "+ count);

        NumberConnectedComponents ncc1 = new NumberConnectedComponents();
        List<List<Integer>> lists = ncc1.getConnectedComponents(n,edges);

        System.out.println("Lists of connected components");
        for (List<Integer> list : lists){
            System.out.println(list);
        }

        NumberConnectedComponents ncc2 = new NumberConnectedComponents();
        int[][] edges1 = {{1,0},{2,3},{3,4}};
        System.out.println("Graph DFS:");
        int count1 = ncc2.countComponents(n,edges1);
        System.out.println("Number of Connected Components: "+ count1);

        NumberConnectedComponents ncc3 = new NumberConnectedComponents();
        List<List<Integer>> lists1 = ncc3.getConnectedComponents(n,edges1);

        System.out.println("Lists of connected components");
        for (List<Integer> list : lists1){
            System.out.println(list);
        }

    }
}
