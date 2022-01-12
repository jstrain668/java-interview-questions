package com.dsa.foundation.graphs.bipartite;

public class BiPartite {

    public boolean dfsVisit(int[][] graph, int node, int[] color)
    {
        //this is for the first node of the component
        if(color[node] == -1)
            color[node] = 1;

        for(int it: graph[node] )
        {
            // if adjacent node are not colored
            if(color[it] == -1)
            {
                // coloring with opposite color
                color[it] = 1 - color[node];
                // check same for every dfs call
                if(!dfsVisit(graph,it,color))
                    return false;

            }
            // return false if any adjacent node are colored with same node color
            else if(color[it] == color[node])
                return false;
        }
        //otherwise return true at end
        return true;
    }

    public boolean isBipartite(int[][] graph) {
        int[] color = new int [graph.length];
        // at start every nodes are not colored so -1
        for(int i=0; i<graph.length; i++) {
            color[i] = -1;
        }

        // check for all components of graph
        for(int i =0; i<graph.length; i++)
        {
            if(color[i] == -1)
            {
                if(!dfsVisit(graph,i,color))
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        BiPartite bpg = new BiPartite();
        int[][] graph1 = {{1,2,3},{0,2},{0,1,3},{0,2}};

        System.out.println(bpg.isBipartite(graph1));

        int[][] graph2 = {{1,3},{0,2},{1,3},{0,2}};
        System.out.println(bpg.isBipartite(graph2));
    }
}
