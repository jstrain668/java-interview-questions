package com.dsa.medium.graphs;

public class NumberIslands {

    private void dfs(char[][] grid,int i, int j){

        int rowLength = grid.length;
        int colLength = grid[0].length;

        if (i < 0 || j < 0 || i > rowLength-1 || j > colLength-1 || grid[i][j] != '1')
        {
            return;
        }

        grid[i][j] = '0';

        dfs(grid,i+1,j);
        dfs(grid,i-1,j);
        dfs(grid,i,j+1);
        dfs(grid,i,j-1);
    }

    public int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        int islands = 0;

        int rows = grid.length;
        int cols = grid[0].length;

        for (int i=0; i < rows; i++){
            for (int j=0; j < cols; j++){
                if (grid[i][j] == '1'){
                    islands++;
                    dfs(grid,i,j);
                }
            }
        }

        return islands;
    }

    public static void main(String[] args) {
        NumberIslands ni = new NumberIslands();
        char[][] graph1 = {{'1', '1', '0', '0', '0'},
                           {'1', '1', '0', '0', '0'},
                           {'0', '0', '1', '0', '0'},
                           {'0', '0', '0', '1', '1'}};

        System.out.println(ni.numIslands(graph1));
    }
}
