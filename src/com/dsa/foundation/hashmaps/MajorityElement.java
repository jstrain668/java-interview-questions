package com.dsa.foundation.hashmaps;

import java.util.Arrays;
import java.util.HashMap;

//Reference: https://leetcode.com/problems/majority-element/

public class MajorityElement {


    //Description: Brute force approach of two loops of keeping track of all the max count
    //for each element.
    //Time Complexity: O(nxn)
    //Space Complexity: O(1)

    public int bfMajorityElement(int[] nums){
        
        if (nums == null || nums.length == 0){
            throw new IllegalArgumentException("Cannot have null or empty array");
        }
        
        int majorityElement = nums[0];
        int maxOccurrences = 0;
        
        for (int i=0; i < nums.length; i++){
            int numOccurrences = 0;
            for (int j=0; j < nums.length; j++){
                if (nums[i] == nums[j]){
                    numOccurrences++;
                }
            }
            if (numOccurrences > maxOccurrences){
                maxOccurrences = numOccurrences;
                majorityElement = nums[i];
            }

            if (maxOccurrences > (nums.length/2))
                return majorityElement;
        }
        return majorityElement;
    }

    //Description: Single for loop using a Hashmap to track the counts of each element. Max occurrence
    //count is tracked as well and the value returned if its number of occurrences is > array size /2
    //Time Complexity: O(n)
    //Space Complexity: O(n) for the Hashmap

    public int effMajorityElement(int[] nums) {

        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Cannot have null or empty array");
        }

        if (nums.length == 1){
            return nums[0];
        }

        HashMap<Integer, Integer> elementOccurrences = new HashMap<>();

        int maxOccurrences = 0;
        int majorityElement = nums[0];

        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            if (elementOccurrences.containsKey(key)){
                int count = elementOccurrences.get(key);
                elementOccurrences.put(key,count+1);

                if (count+1 > maxOccurrences){
                    maxOccurrences = count+1;
                    majorityElement = key;
                }
            } else {
                elementOccurrences.put(key,1);
            }

            if (maxOccurrences > (nums.length/2)){
                return majorityElement;
            }
        }

       // int maxOccurrences = 0;
       // int majorityElement = -1;

       // for (Integer key : elementOccurrences.keySet()) {
       //     int occurrences = elementOccurrences.get(key);
       //     if (occurrences > maxOccurrences){
       //         maxOccurrences = occurrences;
       //         majorityElement = key;
       //     }
       // }

        return -1;
    }

    //Loop through each element and maintains a count of majority element, and a majority index,
    //If the next element is same then increment the count if the next element is not same then
    //decrement the count. If the count reaches 0 then changes the maj_index to the current element
    // and set the count again to 1.
    private int findMajElement(int[] nums){

        int maj_index = 0, count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[maj_index] == nums[i])
                count++;
            else
                count--;
            if (count == 0) {
                maj_index = i;
                count = 1;
            }
        }
        return nums[maj_index];
    }

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public int majorityElement(int[] nums){

        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Cannot have null or empty array");
        }

        if (nums.length == 1){
            return nums[0];
        }

        return findMajElement(nums);
    }

    public static void main(String[] args) {
        MajorityElement me = new MajorityElement();
        int[] nums = new int[] {3,2,3};
        int[] nums1 = new int[] {2,2,1,1,1,2,2};
        System.out.println("Input array: "+ Arrays.toString(nums1));
        System.out.println("Majority element: "+ me.bfMajorityElement(nums1));
        System.out.println("Majority element: "+ me.effMajorityElement(nums1));
        System.out.println("Majority element: "+ me.majorityElement(nums1));
    }
}
