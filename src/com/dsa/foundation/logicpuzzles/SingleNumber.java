package com.dsa.foundation.logicpuzzles;

import java.util.Arrays;
import java.util.HashSet;

//Reference: https://leetcode.com/problems/single-number/

public class SingleNumber {

    // Description: Can assume there is one unique number in the nums array.
    // Use a HashSet to detect if number is unique or not. If found in HashSet then remove.
    // Number left in HashSet is the unique number unless there is more than one unique number
    // Time Complexity: O(n) - Cycle through all elements in array
    // Space Complexity: O(1) - Since only one unique value

    public int singleNumber(int[] nums){

        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("Cannot have null or empty numbers list");

        HashSet<Integer> uniqueNumbers = new HashSet<>();

        for (int i=0; i < nums.length;i++) {

            int number = nums[i];
            if (uniqueNumbers.contains(number))
                uniqueNumbers.remove(number);
            else
                uniqueNumbers.add(number);
        }

        if (uniqueNumbers.size() != 1)
            throw new IllegalArgumentException("Numbers array did not contain one unique number");

        return uniqueNumbers.iterator().next();

    }

    // Description: XOR is used to determine if values are same or different. XOR, takes two boolean
    // operands and returns true if and only if the operands are different. Thus, it returns false
    // if the two operands have the same value.
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int singleNumberXOR(int[] nums){

        if (nums == null || nums.length == 0)
            throw new IllegalArgumentException("Cannot have null or empty numbers list");

        int number = nums[0];
        for (int i=1; i < nums.length; i++) {
            number = number ^ nums[i];
        }

        return number;
    }

    public static void main(String[] args) {
        SingleNumber sn = new SingleNumber();
        int[] nums = new int[] {4,1,2,1,2};
        //int[] nums = new int[] {4,0,4};
        System.out.println("Numbers array: "+ Arrays.toString(nums));
        System.out.println("Number which occurs once in array: "+sn.singleNumber(nums));
        System.out.println("Number which occurs once in array using XOR: "+sn.singleNumberXOR(nums));
    }
}
