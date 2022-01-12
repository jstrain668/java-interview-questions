package com.dsa.foundation.hashmaps;

import java.util.*;

//Three sum to target

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums, int target){

        List<List<Integer>> result = new ArrayList<List<Integer>>();

        for (int i = 0; i < nums.length; i++){
            Set<Integer> set = new HashSet<>();
            int currentTarget = target - nums[i];
            for (int j = i+1; j < nums.length; j++){
                if (set.contains(currentTarget - nums[j])){
                    List<Integer> output = Arrays.asList(nums[i],nums[j],currentTarget - nums[j]);
                    result.add(output);
                } else {
                    set.add(nums[j]);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSum threeSum = new ThreeSum();
        int[] nums = {7, 12, 3, 1, 2, -6, 5, -8, 6};
        int target = 0;

        List<List<Integer>> result = threeSum.threeSum(nums,target);

        for (List<Integer> res : result){
            System.out.println(res.toString());
        }
    }
}
