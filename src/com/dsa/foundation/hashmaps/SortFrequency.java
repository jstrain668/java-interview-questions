package com.dsa.foundation.hashmaps;

//Question: Sorting Elements of an Array by Frequency. Given an array A[] of integers, sort the array according to
//frequency of elements. That is elements that have higher frequency come first. If frequencies of two elements are same,
// then smaller number comes first.

//Reference: https://ao.ms/how-to-sort-array-by-frequency-in-java/

import java.util.*;

public class SortFrequency {


    //Assuming that the sorting algorithm takes O(n log(n)), where n is the number of elements in the input,
    // then method two takes O(n) + O(m log(m)), where m denotes the number of distinct elements in the input.
    public int[] sortBasedOnFrequencyAndValue(int[] nums){

        Map<Integer,Integer> map = new HashMap<>();

        for (int num : nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        Queue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                Integer freq1 = map.get(o1);
                Integer freq2 = map.get(o2);

                if (freq1 == freq2){
                    return o1.compareTo(o2);
                }

                return freq2.compareTo(freq1);
            }
        });

        for(int num : nums) {
            pq.add(num);
        }

        for(int i=0; i<nums.length; i++) {
            nums[i] = pq.poll();
        }

        return nums;
    }


    //Given an array of integers nums, sort the array in increasing order based on the frequency of the values. If
    // multiple values have the same frequency, sort them in decreasing order.
    // https://leetcode.com/problems/sort-array-by-increasing-frequency/
    public int[] sortBasedOnIncFrequencyAndValue(int[] nums){

        Map<Integer,Integer> map = new HashMap<>();

        for (int num : nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        Queue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                Integer freq1 = map.get(o1);
                Integer freq2 = map.get(o2);

                if (freq1 == freq2){
                    return o2.compareTo(o1);
                }

                return freq1.compareTo(freq2);
            }
        });

        for(int num : nums) {
            pq.add(num);
        }

        for(int i=0; i<nums.length; i++) {
            nums[i] = pq.poll();
        }

        return nums;
    }

    public static void main(String[] args) {
        SortFrequency sf = new SortFrequency();

        int[] nums = {2, 3, 5, 3, 7, 9, 5, 3, 7}; //Returns {3, 3, 3, 5, 5, 7, 7, 2, 9}

        System.out.println("Array sorted by frequency and value: "+Arrays.toString(sf.sortBasedOnFrequencyAndValue(nums)));
        System.out.println("Array sorted by inc frequency and value: "+Arrays.toString(sf.sortBasedOnIncFrequencyAndValue(nums)));

        int[] nums2 = { 2, 5, 2, 8, 5, 6, 8, 8 };
        System.out.println("Array sorted by frequency and value: "+Arrays.toString(sf.sortBasedOnFrequencyAndValue(nums2)));
        System.out.println("Array sorted by inc frequency and value: "+Arrays.toString(sf.sortBasedOnIncFrequencyAndValue(nums2)));
    }

}
