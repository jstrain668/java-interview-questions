package com.dsa.medium.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Question: Find The Continuous Sub Array In An Array Whose Sum Is Equal To Given Number?
//You have given an integer array and a number. You need to find the continuous sub array of the given array whose sum
//is equal to given number. For example, If {12, 5, 31, 9, 21, 8} is the given array and 45 is the given number, then
// you have to find continuous sub array in this array such that whose elements add up to 45. In this case, {5, 31, 9}
// is such sub array whose elements add up to 45.

public class ContinuousSubArrayToK {

    //Time Complexity: O(n)
    public int[] findSubArray(int[] nums, int k)
    {

        if (nums == null || nums.length == 0){
            return new int[0];
        }

        List<Integer> res = new ArrayList<>();

        //Initializing sum with the first element of the nums
        int sum = nums[0];

        //Initializing starting point with 0
        int start = 0;

        boolean found = false;
        //Iterating through nums starting from second element
        for (int i = 1; i < nums.length; i++)
        {
            //Adding nums[i] to the current 'sum'
            sum += nums[i];

            //If sum is greater than k then following loop is executed until
            //sum becomes either smaller than or equal to inputNumber

            while(sum > k && start <= i-1)
            {
                //Removing starting elements from the 'sum'
                sum -= nums[start++];
            }

            //If 'sum' is equal to 'k' then printing the sub array
            if(sum == k)
            {
                for (int j = start; j <= i; j++)
                {
                    res.add(nums[j]);
                }
                found = true;
                break;
            }
        }

        if (found){
            int[] result = new int[res.size()];
            for (int i=0; i <res.size(); i++){
                result[i] = res.get(i);
            }
            return result;
        }

        return new int[0];
    }

    public static void main(String[] args) {
        ContinuousSubArrayToK csa = new ContinuousSubArrayToK();
        int[] nums = {12, 5, 31, 9, 21, 8};
        int k = 45;
        System.out.print("Continuous sub array of "+ Arrays.toString(nums)+" whose sum is "+k+" is ");
        int[] res = csa.findSubArray(nums,k);
        System.out.println(Arrays.toString(res));

    }
}
