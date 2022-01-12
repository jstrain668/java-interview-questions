package com.dsa.foundation.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class IntersectionOfArrays {


    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public int[] intersection(int[] nums1, int[] nums2){
        if (nums1 == null || nums1.length==0 || nums2 == null || nums2.length==0){
            return new int[0];
        }

        Set<Integer> set1 = new HashSet<>();
        for(Integer num : nums1){
            set1.add(num);
        }

        Set<Integer> set2 = new HashSet<>();
        for(Integer num : nums2){
            set2.add(num);
        }

        set1.retainAll(set2);

        int[] res = new int[set1.size()];
        int index = 0;
        for (Integer num: set1){
            res[index++] = num;
        }

        return res;
    }

    public static void main(String[] args) {
        IntersectionOfArrays ioa = new IntersectionOfArrays();
        int[] nums1 = {12,15,45,56,78,99,0,7};
        int[] nums2 = {1,3,0,99,44,15,88,10,11};
        int[] intersect = ioa.intersection(nums1,nums2);

        System.out.println("nums1 array: "+ Arrays.toString(nums1));
        System.out.println("nums1 array: "+ Arrays.toString(nums2));
        System.out.println("intersection array: "+ Arrays.toString(intersect));
    }
}
