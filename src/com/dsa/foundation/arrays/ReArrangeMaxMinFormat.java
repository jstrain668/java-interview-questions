package com.dsa.foundation.arrays;

import java.util.Arrays;

//Question: Rearrange Sorted Array in Max/Min Form
//Given an array, can you re-arrange the elements such that the first position will have the largest number, the second
// will have the smallest, the third will have the second-largest, and so on.

public class ReArrangeMaxMinFormat {

    //Since given a sorted array a simple solution is to assign the max numbers to the even indices in a new array
    // starting from index 0. The max number  are extracted from right to left of source array. The min numbers are the
    // odd numbers and they are extracted from left to right of the source array
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public int[] maxMinFormat(int[] nums){

        if (nums == null || nums.length == 0){
            throw new IllegalArgumentException("Can't pass empty or null parameter");
        }

        //returned array
        int[] maxminArray = new int[nums.length];
        int firstIndex = 0;
        int lastIndex = nums.length-1;

        for (int i = 0; i < nums.length; i++) {

            if((i%2) == 0) { //even indices

                maxminArray[i] = nums[lastIndex--];
            }
            else {
                maxminArray[i] = nums[firstIndex++];
            }
        }

        return maxminArray;
    }

    public static void main(String[] args) {
        ReArrangeMaxMinFormat mmf = new ReArrangeMaxMinFormat();

        int[] nums = {0,11,9,5,-12,2,15};
        Arrays.sort(nums);
        System.out.println("Source array: "+ Arrays.toString(nums));
        mmf.maxMinFormat(nums);
        System.out.println("Max Min Format Array: "+ Arrays.toString(mmf.maxMinFormat(nums)));
    }
}
