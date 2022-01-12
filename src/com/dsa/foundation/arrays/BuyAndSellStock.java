package com.dsa.foundation.arrays;

import java.util.Arrays;

//Reference: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

public class BuyAndSellStock {

    //Description: Two step process of finding the minPrice to buy the stock and sell the stock
    // on a subsequent day which maximises profit. Loop through the array updating the minPrice or
    // updating the maxProfit, can't do both on same day as selling can only occur on a day after
    // the purchase.
    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public int maxProfit(int[] prices){

        if (prices == null || prices.length == 0){
            throw new IllegalArgumentException("Null or empty array passed");
        }

        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;

        for (int i=0; i < prices.length; i++){
            if (prices[i] < minPrice){
                minPrice = prices[i];
            } else if (prices[i] - minPrice > maxProfit){
                maxProfit = prices[i] - minPrice;
            }
        }

        return maxProfit;
    }

    //Description: need to find out the maximum difference between two numbers in the given array.
    // Also, the second number (selling price) must be larger than the first one (buying price).
    // Use 2 loops to compare the current number against subsequent number to determine the difference
    // and record the max difference (profit)
    // Time Complexity: O(nxm)
    // Space Complexity: O(1)

    public int bfMaxProfit(int[] prices){

        if (prices == null || prices.length == 0){
            throw new IllegalArgumentException("Null or empty array passed");
        }

        int maxProfit = 0;

        for (int i=0; i < prices.length; i++){
            for (int j=i+1; j < prices.length; j++){
                int profit = prices[j] - prices[i];
                if (profit > maxProfit){
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        BuyAndSellStock bst = new BuyAndSellStock();
        int[] prices1 = new int[] {7,1,5,3,6,4};
        int[] prices2 = new int[] {7,6,4,3,1};

        System.out.println("Buy and sell stock prices: "+ Arrays.toString(prices1));
        System.out.println("Max profit: "+bst.maxProfit(prices1));
        System.out.println("Max profit: "+bst.bfMaxProfit(prices1));

        System.out.println("Buy and sell stock prices: "+ Arrays.toString(prices2));
        System.out.println("Max profit: "+bst.maxProfit(prices2));
        System.out.println("Max profit: "+bst.bfMaxProfit(prices2));
    }
}
