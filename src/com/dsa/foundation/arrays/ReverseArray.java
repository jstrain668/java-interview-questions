package com.dsa.foundation.arrays;

import java.util.Arrays;

//Reference: https://www.techiedelight.com/inplace-merge-two-sorted-arrays/

public class ReverseArray {

    private void swap(int[] nums,int i, int j){

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    //Time Complexity: O(n/2) as shrink the array traversal by 1 for every call. Drop the constant so its O(n)
    //Space Complexity: O(1)
    public void reverseArrayAlt(int[] nums){

        int i=0;
        int j=nums.length-1;

        while (i<j){
            swap(nums,i,j);
            i++;
            j--;
        }
    }

    //Time Complexity: O(n/2) drop the constant so it becomes O(n)
    //Space Complexity: O(1)
    public void reverseArray(int[] nums){

        for (int i=0; i < nums.length/2; i++){
            int other = nums.length - i - 1;
            int temp = nums[i];
            nums[i] = nums[other];
            nums[other] = temp;
        }
    }

    public static void main(String[] args) {
        ReverseArray ra = new ReverseArray();
        int[] nums = {23,2,56,-4,56,89,45};
        System.out.println("Original array: "+ Arrays.toString(nums));
        ra.reverseArray(nums);
        System.out.println("Reversed array: "+ Arrays.toString(nums));

        int[] nums2 = {20,25,59,100,56,99,40,0};
        System.out.println("Original array: "+ Arrays.toString(nums2));
        ra.reverseArrayAlt(nums2);
        System.out.println("Reversed array: "+ Arrays.toString(nums2));
    }
}
