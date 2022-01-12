package com.dsa.foundation.arrays;

import java.util.Arrays;

//Reference: https://www.studymite.com/rearrange-positive-and-negative-values-in-an-array-problem/

public class ReArrangePosNeg {

    public int[] reArrangeWithArray(int[] nums){

        if (nums == null || nums.length==0){
            return nums;
        }

        int[] reArranged = new int[nums.length];

        int index=0;
        for (int num: nums){
            if (num < 0){
                reArranged[index++] = num;
            }
        }

        for (int num: nums){
            if (num >= 0){
                reArranged[index++] = num;
            }
        }

        return reArranged;
    }


    //Single pass solution with no extra space
    public void partitionSort(int[] nums){

        int j = 0;
        for (int i=0; i < nums.length; i++){
            if (nums[i] < 0){
                if (i != j){ //Ensure not swapping with self - just increment j
                    int temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
                j++;
            }
        }
    }

    public static void main(String[] args) {
        ReArrangePosNeg pn = new ReArrangePosNeg();
        int[] nums = {0,11,-9,5,-12,2,15,-7};
        System.out.println("Source array: "+ Arrays.toString(nums));
        System.out.println("Rearranged array: "+ Arrays.toString(pn.reArrangeWithArray(nums)));

        int[] nums2 = {0,11,-9,5,-12,2,15,-7};
        System.out.println("Source array: "+ Arrays.toString(nums2));
        pn.partitionSort(nums2);
        System.out.println("Rearranged array: "+ Arrays.toString(nums2));
    }
}
