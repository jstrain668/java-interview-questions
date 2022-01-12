package com.dsa.foundation.logicpuzzles;

import java.util.Arrays;

//Reference: https://leetcode.com/problems/missing-number/

public class MissingNumber {

    //Description: Since the numbers follow a sequence (add 1 to the next number). The missing
    // number in the sequence can be derived by calculating the sum of all numbers and subtracting
    // it from the outcome of applying Gauss formula n(n + 1)/2
    // Time complexity: O(n) - sum all the values in the source array
    // Space Complexity: O(1)
    public int missingNumberUsingGauss(int[] nums){

        if (nums == null || nums.length==0){
            throw new IllegalArgumentException("Null or empty array not allowed");
        }

        int n = nums.length;

        int gaussTotal = (n * (n + 1)) / 2;
        int sum = 0;

        for (int i=0; i < nums.length; i++){
            sum += nums[i];
        }

        return gaussTotal - sum;
    }


    //Description: To find the missing number, sort the array (ascending order). Loop through the
    //expected sequence of numbers comparing against source array. If mis-match then this is missing number
    // If no mis-match in loop then nth number is missing number.
    //Time Complexity: Sort = n log (n) for average, O(nxn) for worst case. Add O(n) for for loop
    //Space Complexity: O(1)
    public int missingNumber(int[] nums){

        if (nums == null || nums.length==0){
            throw new IllegalArgumentException("Null or empty array not allowed");
        }

        // n number is the last number and the number of elements in the source array
        int n = nums.length;

        // Start index for iterating through array
        int index = 0;

        // Sort the nums in ascending order for
        Arrays.sort(nums);

        for (index = 0; index < n; index++){
            if (index != nums[index]){
                return index;
            }
        }

        // If all elements in source are matched in source array then the nth element
        // is the missing number
        return n;
    }

    public int missingNumberGauss(int[] nums){

        if (nums == null || nums.length==0){
            throw new IllegalArgumentException("Null or empty array not allowed");
        }

        int n = nums.length;
        int gaussTotal = n * (n + 1) / 2;

        int total = 0;
        for (int i=0; i < n; i++){
            total += nums[i];
        }

        return gaussTotal - total;
    }

    public static void main(String[] args) {
        MissingNumber mn = new MissingNumber();
        int[] nums1 = new int[] {3,0,1};
        int[] nums2 = new int[] {0,1};
        int[] nums3 = new int[] {9,6,4,2,3,5,7,0,1};
        int[] nums4 = new int[] {0};

        System.out.println("Source int array: "+ Arrays.toString(nums3));
        System.out.println("Missing number from source array: "+ mn.missingNumber(nums3));
        System.out.println("Missing number from source array: "+ mn.missingNumberUsingGauss(nums3));
        System.out.println("Missing number from source array: "+ mn.missingNumberGauss(nums3));
    }
}
