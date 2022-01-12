package com.dsa.foundation.arrays;

import java.util.Arrays;

public class RightRotate {

    //Solution one pass with auxiliary space for the new array
    //Time Complexity; O(n)
    //Space Complexity: O(n)
    public int[] rightRotateWithArray(int[] nums, int numRotations){

        if (nums == null || nums.length == 0){
            throw new IllegalArgumentException("Passed null or empty parameter");
        }

        if (nums.length == 1){
            return nums;
        }

        int len = nums.length;
        int[] rotateArray = new int[len];

        for (int i=0; i < len;i++){
            int index = (i + numRotations) % len;
            rotateArray[index] = nums[i];
        }

        return rotateArray;
    }


    public void rightRotateByOne(int[] nums){

        int last = nums[nums.length - 1];

        for (int i= nums.length -2; i >= 0; i--){
            nums[i+1] = nums[i];
        }

        nums[0] = last;
    }


    //Solution: call 'rightRotateByOne' k times to perform the k number of right rotations
    //Time Complexity: O(k*n) where k is the number of rotations and n the length of the array
    //Space Complexity: O(1)
    public void rightRotate(int[] nums,int numRotations){

        if (nums == null || nums.length==0){
            return;
        }

        if (numRotations <= 0){
            return;
        }

        for (int i=0; i < numRotations; i++){
            rightRotateByOne(nums);
        }
    }


    public static void main(String[] args) {
        RightRotate rr = new RightRotate();
        int[] nums = {0,11,9,5,-12,2,15};
        System.out.println("Source array: "+ Arrays.toString(nums));
        int k = 1;
        System.out.println("Rotated array by "+k+ " : "+ Arrays.toString(rr.rightRotateWithArray(nums,k)));
        int[] nums2 = {0,11,9,5,-12,2,15};
        rr.rightRotate(nums2,k);
        System.out.println("Rotated array by "+k+ " : "+ Arrays.toString(nums2));
    }
}
