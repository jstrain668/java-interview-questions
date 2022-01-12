package com.dsa.medium.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//Reference: https://www.programcreek.com/2014/05/graph-valid-tree-java/
//Reference: https://www.geeksforgeeks.org/check-given-graph-tree/

//Question: https://www.lintcode.com/problem/178/

public class ValidUDGraph {

    public boolean validTree(int n, int[][] edges) {
        if ( n == 1 && (edges == null || edges.length == 0 || edges[0].length == 0)){
            return true;
        }

        if ( n > 1 && (edges == null || edges.length == 0 || edges[0].length == 0)){
            return false;
        }

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();

        for (int i=0; i < n; i++){
            map.put(i,new ArrayList<>());
        }

        for (int[] edge : edges){
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];

        if(cycle(0,map,visited,-1)) {
            return false;
        }

        for (boolean val: visited){
            if (!val){
                return false;
            }
        }
        return true;
    }

    public boolean cycle(int cVertex,Map<Integer,ArrayList<Integer>> map,boolean[] visited,int parent){

        visited[cVertex] = true;

        for (int i=0; i < map.get(cVertex).size(); i++){
            int adjVertex = map.get(cVertex).get(i);

            if (adjVertex != parent){
                if (visited[adjVertex]){
                    return true;
                } else {
                    if (cycle(adjVertex,map,visited,cVertex)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ValidUDGraph vud = new ValidUDGraph();
        int vertex = 5;
        int[][] grid = {{0,1},{0,2},{0,3},{1,4}};  //Yes

        if(vud.validTree(vertex,grid)){
            System.out.println("Graph is a valid tree");
        } else {
            System.out.println("Graph is not a valid tree");
        }

        ValidUDGraph vud1 = new ValidUDGraph();
        int[][] grid1 = {{0,1},{1,2},{2,3},{1,3},{1,4}};  //No

        if(vud1.validTree(vertex,grid1)){
            System.out.println("Graph is a valid tree");
        } else {
            System.out.println("Graph is not a valid tree");
        }

        ValidUDGraph vud2 = new ValidUDGraph();
        int[][] grid2 = {{1,0}};  //Yes

        if(vud2.validTree(2,grid2)){
            System.out.println("Graph is a valid tree");
        } else {
            System.out.println("Graph is not a valid tree");
        }

        ValidUDGraph vud3 = new ValidUDGraph();
        int[][] grid3 = {{0,1}};  //No

        if(vud3.validTree(3,grid3)){
            System.out.println("Graph is a valid tree");
        } else {
            System.out.println("Graph is not a valid tree");
        }
    }
}
