package com.dsa.medium.graphs;

import java.util.Arrays;

//Info on Bellman-Ford Alg: https://www.happycoders.eu/algorithms/bellman-ford-algorithm-java/

//Question: https://leetcode.com/problems/cheapest-flights-within-k-stops/
//Reference: https://github.com/Cee/Leetcode/blob/master/787%20-%20Cheapest%20Flights%20Within%20K%20Stops.java
//Reference: https://github.com/cherryljr/LeetCode/blob/master/Cheapest%20Flights%20Within%20K%20Stops.java

//This is  a Bellman-Ford algorithm. Input a starting point and ending point, K (the number of transfers), a two-d array
// of air tickets between two locations, and find the cheapest air ticket cost from the starting point to the ending point.

public class CheapestFlight {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[] prices = new int[n];
        //Setup the prices table so that all entries are infinity apart from the starting point (set to zero)
        Arrays.fill(prices,Integer.MAX_VALUE);
        prices[src] = 0;

        //Iterate over the number of allowed transfers
        for (int i=0; i <= K; i++){

            //Copy prices into temp array
            int[] temp = Arrays.copyOf(prices,n);
            //Iterate over the number of flights (edges)
            for (int[] flight : flights){
                int curr = flight[0];
                int next = flight[1];
                int price = flight[2];
                if (prices[curr] == Integer.MAX_VALUE){
                    continue;
                }
                temp[next] = Math.min(temp[next],prices[curr]+price);
            }
            prices = temp;
        }
        return prices[dst] == Integer.MAX_VALUE ? -1 : prices[dst];

    }

    public static void main(String[] args) {
        CheapestFlight cf = new CheapestFlight();
        int[][] flights = { {0,1,100},
                {1,2,100},
                {0,2,500}};
        int n = 3;
        int src = 0;
        int dst = 2;
        int k = 1;
        System.out.println("Cheapest price is "+cf.findCheapestPrice(n,flights,src,dst,k));
        System.out.println("Cheapest price is "+cf.findCheapestPrice(n,flights,src,dst,0));


    }
}
