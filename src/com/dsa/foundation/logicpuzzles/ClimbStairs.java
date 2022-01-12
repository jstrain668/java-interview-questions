package com.dsa.foundation.logicpuzzles;


//Question: How many different ways to climb stairs where you can take 1,2 or 3 steps at a time.
//Reference: https://www.techiedelight.com/find-total-ways-to-reach-nth-stair/
//Reference: https://afteracademy.com/blog/climbing-stairs-problem

public class ClimbStairs {

    // Recursive function to find total ways to reach the n'th stair from the bottom
    // when a person is allowed to climb either 1 or 2 or 3 stairs at a time
    //Time Complexity: O(3 to the power of n)
    //Space Complexity: O(n) for recursion stack space
    public static int totalWays(int n)
    {
        // base case
        if (n < 0) {
            return 0;
        }

        // base case: there is one way to cover it with no steps
        if (n == 0) {
            return 1;
        }

        // combine results of taking 1 step or 2 steps or 3 steps at a time
        return totalWays(n - 1) + totalWays(n - 2) + totalWays(n - 3);
    }

    // Recursive DP function to find total ways to reach the n'th stair from the bottom
    // when a person is allowed to climb either 1 or 2 or 3 stairs at a time
    //Time Complexity: O(n)
    //Space Complexity: O(n) for recursion stack space + O(n) for the dp array
    public static int totalWaysTD(int n, int[] dp)
    {
        // base case
        if (n < 0) {
            return 0;
        }

        // base case: there is one way to cover it with no steps
        if (n == 0) {
            return 1;
        }

        // if the sub problem is not seen before
        if (dp[n] == 0)
        {
            // combine results of taking 1 step or 2 steps or 3 steps at a time
            dp[n] = totalWaysTD(n - 1, dp) + totalWaysTD(n - 2, dp) + totalWaysTD(n - 3, dp);
        }

        // return the sub problem solution
        return dp[n];
    }

    // DP function to find total ways to reach the n'th stair from the bottom
    // when a person is allowed to climb either 1 or 2 or 3 stairs at a time
    //Since overlapping sub problems are present in this scenario and it has optimal substructure property, we could
    // optimize our algorithm using dynamic programming. We could store the results of the already computed subproblems
    // and then retrieve their results in O(1) instead of calculating again.
    //Time Complexity: O(n)
    //Space Complexity: O(n) for the dp array
    public static int totalWaysBU(int n)
    {
        // create an array of size `n+1` for storing solutions to the subproblems
        int[] lookup = new int[n + 1];

        // base case: 1 way (with no steps)
        lookup[0] = 1;

        // There is only 1 way to reach the 1st stair
        lookup[1] = 1;

        // There are 2 ways to reach the 2nd stair
        lookup[2] = 2;

        // fill the lookup table in a bottom-up manner
        for (int i = 3; i <= n; i++) {
            lookup[i] = lookup[i - 1] + lookup[i - 2] + lookup[i - 3];
        }

        return lookup[n];
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.printf("Total ways to reach the %d'th stair are %d \n",
                n, totalWays(n));

        // create an array of size `n+1` for storing solution to the sub problems
        // and initialize it by 0
        int[] dp = new int[n + 1];
        System.out.printf("Total ways to reach the %d'th stair are %d \n",
                n, totalWaysTD(n, dp));

        System.out.println("Total ways to reach the " + n + "'th stair are "
                + totalWaysBU(n));

    }
}
