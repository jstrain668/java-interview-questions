package com.dsa.foundation.logicpuzzles;

import java.util.Arrays;

public class Fibonacci {

    //Time Complexity: O(branches to the power of n) = O(2^n)
    //Space Complexity: O(n)
    public int fib(int n){
        if (n <= 0){
            return 0;
        } else if (n == 1) {
            return 1;
        }
        return fib(n-1) + fib(n-2);
    }

    //Time Complexity: O(2^n)
    public void printFib(int n){
        for (int i=0; i <= n; i++){
            System.out.println(i+" : "+fib(i));
        }
    }

    //Time Complexity: it stores (i.e., caches) previously computed values in an integer array. If it has already been
    //computed, it just returns the cache. What is its runtime?
    //At each call to topDowmFib(i), we have already computed and stored the values for topDownFib( i-1) and
    //topDownFib(i-2). We just look up those values, sum them, store the new result, and return. This takes a constant
    //amount of time. We're doing a constant amount of work N times, so this is O ( n) time.
    //This technique, called memoization, is a very common one to optimize exponential time recursive algorithms.
    public int topDownFib(int n,int[] memo){
        if (n <= 0){
            return 0;
        } else if (n == 1){
            return 1;
        } else if (memo[n] > 0){
            return memo[n];
        }

        memo[n] = topDownFib(n-1,memo) + topDownFib(n-2,memo);
        return memo[n];
    }

    public void printTopDownFib(int n){
        int[] memo = new int[n+1];
        for (int i=0; i <= n; i++){
            System.out.println(i+" : "+topDownFib(i,memo));
        }
    }

    //Use dynamic programming to resolve fibonacci sequence
    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public int bottomUpFib(int n){

        int[] dp = new int[n+1];

        dp[0] = 0;
        dp[1] = 1;

        for (int i=2; i <=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }

    public int[] getBottomUpFib(int n){
        int[] dp = new int[n+1];

        dp[0] = 0;
        dp[1] = 1;

        for (int i=2; i <=n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp;
    }

    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        int n=9;
        System.out.println(n+"th fibonacci number = " +f.fib(n));
        System.out.println("Recursive fibonacci calculation:");
        f.printFib(n);

        System.out.println("Top Down approach for fibonacci calculation: (Recursive + memoisation)");
        f.printTopDownFib(n);
        System.out.println("Bottom Up approach for fibonacci calculation: (Dynamic programming)");

        System.out.println(n+"th fibonacci number = " +f.bottomUpFib(n));
        int[] dp = f.getBottomUpFib(n);
        System.out.println("Fibonacci numbers up to "+n+" are "+ Arrays.toString(dp));

    }
}
