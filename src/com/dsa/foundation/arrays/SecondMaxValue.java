package com.dsa.foundation.arrays;



import java.util.Arrays;

public class SecondMaxValue {

    //Solution: Sort the array and return send last element in the array
    //Time Complexity: O(nlogn) where n is the length of the array
    //Space Complexity: O(1)
    public int secondMaxValue(int[] nums){

        if (nums == null || nums.length == 0){
            throw new IllegalArgumentException("Passed null or empty parameter");
        }

        Arrays.sort(nums);

        return nums[nums.length-2];
    }

    //Single pass solution using 2 variables to record highest and second highest
    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public int secondHighest(int[] nums) {

        if (nums == null || nums.length == 0){
            throw new IllegalArgumentException("Passed null or empty parameter");
        }

        int high1 = Integer.MIN_VALUE;
        int high2 = Integer.MIN_VALUE;
        for (int num : nums) {
            if (num > high1) {
                high2 = high1;
                high1 = num;
            } else if (num > high2) {
                high2 = num;
            }
        }
        return high2;
    }


    public static void main(String[] args) {

        SecondMaxValue amv = new SecondMaxValue();
        int[] nums = {0,11,9,5,-12,2,15};
        System.out.println("Source array: "+ Arrays.toString(nums));
        System.out.println("Second Max Value using sort: "+ amv.secondMaxValue(nums));

        int[] nums2 = {44,11,9,0,-12,2,35};
        System.out.println("Second Max Value in a single pass: "+ amv.secondHighest(nums2));

    }
}
