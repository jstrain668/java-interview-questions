package com.dsa.foundation.logicpuzzles;

import java.util.Arrays;

//Reference: https://leetcode.com/problems/house-robber/

public class HouseRobber {

    //Description: Max loot for robbing houses is a choice between robbing ith or ith house plus ith
    //+2 house over n available houses. Recursive solution where the base exit condition from recursive loop
    // is when ith is equal to number of houses or ith > number of houses.
    //Time complexity O(2 to the power of n)
    //Space Complexity O(n) because of system calls
    private int maxLoot(int[] nums, int index){

        if (index >= nums.length){
            return 0;
        }

        int result = Math.max(maxLoot(nums,index+1),nums[index] + maxLoot(nums,index+2));
        return result;
    }

    public int rob(int[] nums){
        if (nums == null || nums.length ==0){
            throw new IllegalArgumentException("Null or empty array not allowed");
        }
        return maxLoot(nums,0);
    }

    //Description: Max loot for robbing houses is a choice between robbing ith or ith house plus ith
    //+2 house over n available houses.
    // Update dp[0] as nums[0] and dp[1] as maximum of nums[0] and nums[1]
    //Traverse the array from the second element (2nd index) to the end of array.
    //For every index, update dp[i] as maximum of dp[i-2] + nums[i] and dp[i-1], this step defines
    // the two cases, if an element is selected then the previous element cannot be selected and if an
    // element is not selected then the previous element can be selected.
    // Max loot is recorded in dp[n-1]
    //Time complexity O(n) - for loop
    //Space Complexity O(n) because of additional array
    public int dpRob(int[] nums){

        if (nums == null || nums.length ==0){
            throw new IllegalArgumentException("Null or empty array not allowed");
        }

        if (nums.length == 1){
            return nums[0];
        }

        if (nums.length == 2){
            return Math.max(nums[0],nums[1]);
        }

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0],nums[1]);
        for (int i=2; i < nums.length; i++){
            dp[i] = Math.max(nums[i] + dp[i-2],dp[i-1]);
        }

        return dp[dp.length-1];
    }

    // Description: Create two variables value1 and value2. value1 as nums[0] and
    // value2 as maximum of nums[0] and nums[1] and a variable max_val to store the answer
    //Traverse the array from the second element (2nd index)  to the end of array.
    //For every index, update max_val as maximum of value1 + nums[i] and value2.
    // This step defines the two cases, if an element is selected then the previous element
    // cannot be selected and if an element is not selected then the previous element can be selected.
    //For every index, Update value1 = value2 and value2 = max_val
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int altRob(int[] nums){

        if (nums == null || nums.length ==0){
            throw new IllegalArgumentException("Null or empty array not allowed");
        }

        if (nums.length == 1){
            return nums[0];
        }

        if (nums.length == 2){
            return Math.max(nums[0],nums[1]);
        }

        int value1 = nums[0];
        int value2 = Math.max(nums[0],nums[1]);

        int maxValue = 0;
        for (int i=2; i < nums.length; i++){
            maxValue = Math.max(nums[i] + value1,value2);
            value1 = value2;
            value2 = maxValue;
        }

        return maxValue;
    }

    public static void main(String[] args) {
        HouseRobber hr = new HouseRobber();
        int[] nums = {1,2,3,1};
        int[] nums2 = {6, 7, 1, 3, 8, 2, 4};
        int[] nums3 = {2,1,1,2};
        System.out.println("Max loot from houses: "+ Arrays.toString(nums2)+" is "+hr.rob(nums2));
        System.out.println("Max loot from houses: "+ Arrays.toString(nums2)+" is "+hr.dpRob(nums2));
        System.out.println("Max loot from houses: "+ Arrays.toString(nums2)+" is "+hr.altRob(nums2));
    }
}
