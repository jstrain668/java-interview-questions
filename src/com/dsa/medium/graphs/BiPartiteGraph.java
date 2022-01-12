package com.dsa.medium.graphs;

//Question: https://leetcode.com/problems/is-graph-bipartite/

//Reference: https://massivealgorithms.blogspot.com/2018/04/leetcode-785-is-graph-bipartite.html

//Goal is trying to use two colors to color the graph and see if there are any adjacent nodes having the same color.
//Initialize a color[] array for each node. Here are three states for colors[] array:
//-1: Haven't been colored.
//0: Blue.
//1: Red.
//For each node,
//If it hasn't been colored, use a color to color it. Then use the other color to color all its adjacent nodes (DFS).
//If it has been colored, check if the current color is the same as the color that is going to be used to color it

public class BiPartiteGraph {

    public boolean isBipartite(int[][] graph) {

        if (graph == null || graph.length == 0){
            return true;
        }

        int n = graph.length;
        int[] colours = new int[n];
        for (int i=0; i < n; i++){
            colours[i] = -1;
        }

        for (int i=0; i < n; i++){
            if (colours[i] == -1 && !validColour(graph,colours,0,i)){
                return false;
            }
        }
        return true;
    }

    private boolean validColour(int[][] graph,int[] colours,int colour,int node){

        if (colours[node] != -1){
            return (colours[node] == colour);
        }
        colours[node] = colour;

        for (int next : graph[node]){
            if (!validColour(graph,colours,1-colour,next)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BiPartiteGraph bpg = new BiPartiteGraph();
        int[][] graph1 = {{1,2,3},{0,2},{0,1,3},{0,2}};

        System.out.println(bpg.isBipartite(graph1));

        int[][] graph2 = {{1,3},{0,2},{1,3},{0,2}};
        System.out.println(bpg.isBipartite(graph2));
    }
}
