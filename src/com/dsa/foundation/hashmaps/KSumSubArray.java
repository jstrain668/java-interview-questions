package com.dsa.foundation.hashmaps;

import java.util.HashMap;

//Given an array of integers and an integer k, you need to find the total number of continuous subarrays whose sum
// equals to k.

//Question: https://leetcode.com/problems/subarray-sum-equals-k/

public class KSumSubArray {

    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        HashMap< Integer, Integer > map = new HashMap<>();
        map.put(0, 1);

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];

            if (map.containsKey(sum - k)) {
                count += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }

    public static void main(String[] args) {
        KSumSubArray sumK = new KSumSubArray();

        int[] arr = { -3, 2, 3, 1, 6 };
        int k = 6;
        System.out.println("Subarray with "+k+" sum has length of "+sumK.subarraySum(arr,k));


        int[] nums = { 3, 4, -7, 3, 1, 3, 1, -4, -2, -2 };
        k = 0;
        System.out.println("Subarray with "+k+" sum has length of "+sumK.subarraySum(nums,k));

    }
}
