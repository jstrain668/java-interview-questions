package com.dsa.medium.arrays;

//Question: Maximum Sum Subarray of Size K
//Given an array of positive integers, and a positive number k, find the maximum sum of any contiguous subarray of
// size k.

//Reference: https://www.callicoder.com/maximum-sum-subarray-of-size-k/
import java.util.Arrays;

public class MaxSubArrayToK {

    //Solution: start a loop that runs for every index of the array and then for each index you start another loop to
    //add the next k elements. This way, you will be able to calculate the sum of all k-sized subarrays and then return
    //the maximum of all the sums.
    //Time Complexity: O(n*k), where n is the number of elements in the array, and k is the size of the subarray.
    //Space Complexity: O(k)
    public int maxSumOfSizeK(int[] nums, int k){

        if (nums == null || k <= 0 || nums.length < k){
            return 0;
        }

        int maxSum = 0;

        for (int i=0; i < nums.length -k; i++){
            int subArraySum = 0;
            for (int j=i; j < i+k; j++){
                subArraySum += nums[j];
            }
            maxSum = Math.max(maxSum,subArraySum);
        }

        return maxSum;
    }

    //Sliding windows solution: Initialize two variables windowStart and windowEnd both set to zero, i.e., both
    //pointing to the first element of the array. So the initial window size is 1.
    //Initialize another variable windowSum = 0 that stores the sum of the current window (current subarray). And a
    //variable maxSum = Integer.MIN_VALUE that keeps track of the maximum sum among all the subarrays of size k.
    //Time Compexity: O(n)
    public int maxSumOfSizeK2(int[] nums, int k){

        if (nums == null || k <= 0 || nums.length < k){
            return 0;
        }

        int maxSum = Integer.MIN_VALUE;
        int windowStart = 0;
        int windowSum = 0;

        for (int windowEnd=0; windowEnd < nums.length; windowEnd++){
            windowSum+=nums[windowEnd]; // Add the next element to the window

            if (windowEnd - windowStart+1 == k){ //When we hit the window size. Update maximum sum, and slide the window
                maxSum = Math.max(maxSum, windowSum);
                windowSum -= nums[windowStart]; // Subtract the element going out of the window
                windowStart++; // Slide the window
            }
        }

        return maxSum;
    }


    public static void main(String[] args) {
        MaxSubArrayToK msat = new MaxSubArrayToK();
        int k = 2;
        int[] nums = {3, 5, 2, 1, 7};
        System.out.println("Original array: "+ Arrays.toString(nums));
        System.out.println("Max sum subarray of size "+k+" = "+msat.maxSumOfSizeK(nums,k));
        System.out.println("Max sum subarray of size "+k+" = "+msat.maxSumOfSizeK2(nums,k));

        int[] nums2 = {4, 2, 3, 5, 1, 2};
        k = 3;
        System.out.println("Original array: "+ Arrays.toString(nums2));
        System.out.println("Max sum subarray of size "+k+" = "+msat.maxSumOfSizeK(nums2,k));
        System.out.println("Max sum subarray of size "+k+" = "+msat.maxSumOfSizeK2(nums2,k));

    }
}
