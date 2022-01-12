package com.dsa.foundation.trees.heap;

import java.util.PriorityQueue;
import java.util.Queue;

//Question: There are given n ropes of different lengths, we need to connect these ropes into one rope. The cost to
//connect two ropes is equal to the sum of their lengths. We need to connect the ropes with minimum cost.
//For example, if we are given 4 ropes of lengths 4, 3, 2, and 6. We can connect the ropes in the following ways.
//1) First, connect ropes of lengths 2 and 3. Now we have three ropes of lengths 4, 6, and 5.
//2) Now connect ropes of lengths 4 and 5. Now we have two ropes of lengths 6 and 9.
//3) Finally connect the two ropes and all ropes have connected.
//Total cost for connecting all ropes is 5 + 9 + 15 = 29. This is the optimized cost for connecting ropes. Other ways
// of connecting ropes would always have same or more cost. For example, if we connect 4 and 6 first (we get three
// strings of 3, 2 and 10), then connect 10 and 3 (we get two strings of 13 and 2). Finally we connect 13 and 2.
// Total cost in this way is 10 + 13 + 15 = 38.

//Reference: https://www.techiedelight.com/connect-n-ropes-with-minimal-cost/

public class ConnectRopesMinCost {


    //Solution: The idea is to connect the two lowest-cost ropes first. The resultant rope has a cost equal to the sum
    //of the connected ropes. Repeat the process (with resultant rope included) until we are left with a single rope.
    //At each iteration of the loop, we will be left with one less rope and the optimal cost is added to the total cost.
    //The final cost for connecting n ropes will be minimal among all possible combinations. A priority queue
    //implemented using min-heap is best suited for this problem
    //The time complexity of the above solution is O(n.log(n))
    //Space Complexity: O(n) extra space, where n is the total number of ropes.

    public int minCost(int arr[], int n)
    {
        // Create a priority queue
        Queue<Integer> pq = new PriorityQueue<Integer>();

        // Adding items to the pQueue
        for (int i = 0; i < n; i++) {
            pq.add(arr[i]);
        }

        // Initialize result
        int res = 0;

        // While size of priority queue
        // is more than 1
        while (pq.size() > 1) {
            // Extract shortest two ropes from pq
            int first = pq.poll();
            int second = pq.poll();

            // Connect the ropes: update result
            // and insert the new rope to pq
            res += first + second;
            pq.add(first + second);
        }

        return res;
    }

    public static void main(String[] args) {
        ConnectRopesMinCost crmc = new ConnectRopesMinCost();

        int len[] = { 4, 3, 2, 6 };
        int len1[] = {5, 4, 2, 8};
        int size = len1.length;
        System.out.println("Total cost for connecting"
                + " ropes is " + crmc.minCost(len1, size));
    }

}
