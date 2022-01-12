package com.dsa.foundation.hashmaps;

//Given an array and a number "n", find two numbers from the array that sums up to "n".

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SumToN {

    public int[] twoSum(int[] nums, int target){
        if (nums == null || nums.length < 2){
            return new int[0];
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums){
            int difference = target - num;

            if (set.contains(difference)){
                return new int[] {difference,num};
            } else{
                set.add(num);
            }
        }
        return new int[0];
    }

    public static void main(String[] args) {
        SumToN sum = new SumToN();
        int target = 15;
        int[] nums = {2,7,11,15,9,6,11};

        System.out.println("Array: "+ Arrays.toString(nums));
        System.out.println("Following numbers sum to : "+target+" "+Arrays.toString(sum.twoSum(nums,target)));


    }
}
