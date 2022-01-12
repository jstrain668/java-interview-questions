package com.dsa.foundation.arrays;

import java.util.Arrays;
import java.util.HashMap;

//Reference: https://leetcode.com/problems/rotate-array/
public class RotateArray {

    private int calcRotateValue(int arrayLen, int k){
        return (k % arrayLen);
    }

    //Description: Calculate the rotation if k is > nums length, use modulus operator when dividing by
    //size of array to get rotation value. So we don't overwrite values when rotating each index value
    //to its new rotation index store the array to Hashmap and retrieve values from HashMap when rotating
    //Time Complexity: O(n) = O(n) for backing up array to HashMap + O(n) for rotating array. Accessing
    //HashMap is assumed to be O(1) but it can be O(n)
    //Space Complexity: O(n) for HasMap
    public void rotate(int[] nums, int k) {

        if (nums == null || nums.length == 0){
            throw new IllegalArgumentException("Null or empty array passed");
        }

        if (k < 0){
            throw new IllegalArgumentException("Rotate value cannot be negative");
        }

        int arrayLen = nums.length;

        if (arrayLen == 1){
            return;
        }

        if (k > arrayLen){
            k = calcRotateValue(arrayLen, k);
        }

        HashMap<Integer,Integer> hm = new HashMap<>();

        for (int i=0; i < arrayLen; i++){
            hm.put(i,nums[i]);
        }

        int destIndex;
        for (int i=0; i < arrayLen; i++){

            if (i + k > arrayLen -1){
                destIndex = (i + k) - (arrayLen);
            } else {
                destIndex = i + k;
            }
            nums[destIndex] = hm.get(i);
        }
    }

    //Description: Simple solution, calculate the rotation index using (k+i)%nums.length, use modulus
    //operator when dividing by size of array to get rotation value. So we don't overwrite values when
    //rotating each index value to its new rotation index, store the results in a new array.
    //Copy new array contents back to original array
    //Time Complexity: O(n) = O(n) for rotating and storing in new array + O(n) for copying back
    //Space Complexity: O(n) for new array
    public void rotateK(int[] nums, int k) {

        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Null or empty array passed");
        }

        if (k < 0) {
            throw new IllegalArgumentException("Rotate value cannot be empty");
        }

        int arrayLen = nums.length;

        if (arrayLen == 1) {
            return;
        }

        int[] rotateArray = new int[nums.length];

        for (int i=0; i < nums.length; i++){
            rotateArray[(i+k) % nums.length] = nums[i];
        }

        for (int i=0; i < rotateArray.length; i++){
            nums[i] = rotateArray[i];
        }

    }

    public void rightRotateByOne(int[] nums)
    {
        int last = nums[nums.length - 1];
        for (int i = nums.length - 2; i >= 0; i--) {
            nums[i + 1] = nums[i];
        }

        nums[0] = last;
    }

    // Brute force solution to right-rotate an array by `k` positions
    // Time Complexity: O(n*k)
    // Space Complexity: O(1)
    public void rightRotate(int[] nums, int k)
    {
        for (int i = 0; i < k; i++) {
            rightRotateByOne(nums);
        }
    }

    public int rotateTo(int i, int arrayLen, int rotateLen){
        return (i + rotateLen) % arrayLen;
    }
    public void rotateK2(int[] nums, int k){
        if (nums == null || nums.length == 0){
            throw new IllegalArgumentException("Passed null or empty array");
        }

        if (nums.length == 1 || k == 0){
            return;
        }

        HashMap<Integer,Integer> store = new HashMap<>();

        for (int i=0; i < nums.length; i++){
            store.put(i,nums[i]);
        }

        for (int i=0; i < nums.length; i++){
            int destIndex = rotateTo(i,nums.length,k);
            nums[destIndex] = store.get(i);
        }
    }

    public static void main(String[] args) {
        RotateArray ra = new RotateArray();
        int[] nums = {1,2,3,4,5,6,7};
        int[] nums2 = {-1,-100,3,99};
        System.out.println("Source array: "+ Arrays.toString(nums));
        ra.rotate(nums,20);
        System.out.println("Rotate array: "+ Arrays.toString(nums));
        System.out.println("Source array: "+ Arrays.toString(nums2));
        ra.rotate(nums2,2);
        System.out.println("Rotate array: "+ Arrays.toString(nums2));

        System.out.println("Source array: "+ Arrays.toString(nums));
        ra.rotateK(nums,20);
        System.out.println("Rotate array: "+ Arrays.toString(nums));
        System.out.println("Source array: "+ Arrays.toString(nums2));
        ra.rotateK(nums2,2);
        System.out.println("Rotate array: "+ Arrays.toString(nums2));

        System.out.println("Source array: "+ Arrays.toString(nums));
        ra.rightRotate(nums,20);
        System.out.println("Rotate array: "+ Arrays.toString(nums));
        System.out.println("Source array: "+ Arrays.toString(nums2));
        ra.rightRotate(nums2,2);
        System.out.println("Rotate array: "+ Arrays.toString(nums2));

        nums = new int[] {1,2,3,4,5,6,7};
        System.out.println("Rotate array: "+ Arrays.toString(nums));
        ra.rotateK2(nums,3);
        System.out.println("Rotate array: "+ Arrays.toString(nums));
    }
}
