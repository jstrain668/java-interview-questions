package com.dsa.foundation.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


//Reference: https://stackoverflow.com/questions/41181004/bitwise-xor-operator-to-find-missing-unique-id

public class NonRepeatingInteger {

    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public int firstUniqueInteger(int[] nums){

        if (nums == null || nums.length == 0){
            return -1;
        }

        Map<Integer,Integer> map = new HashMap<>();

        for (int i=0; i < nums.length; i++){
            int keyValue = nums[i];
            if (map.containsKey(keyValue)){
                map.put(keyValue, map.get(keyValue)+1);
            }else{
                map.put(keyValue,1);
            }
        }

        for (int i=0; i < nums.length; i++){
            int keyValue = nums[i];
            if (map.get(keyValue) == 1){
                return nums[i];
            }
        }
        return -1;
    }

    //Only works if there is a single unique number. XORing n numbers is like counting the number of 1 bits in each
    //position, and setting the corresponding output bit to 1 if the count is odd. The order in which you are XORing
    //them doesn't matter.
    //If an array contains x pairs of equal numbers and one unique number, the bits of the equal pairs cancel each other
    //(since they contribute an even number of 1s in each position), leaving you with just the bits of the unique number.
    //100100101
    //010110110
    //101101100 // the unique number
    //100100101
    //010110110
    //Count the number of 1s in each position:
    //321521522
    //Result of XOR (1 bit in for each odd count) : 101101100 which is the single unique number in the list.
    //Note: If there wasn't a unique number then zero is returned after executing last XOR
    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public int singleNumber(int[] nums){

        int number = nums[0];
        for (int i=1; i < nums.length; i++){
            number = number ^ nums[i];
        }

        return number;
    }

    public static void main(String[] args) {
        NonRepeatingInteger nri = new NonRepeatingInteger();
        int[] nums = {12,14,14,45,0,12,1,45,0};
        System.out.println("Original array: "+ Arrays.toString(nums));
        System.out.println("First unique integer: "+ nri.firstUniqueInteger(nums));

    }
}
