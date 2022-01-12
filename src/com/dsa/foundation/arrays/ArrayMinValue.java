package com.dsa.foundation.arrays;

import java.util.Arrays;

public class ArrayMinValue {


    //Time Complexity: O(n)

    public int minValue(int[] nums){
        if (nums == null || nums.length == 0){
            throw new IllegalArgumentException("Cannot have empty or null parameter");
        }

        int min = nums[0];

        for (int i : nums){
            if (i < min){
                min = i;
            }
        }

        return min;
    }

    public static void main(String[] args) {

        ArrayMinValue amv = new ArrayMinValue();
        int[] nums = {0,2,5,9,-12,11,15};
        System.out.println("Source array: "+ Arrays.toString(nums));
        System.out.println("Min value: "+ amv.minValue(nums));

    }
}
