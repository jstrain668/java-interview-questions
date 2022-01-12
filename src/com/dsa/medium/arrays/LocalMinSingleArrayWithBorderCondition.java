package com.dsa.medium.arrays;

//Question: Problem: Given an array A[0,…, n-1], an element at index i(0 < i < n-1) is a local minimum if A[i] < A[i-1]
// as well as A[i]< A[i+1]. That is, the element is lower than the element to the immediate left as well as to the
// element to the immediate right.
// Constraints: – The numbers are unique
// = For corner elements, we need to consider only one neighbor for comparison.
// Because there is no constraint - The first two numbers are decreasing and the last two numbers are increasing then
// there is no guarantee a local min will be found

//Reference: https://www.geeksforgeeks.org/find-local-minima-array/
//https://java2blog.com/find-local-minima-array/#Efficient_approach

import java.util.Arrays;

public class LocalMinSingleArrayWithBorderCondition {


    //Solution: Use iterative binary search to find local min which satisfies a[i] < a[i-1] and a[i] < a[i+1]
    //Before the loop handle the special cases of empty array - Return -1
    //Border checks are executed to find local min. Also since has reach the border of the array can't go any
    //further so return having not found a local min
    //Time Complexity: O(log n) due to binary search.
    //Space Complexity: O(1)
    public int findLocalMinIterative(int[] nums){

        if (nums == null || nums.length == 0){
            System.out.println("Null or empty array passed into findLocalMin");
            return -1;
        }

        int start = 0;
        int end = nums.length;

        while (start <= end){
            int mid = start + (end - start) / 2;

            //Border checks and exit from recursive loop
            if (mid == 0) {
                if (nums[mid] < nums[mid+1]){
                    return nums[mid];
                } else {
                    System.out.println("No local min could be found - mid point in binary search reached " + mid);
                    return -1;
                }
            }

            if (mid == nums.length-1) {
                if (nums[mid] < nums[mid-1]){
                    return nums[mid];
                } else {
                    System.out.println("No local min could be found - mid point in binary search reached " + mid);
                    return -1;
                }
            }

            if ((nums[mid] < nums[mid-1]) && (nums[mid] < nums[mid+1])) {
                return nums[mid];
            }
            else if (nums[mid] > nums[mid - 1]) { // Go left
                end = mid;
            }
            else {  // Go right
                start = mid + 1;
            }
        }

        //Reached here then a local min couldn't be found
        System.out.println("No local min could be found");
        return -1;

    }


    //Solution: Use recursive binary search to find local min which satisfies a[i] < a[i-1] and a[i] < a[i+1]
    //Exit checks in recursive loop when mid reaches zero or array length -1. Before exiting execute border check for
    // local min.
    //Time Complexity: O(log n) due to binary search.
    //Space Complexity: O(log n) for the call stack
    private int findLocalMin(int[] nums, int start, int end)
    {
        int mid = start + (end - start) / 2;
        // Find index of middle element

        //Border checks and exit from recursive loop
        if (mid == 0) {
            if (nums[mid] < nums[mid+1]){
                return nums[mid];
            } else {
                System.out.println("No local min could be found - mid point in binary search reached " + mid);
                return -1;
            }
        }

        if (mid == nums.length-1) {
            if (nums[mid] < nums[mid-1]){
                return nums[mid];
            } else {
                System.out.println("No local min could be found - mid point in binary search reached " + mid);
                return -1;
            }
        }

        if ((nums[mid] < nums[mid-1]) && (nums[mid] < nums[mid+1])) {
            return nums[mid];
        }
        else if (nums[mid] > nums[mid - 1]) {  // Go Left
            return findLocalMin(nums, start, mid);
        }
        else { // Go Right
            return findLocalMin(nums, mid + 1, end);
        }
    }

    //Solution: Use recursive binary search to find local min which satisfies a[i] < a[i-1] and a[i] < a[i+1]
    //Time Complexity: O(log n) due to binary search.
    //Space Complexity: O(log n) for the call stack

    public int findLocalMinRecursive(int[] nums){

        if (nums == null || nums.length == 0){
            System.out.println("Null or empty array passed into findLocalMin");
            return -1;
        }

        return findLocalMin(nums,0,nums.length);
    }

    public int findLocalMinLinear(int[] nums){

        if (nums == null || nums.length == 0){
            System.out.println("Null or empty array passed into findLocalMin");
            return -1;
        }

        //One element array is its own local min
        if (nums.length == 1){
            return nums[0];
        }

        //Special case of 2 element array apply border condition check
        if (nums.length == 2){
            if (nums[0] < nums[1]){
                return nums[0];
            }
        }

        //Start from 1 to handle predecessor check and end before length -1 to handle successor check
        for (int i=0; i < nums.length; i++){

            //Border condition check
            if (i == 0){
                if (nums[i] < nums[i+1]){
                    return nums[i];
                }
                continue;
            }

            //Border condition check
            if (i == nums.length-1){
                if (nums[i] < nums[i-1]){
                    return nums[i];
                }
            }

            // nums[i] has to be less than its left neighbour and right neighbour
            if ((nums[i] < nums[i-1]) && (nums[i] < nums[i+1])) {
                return nums[i];
            }
        }

        //Reached here then a local min couldn't be found
        System.out.println("No local min could be found");
        return -1;
    }

    public static void main(String[] args) {

        LocalMinSingleArrayWithBorderCondition localMinSingleArray = new LocalMinSingleArrayWithBorderCondition();
        int[] nums = {10, 5, 3, 6, 13, 16, 19};
        int[] nums1 = {14,13,12,11};
        int[] nums2 = {11,12,13,14};
        int[] nums3 = {8,6};
        int[] nums4 = {8, 5, 2, 7, 3, 4, 1, 9};
        int[] nums5 = {3,0,-1,-2,-4,-7,-10, -12, 0, -8,10};
        int[] nums6 = {9, 6, 3, 14, 5, 7, 4};
        int[] nums7 = {23, 8, 15, 2, 3};
        int[] nums8 = {1, 2, 3};
        int[] nums9 = {3, 2, 1};
        System.out.println("Array of ints: "+ Arrays.toString(nums));

        System.out.println("Local min: "+localMinSingleArray.findLocalMinLinear(nums));
        System.out.println("Local min: "+localMinSingleArray.findLocalMinRecursive(nums));
        System.out.println("Local min: "+localMinSingleArray.findLocalMinIterative(nums));
    }
}
