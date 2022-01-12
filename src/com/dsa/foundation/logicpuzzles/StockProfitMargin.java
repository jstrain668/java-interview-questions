package com.dsa.foundation.logicpuzzles;

public class StockProfitMargin {

    //Time complexity : O(nxn). Loop runs n(n-1)/2 times
    //Space complexity : O(1)O(1). Only two variables maxprofit and profit are used.
    public int doublePassMaxProfit(int[] prices){
        int max_profit = 0;

        if (prices.length == 0)
            return 0;

        for (int i=0; i < prices.length-1; i++){
            for (int j=i+1; j < prices.length; j++){ //j > i
                int profit = prices[j] - prices[i];
                if (profit > max_profit){
                    max_profit = profit;
                }
            }
        }
        return max_profit;
    }

    // The points of interest are the peaks and valleys in the given graph.
    // We need to find the largest peak following the smallest valley.
    // We can maintain two variables - minprice and maxprofit corresponding
    // to the smallest valley and maximum profit (maximum difference between
    // selling price and minprice) obtained so far respectively.
    // Complexity Analysis
    //Time complexity : O(n). Only a single pass is needed.
    //Space complexity : O(1). Only two variables are used.
    public int singlePassMaxProfit(int[] prices){
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;

        if (prices.length == 0)
            return 0;


        for (int i=0; i < prices.length; i++){

            if (prices[i] < minPrice){
                minPrice = prices[i];
            }else if (prices[i] - minPrice > maxProfit){
                maxProfit = prices[i] - minPrice;
            }

        }
        return maxProfit;
    }

    public int simpleMaxProfit(int[] prices){
        int maxProfit = 0;
        int minSellingPrice = prices[0];

        for (int i=0; i < prices.length; i++){

            if (prices[i] < minSellingPrice){
                minSellingPrice = prices[i];
            }

            int profit = prices[i] - minSellingPrice;
            if (profit > maxProfit){
                maxProfit = profit;
            }
        }

        return maxProfit;
    }
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        int[] prices1 = {7,6,4,3,1};
        StockProfitMargin spm = new StockProfitMargin();
        System.out.println("Maximum profit return: double pass "+spm.doublePassMaxProfit(prices));
        System.out.println("Maximum profit return: single pass "+spm.singlePassMaxProfit(prices));
        System.out.println("Maximum profit return: single pass "+spm.simpleMaxProfit(prices));
    }
}
