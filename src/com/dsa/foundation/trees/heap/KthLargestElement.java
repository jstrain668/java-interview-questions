package com.dsa.foundation.trees.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

//https://www.techiedelight.com/find-first-k-maximum-occurring-words-given-set-strings/

public class KthLargestElement {

    public int findKthLargest(int[] nums,int k){
        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        int kthLargest = Integer.MIN_VALUE;
        for (int num : nums){
            pq.add(num);
        }

       while (!pq.isEmpty() && k-- > 0){
            kthLargest = pq.poll();
       }

        return kthLargest;
    }

    public int findKthLargestAlt(int[] nums,int k){

        //Use Min-Heap - requires only one traversal
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int num : nums){
            pq.add(num);
            if (pq.size() > k){
                pq.poll();
            }
        }

        return pq.peek();
    }

    public static void main(String[] args) {
        KthLargestElement heap = new KthLargestElement();
        int[] nums = {12,21,9,33,0,77,11,22};
        int k = 4;

        int kLargest = heap.findKthLargest(nums,k);
        System.out.println("Kth Largest : "+kLargest);

        System.out.println("Kth Largest : "+heap.findKthLargestAlt(nums,k));

    }
}
