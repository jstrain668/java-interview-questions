package com.dsa.foundation.arrays;

import java.util.Arrays;

//Reference: https://leetcode.com/problems/move-zeroes/

public class MoveZeroes {

    // Description: In first loop remove all zeros by swapping zero value with next value in array which
    // is non-zero while incrementing count of all values in array which is non-zero. In second loop start
    // from count to the end of array and set each value to zero
    // Time Complexity: O(n + n) =O(n)
    // Space Complexity: O(1)
    public void moveZeroes(int[] nums){

        // Return illegal argument exception for null or empty array
        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("Null or empty array passed in!");

        // Return if array has only one value - nothing to move
        if (nums.length == 1)
            return;

        int count = 0;

        // Traverse array removing non zeroes and incrementing count
        for (int i = 0; i < nums.length; i++){
            if(nums[i] != 0){
                nums[count++] = nums[i];
            }
        }

        //Turn from count to end of array to zeros
        for (int j = count; j < nums.length; j++){
            nums[j] = 0;
        }
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public void moveZeroesEfficiently(int[] nums) {
        // here we will be maintain 2 pointers
        // left and right
        // if  value at right(i) is not zero then swap it with left and increment both of them by 1
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                int temp = nums[left];
                nums[left] = nums[i];
                nums[i] = temp;
                left++;
            }
        }
    }

    public static void main(String[] args) {
        MoveZeroes mz = new MoveZeroes();
        int[] nums = new int[] {0,0,1,3,12};
        //int[] nums = new int[] {1,0,1};
        System.out.println("Original array before moving zeroes: "+ Arrays.toString(nums));
        //mz.moveZeroes(nums);
        mz.moveZeroesEfficiently(nums);
        System.out.println("Array after moving zeroes: "+Arrays.toString(nums));
    }
}
