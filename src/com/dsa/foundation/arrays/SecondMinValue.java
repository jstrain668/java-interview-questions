package com.dsa.foundation.arrays;

import java.util.Arrays;

public class SecondMinValue {

    //Solution: Sort the array and return send element in the array
    //Time Complexity: O(nlogn) where n is the length of the array
    //Space Complexity: O(1)
    public int secondMinValue(int[] nums){
        if (nums == null || nums.length == 0){
            throw new IllegalArgumentException("Passed null or empty parameter");
        }

        Arrays.sort(nums);

        return nums[1];
    }

    //Single pass solution using 2 variables to record lowest and second lowest
    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public int secondLowest(int[] nums){
        if (nums == null || nums.length == 0){
            throw new IllegalArgumentException("Passed null or empty parameter");
        }

        int lowest1 = Integer.MAX_VALUE;
        int lowest2 = Integer.MAX_VALUE;

        for (int num : nums){

            if (num < lowest1){
                lowest2 = lowest1;
                lowest1 = num;
            } else if (num < lowest2){
                lowest2 = num;
            }
        }
        return lowest2;
    }

    public static void main(String[] args) {
        SecondMinValue smv = new SecondMinValue();
        int[] nums = {0,11,9,5,-12,2,15};
        System.out.println("Source array: "+ Arrays.toString(nums));
        System.out.println("Second Min Value using sort: "+ smv.secondMinValue(nums));

        int[] nums2 = {44,11,9,0,-12,2,35};
        System.out.println("Second Min Value in a single pass: "+ smv.secondLowest(nums2));

    }
}
