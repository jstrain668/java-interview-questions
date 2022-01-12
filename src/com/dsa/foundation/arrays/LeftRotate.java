package com.dsa.foundation.arrays;

import java.util.Arrays;

//Reference: https://www.techiedelight.com/right-rotate-an-array-k-times/

public class LeftRotate {

    //Solution one pass with auxiliary space for the new array
    //Time Complexity; O(n)
    //Space Complexity: O(n)
    public int[] leftRotateWithArray(int[] nums, int numRotations){

        if (nums == null || nums.length == 0){
            throw new IllegalArgumentException("Passed null or empty parameter");
        }

        if (nums.length == 1){
            return nums;
        }

        int len = nums.length;
        int[] rotateArray = new int[len];

        for (int i=0; i < len;i++){
            int index = (i - numRotations) % len;
            if (index < 0){
                index = len - Math.abs(index);
            }
            rotateArray[index] = nums[i];
        }

        return rotateArray;
    }

    public void leftRotateByOne(int[] nums){

        int first = nums[0];

        for (int i=1; i < nums.length; i++ ){
            nums[i-1] = nums[i];
        }

        nums[nums.length-1] = first;
    }


    //Solution: call 'leftRotateByOne' k times to perform the k number of left rotations
    //Time Complexity: O(k*n) where k is the number of rotations and n the length of the array
    //Space Complexity: O(1)
    public void leftRotate(int[] nums,int k){

        for (int i=0; i < k; i++){
            leftRotateByOne(nums);
        }
    }

    public static void main(String[] args) {
        LeftRotate lr = new LeftRotate();

        int[] nums = {0,11,9,5,-12,2,15};
        System.out.println("Source array: "+ Arrays.toString(nums));
        int k = 1;
        lr.leftRotate(nums,k);
        System.out.println("Left rotated array by one: "+ Arrays.toString(nums));

        int[] nums2 = {0,11,9,5,-12,2,15};
        lr.leftRotateWithArray(nums2,k);
    }
}
