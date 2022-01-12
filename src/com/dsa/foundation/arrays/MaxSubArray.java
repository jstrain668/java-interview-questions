package com.dsa.foundation.arrays;

import java.util.Arrays;

public class MaxSubArray {


    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0){
            throw new IllegalArgumentException("Null or empty array not allowed");
        }

        int maxSum = Integer.MIN_VALUE;
        int sum = 0;

        for(int i=0;i<nums.length;i++) {
            sum = Math.max(sum+nums[i], nums[i]);
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0){
            throw new IllegalArgumentException("Null or empty array not allowed");
        }

        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;
        int startIndex = 0;
        int endIndex = 0;
        int s = 0;

        for(int i=0;i<nums.length;i++) {

            maxEndingHere += nums[i];

            if (maxEndingHere > maxSoFar){
                maxSoFar = maxEndingHere;
                endIndex = i;
                startIndex = s;
            }

            if (maxEndingHere < 0){
                maxEndingHere = 0;
                s = i + 1;
            }

        }

        int subArray[] = Arrays.copyOfRange(nums,startIndex,endIndex+1);
        System.out.println("SubArray: "+Arrays.toString(subArray));

        return maxSoFar;
    }

    public static void main(String[] args) {
        MaxSubArray msa = new MaxSubArray();
        int[] nums1 = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println("Original array: "+ Arrays.toString(nums1));
        System.out.println("Max subarray value: "+ msa.maxSubArray(nums1));
        System.out.println("Max subarray value: "+ msa.maxSubArray2(nums1));

        int[] nums2 = {1};
        System.out.println("Original array: "+ Arrays.toString(nums2));
        System.out.println("Max subarray value: "+ msa.maxSubArray(nums2));
        System.out.println("Max subarray value: "+ msa.maxSubArray2(nums2));

        int[] nums3 = {5,4,-1,7,8};
        System.out.println("Original array: "+ Arrays.toString(nums3));
        System.out.println("Max subarray value: "+ msa.maxSubArray(nums3));
        System.out.println("Max subarray value: "+ msa.maxSubArray2(nums3));

    }
}
