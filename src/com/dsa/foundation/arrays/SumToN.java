package com.dsa.foundation.arrays;

import java.util.HashSet;
import java.util.Set;

public class SumToN {


    //Time Complexity: O(n)
    //Space Complexity: O(n) for the hashset
    public int[] sumToN(int[] nums, int N){
        if (nums == null || nums.length < 2){
            return null;
        }

        int[] twoSum = new int[2];
        Set<Integer> set = new HashSet<>();

        for (int i : nums){
            int difference = N - i;

            if (set.contains(difference)){
                twoSum[0] = difference;
                twoSum[1] = i;
                return twoSum;
            } else{
                set.add(i);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        SumToN sn = new SumToN();
        int nums[] = {4,6,12,14,16,20};
        int N = 20;
        int[] twoSum = sn.sumToN(nums,N);
        if (twoSum != null) {
            System.out.println("Value 1 " + twoSum[0] + " + value 2 " + twoSum[1] + " = " + N);
        } else{
            System.out.println("Couldn't find two values in array which sum to "+N);
        }
    }
}
