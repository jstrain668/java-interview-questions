package com.dsa.medium.graphs;

import java.util.Arrays;

/**
 * Approach 1: DFS (Time Limit Exceeded)
 * Algorithm
 * We can view the given search space in the form of a tree.
 * The root node of the tree represents the starting position.
 * Four different routes are possible from each position
 * i.e. left, right, up or down. These four options can be represented by 4 branches of each node in the given tree.
 * Thus, the new node reached from the root traversing over the branch represents the new position occupied by the ball
 * after choosing the corresponding direction of travel.
 * In order to do this traversal, one of the simplest schemes is to undergo depth first search.
 * In this case, we choose one path at a time and try to go as deep as possible into the levels of the tree before going for the next path.
 * In order to implement this, we make use of a recursive function dfs(maze, start, destination, visited).
 * This function takes the given maze array, the start position and the destination position as its arguments along with a visited array.
 * visited array is a 2-D boolean array of the same size as that of maze.
 * A True value at visited[i][j] represents that the current position has already been reached earlier during the path traversal.
 * We make use of this array so as to keep track of the same paths being repeated over and over.
 * We mark a True at the current position in the visited array once we reach that particular position in the maze.
 *
 * From every start position, we can move continuously in either left, right, upward or downward direction till we reach the boundary or a wall.
 * Thus, from the start position, we determine all the end points which can be reached by choosing the four directions.
 * For each of the cases, the new endpoint will now act as the new start point for the traversals.
 * The destination, obviously remains unchanged. Thus, now we call the same function four times for the four directions,
 * each time with a new start point obtained previously.
 * If any of the function call returns a True value, it means we can reach the destination.
 *
 * Complexity Analysis
 *  Time complexity : O(mn).
 *  Complete traversal of maze will be done in the worst case. Here, m and n refers to the number of rows and columns of the maze.
 *  Space complexity : O(mn).
 *  visited array of size m*n is used.
 *
 * You can get more detail explanations here:
 * https://leetcode.com/articles/the-maze/
 */



public class MazeDFS {
    /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: whether the ball could stop at the destination
     */
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
        MazeDFS md = new MazeDFS();
        int[][] maze = {{0,0,1,0,0},
                        {0,0,0,0,0},
                        {0,0,0,1,0},
                        {1,1,0,1,1},
                        {0,0,0,0,0}};
        int[] start = {0,4};
        int[] destination = {3,2};
        System.out.println("Is there a path between "+ Arrays.toString(start)+ " and "+Arrays.toString(destination)+" "+md.hasPath(maze,start,destination));

        int[][] maze1 = {{0,0,1,0,0},
                {0,0,0,0,0},
                {0,0,0,1,0},
                {1,1,0,1,1},
                {0,0,0,0,0}};
        int[] start1 = {0,4};
        int[] destination1 = {4,4};
        System.out.println("Is there a path between "+ Arrays.toString(start1)+ " and "+Arrays.toString(destination1)+" "+md.hasPath(maze1,start1,destination1));
    }
}
