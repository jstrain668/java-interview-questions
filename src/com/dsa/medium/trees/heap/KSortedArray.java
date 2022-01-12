package com.dsa.medium.trees.heap;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

//Question: Given a k–sorted array that is almost sorted such that each of the n elements may be misplaced by no more
// than k positions from the correct sorted order. Find a space-and-time efficient algorithm to sort the array.

//Question: https://www.techiedelight.com/sort-k-sorted-array/
//Reference: https://www.geeksforgeeks.org/nearly-sorted-algorithm/

public class KSortedArray {

    //We can sort such arrays more efficiently with the help of Heap data structure. Following is the detailed process
    // that uses Heap.
    //1) Create a Min Heap of size k+1 with first k+1 elements. This will take O(k) time
    //2) One by one remove min element from heap, put it in result array, and add a new element to heap from remaining
    // elements.
    //Removing an element and adding a new element to min heap will take log k time. So overall complexity will be O(k)
    // + O((n-k) * log(k)). O(n log k)

    public void sortKSortedArray(List<Integer> nums,int k){

    }

    public static void main(String[] args)
    {
        KSortedArray heap = new KSortedArray();
        List<Integer> nums = Arrays.asList(1, 4, 5, 2, 3, 7, 8, 6, 10, 9);
        int k = 2;

        heap.sortKSortedArray(nums, k);
        System.out.println(nums);
    }
}
