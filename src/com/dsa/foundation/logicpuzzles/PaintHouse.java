package com.dsa.foundation.logicpuzzles;

//Question: There are a row of n houses, each house can be painted with one of the three colors: red, blue or green.
// The cost of painting each house with a certain color is different. You have to paint all the houses such that no
// two adjacent houses have the same color. The cost of painting each house with a certain color is represented by a
// n x 3 cost matrix. For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost
// of painting house 1 with color green, and so on. Find the minimum cost to paint all houses.

//Reference: https://medium.com/@SilentFlame/leetcode-paint-house-dynamic-programming-9253c3b5687c
//Reference: https://github.com/wzhishen/leetcode/blob/master/src/solutions/PaintHouse_256.java

public class PaintHouse {

    //Time Complexity: O(n)
    //Space Complexity: O(1)

    public int minCost(int[][] costs) {
        if (costs == null || costs.length == 0 || costs[0].length == 0) {
            return 0;
        }

        // 2D DP recurrence:
        // costs[i][j] now means min cost to paint all houses 0~i with house i in color j
        for (int i = 1; i < costs.length; ++i) {
            //Cost of this house to be painted red = Min cost of the previous house painted blue or green plus itself
            costs[i][0] = costs[i][0] + Math.min(costs[i - 1][1], costs[i - 1][2]);
            //Cost of this house to be painted blue = Min cost of the previous house painted red or green plus itself
            costs[i][1] = costs[i][1] + Math.min(costs[i - 1][0], costs[i - 1][2]);
            //Cost of this house to be painted green = Min cost of the previous house painted red or blue plus itself
            costs[i][2] = costs[i][2] + Math.min(costs[i - 1][0], costs[i - 1][1]);
        }
        return Math.min(costs[costs.length - 1][0],
                Math.min(costs[costs.length - 1][1],
                        costs[costs.length - 1][2]));
    }

    //Solution:
    //You have to create a dp array which will be equal to the length of the costs matrix.
    //Initialize, the first element of the dp array to the first element in the costs.
    //For each matrix, add the cost of the particular index with the minimum of the costs of the two different colors
    //found in the dp array.
    //Repeat this step for each point in the matrix, and continue this till you reach the end of the array.
    //At the end of the traversal, return the minimum cost found in the last element of the dp array.
    //Time Complexity: O(n)
    //Space Complexity: O(n)

    public int minimumCost(int[][] costs) {
        if(costs.length == 0 ||  costs== null || costs[0].length == 0){
            return 0;
        }

        int dp[][] =  new int[costs.length][3];

        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];

        for( int  i = 1;  i < costs.length; i++)
        {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + costs[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + costs[i][1];
            dp[i][2] = Math.min(dp[i-1][1], dp[i-1][0]) + costs[i][2];

        }

        return Math.min(dp[costs.length - 1][0], Math.min(dp[costs.length - 1][1], dp[costs.length - 1][2]));

    }

    public static void main(String[] args) {
        PaintHouse ph = new PaintHouse();
        int[][] costs = {{17,2,17},
                         {16,16,5},
                         {14,3,19}};
        System.out.println("Min cost to paint all houses: "+ph.minCost(costs));
    }
}
