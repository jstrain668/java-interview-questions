package com.dsa.medium.graphs;

//Question: https://www.programmerall.com/article/9758246904/
//Reference: https://github.com/awangdev/LintCode/blob/master/Java/1197.%20Minimum%20Knight%20Moves.java
//Reference: http://lixinchengdu.github.io/algorithmbook/leetcode/minimum-knight-moves.html

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].
A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.
Return the minimum number of steps needed to move the knight to the square [x, y].  It is guaranteed the answer exists.

Example 1:
Input: x = 2, y = 1
Output: 1
Explanation: [0, 0] → [2, 1]
Example 2:
Input: x = 5, y = 5
Output: 4
Explanation: [0, 0] → [2, 1] → [4, 2] → [3, 4] → [5, 5]

Constraints:
|x| + |y| <= 300
*/

/*
- bfs: minimum steps, enumerate the possible moves
    - move closer to x or y (test 8 possible directions)
    - add possible moves in queue
*/


public class KnightMoves {

    int[][] dirs = {{-1,-2},{-2,-1},{-2,1},{-1,2},{1,-2},{2,-1},{2,1},{1,2}};
    static final int CHESS_LENGTH = 8;

    //Time Complexity: T(n) = O(N^2)
    //Because we have a square matrix and in the worst case. Thus we may have to deal with each of the cells. And that’s how a quadratic time complexity is achieved.
    //Space Complexity: A(n) = O(N^2)
    //Here we have used BFS, because of which the algorithm has polynomial space complexity.
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);

        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited.add("0,0");
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i < size; i++){
                int[] pos = queue.poll();
                if (pos[0]==x && pos[1]==y) {
                    return count;
                }
                addMoves(queue, visited, pos, x, y);
            }
            count++;
        }
        return -1;
    }

    private void addMoves(Queue<int[]> queue, Set<String> visited, int[] pos, int x, int y) {
        for (int i = 0; i < CHESS_LENGTH; i++) {
            int nx = pos[0] + dirs[i][0], ny = pos[1] + dirs[i][1]; // (x,y) at positive direction; `nx >= -1 && ny >= -1` moves towards (x,y)
            if (!visited.contains(nx+","+ny) && isInside(nx,ny)) {
                queue.offer(new int[]{nx, ny});
                visited.add(nx+","+ny);
            }
        }
    }

    private boolean isInside(int x, int y){

        if (x < 0 && x >= CHESS_LENGTH && y < 0 && y >= CHESS_LENGTH){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {

        KnightMoves kn = new KnightMoves();
        int x = 2;
        int y = 1;
        System.out.println("Minimum knight moves from {0,0} to {"+x+","+y+"} "+kn.minKnightMoves(x,y));
        x = 5;
        y = 5;
        System.out.println("Minimum knight moves from {0,0} to {"+x+","+y+"} "+kn.minKnightMoves(x,y));

    }
}
