package com.dsa.foundation.hashmaps;

//https://leetcode.com/problems/4sum/

import java.util.*;

public class FourSum {


    public List<List<Integer>> fourSum(int[] nums, int target) {

        if (nums.length < 4){
            return new ArrayList<>();
        }

        Set<List<Integer>> set =new HashSet<>();
        Arrays.sort(nums);

        for(int i=0;i<nums.length-3;i++){
            for(int j=i+1;j<nums.length-2;j++){
                int left=j+1;
                int right=nums.length-1;
                while(left<right){
                    int sum=nums[i]+nums[j]+nums[left]+nums[right];
                    if(sum==target){
                        List<Integer> tmp=new ArrayList<>();
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(nums[left]);
                        tmp.add(nums[right]);
                        set.add(tmp);
                        left++;right--;
                    }else if(sum<target){
                        left++;
                    }else{
                        right--;
                    }
                }
            }
        }

        return new ArrayList<>(set);
    }


    public static void main(String[] args) {
        FourSum fs = new FourSum();
        int[] nums = { 2, 7, 4, 0, 9, 5, 1, 3 };
        int target = 20;

        List<List<Integer>> quadruplets = fs.fourSum(nums,target);

        for (List<Integer> quad : quadruplets){
            System.out.println(quad.toString());
        }

        int[] nums2 = {10,2,3,4,5,7,8};
        target = 23;

        quadruplets = fs.fourSum(nums2,target);

        for (List<Integer> quad : quadruplets){
            System.out.println(quad.toString());
        }

        int[] nums3 = {0,0,2,1,1};
        target = 3;

        quadruplets = fs.fourSum(nums3,target);

        for (List<Integer> quad : quadruplets){
            System.out.println(quad.toString());
        }

    }
}
