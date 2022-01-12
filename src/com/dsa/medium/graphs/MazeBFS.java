package com.dsa.medium.graphs;

import java.util.Arrays;


//Reference: https://github.com/cherryljr/LeetCode/blob/master/The%20Maze.java
/**
 * Approach 2: BFS
 * Algorithm
 * The same search space tree can also be explored in a Depth First Search manner.
 * In this case, we try to explore the search space on a level by level basis.
 * i.e. We try to move in all the directions at every step.
 * When all the directions have been explored and we still don't reach the destination,
 * then only we proceed to the new set of traversals from the new positions obtained.
 *
 * In order to implement this, we make use of a queue.
 * We start with the ball at the start position.
 * For every current position, we add all the new positions possible by traversing in all the four directions(till reaching the wall or boundary) into
 * the queue to act as the new start positions and mark these positions as True in the visited array.
 * When all the directions have been covered up, we remove a position value, position,
 * from the front of the queue and again continue the same process with s acting as the new start position.
 *
 * Further, in order to choose the direction of travel, we make use of a dir array,
 * which contains 4 entries. Each entry represents a one-dimensional direction of travel.
 * To travel in a particular direction, we keep on adding the particular entry of the dirs array till we hit a wall or a boundary.
 * For a particular start position, we do this process of dir addition for all all the four directions possible.
 * If we hit the destination position at any moment, we return a True directly.
 *
 * Complexity Analysis
 * Time complexity : O(mn).
 * Complete traversal of maze will be done in the worst case. Here, m and n refers to the number of rows and columns of the maze.
 * Space complexity :O(mn).
 * visited array of size m*n is used and queue size can grow upto m*n in worst case.
 */

public class MazeBFS {

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        return dfs(maze, start, destination, visited);
    }

    public boolean dfs(int[][] maze, int[] position, int[] destination, boolean[][] visited) {
        if (visited[position[0]][position[1]]) {
            return false;
        }
        if (position[0] == destination[0] && position[1] == destination[1]) {
            return true;
        }
        // mark the point has been visited
        visited[position[0]][position[1]] = true;

        // Learn form BFS's code, we can write the code more concise
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        for (int[] dir : dirs) {
            int x = position[0];
            int y = position[1];
            while (x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
            }
            // Roll Back - When the program break from while loop above,
            // it meas that x, y has been added dir[0], dir[1] one more time.
            // But the correct answer (in the range) is less than it, so we should minus dir[0] and dir[1] here.
            if (dfs(maze, new int[]{x - dir[0], y - dir[1]}, destination, visited)) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        MazeBFS mb = new MazeBFS();
        int[][] maze = {{0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}};
        int[] start = {0,4};
        int[] destination = {3,2};
        System.out.println("Is there a path between "+ Arrays.toString(start)+ " and "+Arrays.toString(destination)+" "+mb.hasPath(maze,start,destination));

        int[][] maze1 = {{0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}};
        int[] start1 = {0,4};
        int[] destination1 = {4,4};
        System.out.println("Is there a path between "+ Arrays.toString(start1)+ " and "+Arrays.toString(destination1)+" "+mb.hasPath(maze1,start1,destination1));
    }

}
