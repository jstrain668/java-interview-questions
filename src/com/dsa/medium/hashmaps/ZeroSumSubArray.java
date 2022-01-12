package com.dsa.medium.hashmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//https://www.geeksforgeeks.org/print-all-subarrays-with-0-sum/

public class ZeroSumSubArray {

    public int findSubArrayCount(int[] arr, int n)
    {
        // create an empty map
        Map<Integer, List<Integer>> map = new HashMap<>();

        int sum=0;
        int count=0;

        for (int i=0; i < n; i++){
            sum += arr[i];

            if (sum == 0){
                count++;
            }


            List<Integer> saList = new ArrayList<>();
            if (map.containsKey(sum)){
                saList = map.get(sum);
                for (int num : saList){
                    count++;
                }
            }
            saList.add(i);
            map.put(sum,saList);
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
