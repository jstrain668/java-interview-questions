package com.dsa.foundation.arrays;

import java.util.Arrays;



public class MergeSortedArrays {


    //Time Complexity: O(len1 * len2) where len1 is the length of 'nums1' and len2 is the length of 'nums2'
    //Space Complexity: O(1)
    public void mergeArraysZeroSpace(int[] nums1,int[] nums2){

        if (nums1.length == 0 || nums1 == null || nums2.length == 0 || nums2 == null){
            return;
        }

        int len1 = nums1.length;
        int len2 = nums2.length;

        // Consider each element `nums1[i]` of array `nums1` and ignore the element if it is
        // already in the correct order; otherwise, swap it with the next smaller
        // element, which happens to be the first element of `nums2`.
        for (int i=0; i < len1; i++){

            // compare the current element of `nums[i]` with the first element of `nums2[0]`
            if (nums1[i] > nums2[0]){

                int temp = nums1[i];
                nums1[i] = nums2[0];
                nums2[0] = temp;

                int first = nums2[0];

                // move `nums2[0]` to its correct position to maintain the sorted
                // order of `nums2[]`. Note: `nums[1…n-1]` is already sorted
                int k;
                for (k=1; k < len2 && nums2[k] < first; k++){
                    nums2[k-1] = nums2[k];
                }

                nums2[k-1] = first;
            }
        }

    }

    //Time Complexity: O(n1+n2) where n1 is the length of the first array and n2 is the length of the second array
    //Space Complexity: O(n1+n2) - space assigned for the merged array
    public int[] mergeArrays(int[] nums1,int[] nums2){

        if ((nums1.length == 0 || nums1 == null) && (nums2.length == 0 || nums2 == null)){
            return null;
        }

        if (nums1.length == 0){
            return nums2;
        }

        if (nums2.length == 0){
            return nums1;
        }

        int len1 = nums1.length;
        int len2 = nums2.length;
        int[] mergedArray = new int[len1+len2];

        int n1 = 0;
        int n2 = 0;
        int mIndex = 0;

        while (n1 < len1 && n2 < len2){
            if (nums1[n1] <= nums2[n2]){
                mergedArray[mIndex++] = nums1[n1++];
            }else{
                mergedArray[mIndex++] = nums2[n2++];
            }
        }

        while (n1 < len1){
            mergedArray[mIndex++] = nums1[n1++];
        }

        while (n2 < len2){
            mergedArray[mIndex++] = nums2[n2++];
        }

        return mergedArray;
    }

    public static void main(String[] args) {
        MergeSortedArrays msa = new MergeSortedArrays();
        int[] nums1 = {1,6,8,10,12};
        int[] nums2 = {0,2,5,9,11,15};
        System.out.println("Sorted array 1: "+ Arrays.toString(nums1));
        System.out.println("Sorted array 2: "+ Arrays.toString(nums2));
        System.out.println("Merged(1+2) and sorted array: "+Arrays.toString(msa.mergeArrays(nums1,nums2)));

        int[] nums3 = {1,6,8,10,12};
        int[] nums4 = {9,11,15,20,34,54};
        System.out.println("Sorted array 1: "+ Arrays.toString(nums3));
        System.out.println("Sorted array 2: "+ Arrays.toString(nums4));
        msa.mergeArraysZeroSpace(nums3,nums4);
        System.out.println("Merged and sorted array 1: "+Arrays.toString(nums3));
        System.out.println("Merged and sorted array 2: "+Arrays.toString(nums4));
    }
}
