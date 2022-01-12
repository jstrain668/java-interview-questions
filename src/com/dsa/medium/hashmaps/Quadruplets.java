package com.dsa.medium.hashmaps;

import java.util.*;

//Question:  Your task is to complete the function fourSum() which takes the array arr[] and the
// integer k as its input and returns an array containing all the quadruples in a lexicographical
// manner. Also note that all the quadruples should be internally sorted, ie for any quadruple
// [q1, q2, q3, q4] the following should follow: q1 <= q2 <= q3 <= q4.


//https://practice.geeksforgeeks.org/problems/find-all-four-sum-numbers1732/1#

public class Quadruplets {


    public List<List<Integer>> fourSum(int[] nums, int target) {

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

        List<List<Integer>> ret=new ArrayList<>();
        for(List<Integer> i:set){
            ret.add(i);
        }

        ret.sort(new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                if (o1.get(0) != o2.get(0)){
                    return o1.get(0).compareTo(o2.get(0));
                }
                if (o1.get(1) != o2.get(1)){
                    return o1.get(1).compareTo(o2.get(1));
                }
                if (o1.get(2) != o2.get(2)){
                    return o1.get(2).compareTo(o2.get(2));
                }
                return o1.get(3).compareTo(o2.get(3));
            }
        });
        return ret;
    }

    public static void main(String[] args) {
        Quadruplets q = new Quadruplets();
        int[] nums = { 2, 7, 4, 0, 9, 5, 1, 3 };
        int target = 20;

        List<List<Integer>> quadruplets = q.fourSum(nums,target);

        for (List<Integer> quad : quadruplets){
            System.out.println(quad.toString());
        }

        int[] nums2 = {10,2,3,4,5,7,8};
        target = 23;

        quadruplets = q.fourSum(nums2,target);

        for (List<Integer> quad : quadruplets){
            System.out.println(quad.toString());
        }

        int[] nums3 = {0,0,2,1,1};
        target = 3;

        quadruplets = q.fourSum(nums3,target);

        for (List<Integer> quad : quadruplets){
            System.out.println(quad.toString());
        }

    }
}
