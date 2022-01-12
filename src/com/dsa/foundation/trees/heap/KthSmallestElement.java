package com.dsa.foundation.trees.heap;

import java.util.PriorityQueue;

public class KthSmallestElement {

    public int findKthSmallest(int[] nums,int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int kthSmallest = Integer.MIN_VALUE;
        for (int num : nums){
            pq.add(num);
        }

        while (!pq.isEmpty() && k-- > 0){
            kthSmallest = pq.poll();
        }

        return kthSmallest;
    }

    public static void main(String[] args) {
        KthSmallestElement heap = new KthSmallestElement();
        int[] nums = {12,21,9,33,0,77,11,22};
        int k = 4;

        int kSmallest = heap.findKthSmallest(nums,k);
        System.out.println("Kth smallest : "+kSmallest);

    }
}
