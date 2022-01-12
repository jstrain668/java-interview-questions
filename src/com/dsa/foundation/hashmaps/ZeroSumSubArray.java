package com.dsa.foundation.hashmaps;

import java.util.ArrayList;
import java.util.HashMap;

//https://www.geeksforgeeks.org/print-all-subarrays-with-0-sum/

public class ZeroSumSubArray {

    static int findSubArrayCount(int[] arr, int n)
    {
        // create an empty map
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();

        // Maintains sum of elements so far
        int sum = 0;
        int count = 0;

        for (int i = 0; i < n; i++)
        {
            // add current element to sum
            sum += arr[i];

            // if sum is 0, we found a subarray starting
            // from index 0 and ending at index i
            if (sum == 0){
                count++;
            }

            ArrayList<Integer> al = new ArrayList<>();

            // If sum already exists in the map there exists
            // at-least one subarray ending at index i with
            // 0 sum
            if (map.containsKey(sum))
            {
                // map[sum] stores starting index of all subarrays
                al = map.get(sum);
                for (int it = 0; it < al.size(); it++)
                {
                    count++;
                }
            }
            al.add(i);
            map.put(sum, al);
        }
        return count;
    }




    public static void main(String[] args) {
        ZeroSumSubArray zero = new ZeroSumSubArray();
        int[] arr = {6, 3, -1, -3, 4, -2, 2, 4, 6, -12, -7};
        int n = arr.length;

        int count = zero.findSubArrayCount(arr, n);

        // if we did not find any subarray with 0 sum,
        // then subarray does not exists
        System.out.println("Number of subarrays: "+count);

        int[] nums = {0,0,5,5,0,0};
        count = zero.findSubArrayCount(nums, nums.length);

        // if we did not find any subarray with 0 sum,
        // then subarray does not exists
        System.out.println("Number of subarrays: "+count);


    }
}
