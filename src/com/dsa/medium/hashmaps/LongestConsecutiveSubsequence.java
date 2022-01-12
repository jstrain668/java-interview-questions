package com.dsa.medium.hashmaps;

import java.util.*;

public class LongestConsecutiveSubsequence {


    public int findLongestConsecutiveSubsequenceAlt(int[] nums){

        if (nums == null || nums.length == 0){
            return 0;
        }
        Set<Integer> set = new HashSet<>();

        for (int num : nums){
            set.add(num);
        }

        int maxLen = 0;
        int count = 0;

        for (int i=0; i < nums.length; i++){

            // if current element is the starting
            // element of a sequence
            if (!set.contains(nums[i] - 1)){
                int j = nums[i];
                while (set.contains(j)){
                    j++;
                    count++;
                }
                if (count > maxLen){
                    maxLen = count;
                }
                count = 0;
            }
        }
        return maxLen;
    }

    public int findLongestConsecutiveSubsequence(int[] nums) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Integer> store = new TreeMap<>();

        for (int num : nums) {
            store.put(num, store.getOrDefault(num, 0) + 1);
        }

        List<Integer> ans = new ArrayList<>();
        int index = 0;
        int prev = 0;
        int maxLen = 0; int len = 0;
        for (var entry : store.keySet()) {

            if (index == 0) {
                index++;
                len = 1;
                maxLen = 1;
            } else {
                if (entry - prev == 1) {
                    len++;
                    if (len > maxLen){
                        maxLen = len;
                    }
                } else {
                    len = 1;
                }
            }
            prev = entry;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestConsecutiveSubsequence lcs = new LongestConsecutiveSubsequence();
        int[] nums = {2,6,1,9,4,5,3};

        int result = lcs.findLongestConsecutiveSubsequence(nums);
        System.out.println(result);

        result = lcs.findLongestConsecutiveSubsequenceAlt(nums);
        System.out.println(result);

        int[] nums2 = {1,9,3,10,4,20,2};
        result = lcs.findLongestConsecutiveSubsequence(nums2);
        System.out.println(result);

        result = lcs.findLongestConsecutiveSubsequenceAlt(nums2);
        System.out.println(result);

        int[] nums3 = {61,130};

        result = lcs.findLongestConsecutiveSubsequence(nums3);
        System.out.println(result);

        result = lcs.findLongestConsecutiveSubsequenceAlt(nums3);
        System.out.println(result);
    }
}
