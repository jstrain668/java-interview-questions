package com.dsa.medium.arrays;


//Question: https://leetcode.com/problems/longest-increasing-subsequence/
//Reference: https://segmentfault.com/a/1190000003819886
//Reference: https://github.com/wzhishen/leetcode/blob/master/src/solutions/LongestIncreasingSubsequence_300.java
//Reference: https://afteracademy.com/blog/longest-increasing-subsequence

import java.util.ArrayList;
import java.util.Arrays;

public class LongestIncreasingSubsequence {

    //Naive Solution
    //Find the max length of subsequence that ends with index i.
    //So max[i] = max[j = 0..i-1] + 1 (when nums[i] > nums[j]);

    public int naiveLengthOfLIS(int[] nums) {

        if (nums == null || nums.length == 0){
            return 0;
        }

        if (nums.length == 1){
            return 1;
        }

        int dp[] = new int[nums.length];
        int max = 0;
        for (int i = 0; i < nums.length; ++i) {
            max = 1;
            for (int j = 0; j < i; ++j) {
                if (nums[i] > nums[j]) {
                    max = Math.max(max, dp[j] + 1);
                }
            }
            dp[i] = max;
        }

        for (int i = 0; i < dp.length; ++i) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }



    // Points: Since this longest ascending sequence is not necessarily continuous, for every newly added number, it is
    // possible to form a longer ascending sequence with the previous sequence, or to form a longer ascending sequence
    // with the following sequence. For example 1,3,5,2,8,4,6, for 6, it can be constituted 1,3,5,6 or constituted
    // 2,4,6. Because the length of the previous sequence is 4 and the length of the latter is 3, we prefer 6 to form
    // the sequence of 4, so for 6, the length of the sequence is actually the length of the longest ascending sequence
    // before plus 1. Note that the end of the longest sequence must be less than 6.
    // Assuming that it dp[i] represents the length of the longest ascending sequence that can be formed by adding the
    // i-th number, we need dp[0] to dp[i-1] to find the longest ascending sequence length in it, and ensure that the
    // tail value of the sequence is nums[j] is less than nums[i]. Then add 1 to this length. At the same time,
    // we must update the maximum length in time.


    //Solution: Want to find the longest increasing sequence in the array.
    // To find the count of nums[i] > nums[i-1] through [0 ~ i] history, need to calculate them all and check the max.
    // dp[i]: represents the max increasing sequence for nums[i].
    // Note: its not 'up to i', it's just specifically calculated for index i: nums[i]
    // After each iteration (i) the max figure is compared to the max dp[i] recorded for previous nums[i] and updated
    // if the current nums[i] is bigger than the previous max
    // Compare all prior values: dp[i] = dp[i] > dp[j] + 1 ? dp[i] : dp[j] + 1, where j = [0, i).
    // Time Complexity: O(nxn) - while not strictly nxn
    // Space Complexity: Auxiliary array O(n)
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1){
            return 1;
        }

        int[] dp = new int[nums.length]; // dp[0] = 0
        int max = 0;

        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(dp[i], max);
        }
        return max + 1;
    }

    //Optimal Solution using binary search:
    // Maintain a list of nums in increasing order. When considering a new num:
    //- See if it can append (num > last-max-num from the list)
    //- If not, do binary search with the list and see where the number may fit.
    //- Every time, set num to where may fit in the list (find the smallest item from list which also > num)
    // Why setting a number in the list?
    // The list works as a baseline, which adjusts dynamically: any number less than the baseline won't be able to
    // append. However, it refines (lowers) the baseline, which potentially allow future number to append.
    // At the end, return the size of list.
    // Time complexity: O(nlogn) = O(n) for the loop and each num is potentially subject to binary search thus
    // O(nlogn)
    // Space Complexity: O(n) for the ArrayList

    public int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        if (nums.length == 1){
            return 1;
        }

        ArrayList<Integer> list = new ArrayList<Integer>();

        for (int i=0; i < nums.length; i++) {
            //Build the ascending list
            //First element 'n' is always added to list.
            //New element 'n' will be added if greater than the last element added to the list
            if (list.isEmpty() || nums[i] > list.get(list.size()-1)) {
                list.add(nums[i]);
            } else { //Current element 'n' is less than the last element added to the list
                int low = 0, high = list.size() - 1;
                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    if (nums[i] <= list.get(mid)) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                }
                //Bin search produces the index of the element in the list to be replaced
                list.set(low, nums[i]);
            }
        }
        System.out.println(list.toString());
        return list.size();
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] nums = {10,9,2,5,3,7,101,18};
        int[] nums1 = {7,7,7};
        int[] nums2 = {0,1,0,3,2,3};
        int[] nums3 = {0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15};
        System.out.println("Input array: "+ Arrays.toString(nums3));
        System.out.println("Longest increasing subsequence: "+lis.naiveLengthOfLIS(nums3));
        System.out.println("Longest increasing subsequence: "+lis.lengthOfLIS(nums3));
        System.out.println("Longest increasing subsequence: "+lis.lengthOfLIS2(nums3));
    }
}
