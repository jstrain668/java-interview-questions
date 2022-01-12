package com.dsa.foundation.hashmaps;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Question: https://leetcode.com/problems/intersection-of-two-arrays/

public class ArrayIntersection {

    public int[] intersection(int[] nums1, int[] nums2) {

        if (nums1 == null || nums1.length ==0 || nums2 == null || nums2.length == 0){
            return new int[0];
        }

        Set<Integer> set1 = new HashSet<>();
        for (Integer n : nums1) {
            set1.add(n);
        }

        Set<Integer> set2 = new HashSet<>();
        for (Integer n : nums2) {
            set2.add(n);
        }

        set1.retainAll(set2);

        int [] output = new int[set1.size()];
        int idx = 0;
        for (int s : set1) {
            output[idx++] = s;
        }
        return output;
    }

    public static void main(String[] args) {
        ArrayIntersection ai = new ArrayIntersection();

        int[] nums1 = {3, 4, 2, 2, 4};
        int[] nums2 = {3, 2, 2, 7};

        int[] common = ai.intersection(nums1,nums2);
        System.out.println("Nums 1 array: "+ Arrays.toString(nums1));
        System.out.println("Nums 2 array: "+ Arrays.toString(nums2));
        System.out.println("Common elements: "+ Arrays.toString(common));
    }
}
