package com.dsa.medium.trees.heap;

//Given n ropes of different lengths, connect them into a single rope with minimum cost. Assume that the cost to connect
// two ropes is the same as the sum of their lengths.

//The idea is to connect the two lowest-cost ropes first. The resultant rope has a cost equal to the sum of the
// connected ropes. Repeat the process (with resultant rope included) until we are left with a single rope.
//
//At each iteration of the loop, we will be left with one less rope and the optimal cost is added to the total cost.
// The final cost for connecting n ropes will be minimal among all possible combinations. A priority queue implemented
// using min-heap is best suited for this problem.

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class ConnectRopesMinCost {


    //The time complexity of the above solution is O(n.log(n)) and requires O(n) extra space, where n is the total
    // number of ropes.
    public int findMinCost(List<Integer> prices)
    {
        // create a min-heap using `PriorityQueue` class from elements of the list
        PriorityQueue<Integer> pq = new PriorityQueue<>(prices);

        // keep track of the minimum cost so far
        int cost = 0;

        // repeat till heap size is reduced to one
        while (pq.size() > 1)
        {
            // Extract the top two elements from the min-heap
            int x = pq.poll();
            int y = pq.poll();

            // calculate the cost of the extracted values
            int sum = x + y;

            // insert the cost back to the min-heap
            pq.add(sum);

            // update the minimum cost
            cost += sum;
        }

        return cost;
    }


    public static void main(String[] args)
    {
        ConnectRopesMinCost heap = new ConnectRopesMinCost();
        List<Integer> prices = Arrays.asList(5, 4, 2, 8);
        System.out.println("The minimum cost is " + heap.findMinCost(prices));
    }
}
