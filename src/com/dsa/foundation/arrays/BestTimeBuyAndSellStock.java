package com.dsa.foundation.arrays;

//Reference: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

public class BestTimeBuyAndSellStock {

    // Description: Rules buy on [i]th day and sell [i+n] which produces a profit.
    // Keep on buying and selling to max profit. Only sell when profit to be made
    // Time complexity : O(n). Single pass.
    // Space complexity: O(1). Constant space needed.
    public int simpleMaxProfit(int[] prices){

        int maxProfit = 0;

        for (int i=1; i < prices.length; i++){
            int profit = prices[i] - prices[i-1];
            if (profit > 0){
                maxProfit += profit;
            }
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = {7,1,5,3,6,4};
        int[] prices2 = {1,2,3,4,5};
        int[] prices3 = {7,6,4,3,1};
        BestTimeBuyAndSellStock spm = new BestTimeBuyAndSellStock();
        System.out.println("Maximum profit return: single pass "+spm.simpleMaxProfit(prices1));
        System.out.println("Maximum profit return: single pass "+spm.simpleMaxProfit(prices2));
        System.out.println("Maximum profit return: single pass "+spm.simpleMaxProfit(prices3));
    }
}
