package com.dsa.foundation.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Reference: https://leetcode.com/problems/intersection-of-two-arrays/

public class IntersectionOfTwoNumberLists {


    // Use the HashSet retainall function to provide intersection of two sets. Convert int to Integer while inserting
    // int[] into Set. Convert intersection set of Integers to ints when producing intersection int[] array.
    //There are built-in intersection facilities, which provide O(n+m) time complexity in the average case and
    // O(n×m) time complexity in the worst case when load factor is high enough.
    //Space complexity : \mathcal{O}(n + m)O(n+m) in the worst case when all elements in the arrays are different.
    public int[] intersection(int[] nums1, int[] nums2) {
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
        IntersectionOfTwoNumberLists intersectionOfTwoNumberLists = new IntersectionOfTwoNumberLists();
        int[] firstArray = {0,1,2,3,4,5,6,7,8,9};
        int[] secondArray = {1,3,5,7,9};

        int[] intersection = intersectionOfTwoNumberLists.intersection(firstArray,secondArray);

        System.out.println("Intersection list: "+ Arrays.toString(intersection));

    }
}
