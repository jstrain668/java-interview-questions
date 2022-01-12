package com.dsa.foundation.graphs.bfs;


//Question: https://leetcode.com/problems/rotting-oranges/
//Reference: https://github.com/cherryljr/LeetCode/blob/master/Rotting%20Oranges.java

import java.util.LinkedList;
import java.util.Queue;

public class RottingOranges {

    public int orangesRotting(int[][] grid) {

        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        for (int i=0; i < rows; i++){
            for (int j=0; j < cols; j++){
                if (grid[i][j] == 2){
                    queue.add(new int[] {i,j});
                } else if (grid[i][j] == 1){
                    freshCount++;
                }
            }
        }

        if (freshCount == 0){
            return 0;
        }

        int step = 0;
        int[][] dirs = {{-1,0},{0,-1},{0,1},{1,0}};

        while (!queue.isEmpty()){
            step++;
            int size = queue.size();

            for (int i=0; i < size; i++){
                int[] curr = queue.poll();
                for (int dir[] : dirs){
                    int x = curr[0] + curr[0];
                    int y = curr[1] + curr[1];

                    if (x < 0 || x >= rows || y < 0 || y >= cols || grid[x][y] == 0 || grid[x][y] == 2){
                        continue;
                    }

                    grid[x][y] = 2;
                    queue.offer(new int[]{x,y});
                    freshCount--;

                }
            }
        }
        return (freshCount == 0) ? step -1 : -1;
    }

    public static void main(String[] args) {
        RottingOranges ro = new RottingOranges();
        int[][] grid1 = {{2,1,1},
                         {1,1,0},
                         {0,1,1}};

        int[][] grid2 = {{0,2}};

        int[][] grid3 = {{2,1,1},
                         {0,1,1},
                         {1,0,1}};

        System.out.println(ro.orangesRotting(grid1));
        System.out.println(ro.orangesRotting(grid2));
        System.out.println(ro.orangesRotting(grid3));
    }

}
