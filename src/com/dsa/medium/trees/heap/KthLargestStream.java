package com.dsa.medium.trees.heap;

import java.util.PriorityQueue;
import java.util.Queue;

//https://leetcode.com/problems/kth-largest-element-in-a-stream/discuss/1600698/Java-or-K-sized-min-heap-for-top-k


public class KthLargestStream {

    final Queue<Integer> pq;
    final int K;

    public KthLargestStream(int k, int[] nums) {
        K = k;
        pq = new PriorityQueue<>();
        for(int num : nums) {
            pq.add(num);
            if(pq.size() > K) {
                pq.remove();
            }
        }
    }

    public int add(int val) {
        pq.add(val);
        if(pq.size() > K) {
            pq.remove();
        }
        return pq.peek();
    }

    public static void main(String[] args) {
        int[] nums = {4,5,8,2};
        KthLargestStream kthLargest = new KthLargestStream(3,nums);
        System.out.println("Kth largest after adding 3: "+kthLargest.add(3));    // return 4
        System.out.println("Kth largest after adding 5: "+kthLargest.add(5));    // return 5
        System.out.println("Kth largest after adding 10: "+kthLargest.add(10));  // return 5
        System.out.println("Kth largest after adding 9: "+kthLargest.add(9));    // return 8
        System.out.println("Kth largest after adding 4: "+kthLargest.add(4));    // return 8
    }
}
