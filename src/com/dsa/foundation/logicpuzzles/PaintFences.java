package com.dsa.foundation.logicpuzzles;

//Question: There is a fence with n posts, each post can be painted with one of the k colors. You have to paint all the
// posts such that no more than two adjacent fence posts have the same color. Return the total number of ways you can
// paint the fence. Note: n and k are non-negative integers.

//Solution Analysis: Typical DP questions. Because the same color is only allowed when two consecutive fences are
//allowed here dp[i], the state is not only dp[i-1]related, but also dp[i-2]related.
//Consider two situations, one is that the current fence color is different from the previous fence color, and the other
//is that the current fence color is the same as the previous fence color. For the former, obviously
// dp[i] = (k - 1) * dp[i - 1]. For the latter, because the title requires that the color of three consecutive fences
// cannot be the same, we only need to ensure that the last fence is different from the current fence.
// At this time dp[i] = 1 * (k - 1) * dp[i - 2].
//Resulting in dp[i] = (k - 1) * dp[i - 1] + (k - 1) * dp[i - 2]
//In addition, this question can also have Follow up. For example, the question is changed to allow 3at most one fence
// to have the same color. What is the result?
// dp[i] = dp[i - 1] * (k - 1) + dp[i - 2] * (k - 1) * 1 + dp[i - 3] * (k - 1) * 1 * 1


//Reference: https://github.com/wzhishen/leetcode/blob/master/src/solutions/PaintFence_276.java

public class PaintFences {

    /* DP recurrence:
     * dp[n] denotes total number of ways to paint a fence with n posts.
     *
     * dp[i] = dp[i-1] * (k-1) // Post i and i-1 have different colors, so there are only k-1 colors
     *                         // (different from post i-1) to choose from to paint post i.
     *
     *       + dp[i-2] * 1 * (k-1) // Post i and i-1 have same color, so post i and i-2 must have different colors,
     *                             // so there are only k-1 colors (different from post i-2) to choose from to paint
     *                             // post i. Post i-1 only has 1 choice, which is the same color as post i.
     *
     *       = (dp[i-2] + dp[i-1]) * (k-1)
     *
     * Ref: http://www.phperz.com/article/16/0101/178949.html
     */
    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public int numWays(int n, int k) {
        if (n <= 0 || k <= 0) {
            return 0;
        }
        if (n == 1) {
            return k;
        }

        int[] dp = new int[n];
        dp[0] = k;
        dp[1] = k * k;

        for (int i = 2; i < n; ++i) {
            dp[i] = (dp[i - 2] + dp[i - 1]) * (k - 1);
        }
        return dp[n - 1];
    }

    public static void main(String[] args) {
        PaintFences pf = new PaintFences();
        int numOfFences = 20;
        int numOfColours = 3;
        System.out.println("Number of ways you can paint "+numOfFences+ " fences with "+numOfColours+ " colours: "+pf.numWays(numOfFences,numOfColours));
    }
}
