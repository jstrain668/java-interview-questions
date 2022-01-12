package com.dsa.foundation.hashmaps;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Reference: https://leetcode.com/problems/find-the-duplicate-number/

public class FindDuplicate {

    //Description: To find first duplicate in an int array, sort the array in ascending order and then loop through array
    //checking ith element against ith-1 and stop when match found. The ith element value is returned. Zero is returned
    //if no duplicate is found.
    //Time Complexity: Sort array = O(nlogn) + Match check loop O(n) = O(n) + O(nlogn). Time complexity is equal to the
    //dominant term = O(nlogn)
    //Space Complexity: O(1)
    public int findDuplicate(int[] nums) {

        if (nums == null || nums.length < 2){
            throw new IllegalArgumentException("Array size is less than 2");
        }

        Arrays.sort(nums);

        for (int i = 1; i < nums.length; i++){
            if (nums[i-1] == nums[i]){
                return nums[i];
            }
        }

        return 0;
    }

    //Description: To find first duplicate in an int array,use a HashSet to add each number and detect duplicate via
    //contains method. if contains returns true then return number from array. If no duplicate found return zero.
    //Time Complexity: O(n) for looping through array, the hashset contains and add are constant time.
    //Space Complexity: O(n) for the hashset
    public int findDuplicate2(int[] nums) {

        if (nums == null || nums.length < 2){
            throw new IllegalArgumentException("Array size is less than 2");
        }

        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++){
            if (set.contains(nums[i])){
                return nums[i];
            } else {
                set.add(nums[i]);
            }
        }

        return 0;
    }

    //Given a constant array of n elements which contains elements from 1 to n-1, with any of these numbers appearing
    // any number of times. Find any one of these repeating numbers in O(n) and using only constant memory space.
    //Approach that is based on Floyd’s cycle finding algorithm. We use this to detect loops in a linked list.
    //The idea is to consider array items as linked list nodes. Any particular index is pointing to the value at that
    // index. In case of a duplicate, two indexes will have same value, and they will form a cycle.
    // Can find the entry point of cycle in the linked list and that will be our duplicate element.
    // 1. Maintain two pointers fast and slow
    // 2. For each step fast will move to the index that is equal to arr[arr[fast]](two jumps at a time) and slow will
    // move to the index arr[slow](one step at a time)
    // 3. When fast==slow that means now we are in a cycle.
    // 4. Fast and slow will meet in a circle and the entry point of that circle will be the duplicate element.
    // 5. Now need to find entry point so we will start with fast=0 and visit one step at a time for both fast and slow.
    // 6. When fast==slow that will be entry point.
    // 7. Return the entry point.
    // Time Complexity: Two loops of O(n) = O(2n). Drop the constant so time complexity = O(n)
    // Space Complexity: O(1)


    public int findDuplicate3(int[] nums) {

        if (nums.length < 2){
            return 0;
        }

        // initialize fast(hare) and slow(tortoise)
        int tortoise = nums[0];
        int hare = nums[nums[0]];

        //loop to enter cycle
        while (hare != tortoise){
            //tortoise moves one step
            tortoise = nums[tortoise];
            //hare moves two steps
            hare = nums[nums[hare]];
        }

        //loop to find entry point of the cycle
        hare = 0;
        while (tortoise != hare){
            tortoise = nums[tortoise];
            hare = nums[hare];
        }

        return tortoise;
    }

    public static void main(String[] args) {
        FindDuplicate fd = new FindDuplicate();
        int[] nums = {1,3,4,2,2};
        int[] nums2 = {3,1,3,4,2};

        System.out.println("Duplicate in int array: "+Arrays.toString(nums2)+ " = "+fd.findDuplicate3(nums2));
    }


}
