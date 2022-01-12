package com.dsa.foundation.arrays;

import java.util.Arrays;

public class SimpleBinSearch {

    // Iterative divide and conquer approach to search for an element in an array. Dependent on the array been sorted.
    // Successively halve the array until element is found or not.
    // Time complexity : O(log_2(n)). Reduce the search space in half at every step. Thus, the total search space will
    // be consumed in log_2(n) steps. Here, n refers to the size of nums array.
    // Space complexity : O(1). Constant extra space is used.
    public boolean binSearchIterative(int[] nums, int element){

        int start = 0;
        int end = nums.length - 1;

        while (start <= end){
            int mid = start + (end - start) / 2;

            //Element to be found to the right of the mid point in the array.
            if (element > nums[mid]){
                start = mid + 1;
            } else if (element < nums[mid]){  //Element to be found to the left of the mid point in array
                end = mid - 1;
            } else{ //Found the element in the array
                return true;
            }
        }
        //Element not found
        return false;

    }

    // Recursive divide and conquer approach to search for an element in an array. Dependent on the array been sorted.
    // Successively halve the array until element is found or not.
    // Time complexity : O(log_2(n)). Reduce the search space in half at every step. Thus, the total search space will
    // be consumed in log_2(n) steps. Here, n refers to the size of nums array.
    // Space complexity : O(1). Constant extra space is used.
    private boolean binSearchRec(int[] nums, int element, int start, int end){

        if (start > end){
            return false;
        }

        int mid = start + (end - start) / 2;

        if (element > nums[mid]){
            return binSearchRec(nums, element, mid+1, end);
        } else if (element < nums[mid]){
            return binSearchRec(nums,element,start,mid-1);
        } else {
            return true;
        }
    }

    public boolean binSearchRecursive(int[] nums, int element){
        return binSearchRec(nums,element,0,nums.length-1);
    }

    public static void main(String[] args) {
        SimpleBinSearch sbs = new SimpleBinSearch();
        int[] nums = {-3, 5,2, 99, 23, 44, 65, 17, 47, 7, -88,0, 34, 40, 19};
        Arrays.sort(nums);
        int element = 100;
        System.out.println("Is the element "+element+" in the array: "+Arrays.toString(nums)+" "+sbs.binSearchIterative(nums,element));
        System.out.println("Is the element "+element+" in the array: "+Arrays.toString(nums)+" "+sbs.binSearchRecursive(nums,element));
    }
}
