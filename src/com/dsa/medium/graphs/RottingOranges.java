package com.dsa.medium.graphs;

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

    public int orangesRotting(int[][] grid) {

        if (grid == null || grid.length == 0){
            return 0;
        }
        int freshCount = 0;
        Queue<int[]> queue = new LinkedList<>();
        int rows = grid.length;
        int cols = grid[0].length;

        for (int i=0; i < rows; i++){
            for (int j=0; j < cols; j++){
                if (grid[i][j] == 1){
                    freshCount++;
                } else if (grid[i][j] == 2){
                    queue.add(new int[]{i,j});
                }
            }
        }

        if (freshCount == 0){
            return 0;
        }

        //Number of iterations for spreading rotting oranges!
        int count = 0;
        //Array of valid moves for spreading rotting oranges!
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        //bfs starting from initial rotten oranges
        while (!queue.isEmpty()){
            count++;
            int size = queue.size();
            for (int i=0; i < size; i++){
                int[] curr = queue.poll();

                //Spread from the rotting orange per dirs directions
                for (int[] dir : dirs){
                    int nextX = curr[0] + dir[0];
                    int nextY = curr[1] + dir[1];

                    // Is it a valid move?
                    // if x or y is out of bound
                    // or the orange at (x , y) is already rotten
                    // or the cell at (x , y) is empty
                    // we do nothing
                    if (nextX <0 || nextX >= rows || nextY < 0 || nextY >= cols || grid[nextX][nextY] == 2 || grid[nextX][nextY] == 0){
                        continue;
                    }

                    //Mark the fresh orange as rotten
                    grid[nextX][nextY] = 2;
                    //Reduce the fresh count
                    freshCount--;
                    //Add new rotten orange to the queue to spread to more fresh oranges
                    queue.add(new int[] {nextX,nextY});

                }
            }
        }
        return (freshCount == 0) ? count-1 : -1;
    }

    public static void main(String[] args) {
        RottingOranges ro = new  RottingOranges();
        int[][] grid1 = {{2,1,1},
                {1,1,0},
                {0,1,1}};

        int[][] grid2 = {{0,2}};

        int[][] grid3 = {{2,1,1},
                {0,1,1},
                {1,0,1}};

        int[][] grid4 = {{2,1,1},{1,1,1},{0,1,2}};


        System.out.println(ro.orangesRotting(grid1));
        System.out.println(ro.orangesRotting(grid2));
        System.out.println(ro.orangesRotting(grid3));
        System.out.println(ro.orangesRotting(grid4));
    }
}
