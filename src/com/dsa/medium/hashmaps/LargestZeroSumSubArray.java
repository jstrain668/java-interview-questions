package com.dsa.medium.hashmaps;

//Question: Given an array having both positive and negative integers. The task is to compute the length of the largest
// subarray with sum 0.

//Use Hashing to solve this problem in O(n) time. The idea is to iterate through the array and for every element nums[i],
// calculate sum of elements form 0 to i (this can simply be done as sum += nums[i]). If the current sum has been seen
// before, then there is a zero sum array. Hashing is used to store the sum values, so that we can quickly store sum and
// find out whether the current sum is seen before or not.


//Reference: https://takeuforward.org/data-structure/length-of-the-longest-subarray-with-zero-sum/

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LargestZeroSumSubArray {




    public int[] maxSubArray(int[] nums){
        Map<Integer, Integer> map = new HashMap<>();

        int maxLen = 0;
        int sum = 0;
        int lastIndex = 0;

        for (int i=0; i < nums.length; i++){

            sum += nums[i];

            if (sum == 0){
                maxLen = i+1;
                lastIndex = i;
            } else {
                if (map.containsKey(sum)){
                    maxLen = Math.max(maxLen,i - map.get(sum));
                    lastIndex = i;
                } else {
                    map.put(sum,i);
                }
            }
        }

        int[] result = new int[maxLen];
        int startIndex = lastIndex+1 - maxLen;

        int index = 0;
        for (int i = startIndex; i <= maxLen; i++){
            result[index++] = nums[i];
        }

        return result;
    }

    public static void main(String[] args) {
        LargestZeroSumSubArray subArray = new LargestZeroSumSubArray();
        int[] nums = {15,-2,2,-8,1,7,10,23};


        System.out.println("Largest subarray is "+ Arrays.toString(subArray.maxSubArray(nums)));
    }
}
