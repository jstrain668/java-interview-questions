package com.dsa.foundation.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveEvens {

    public int[] removeEvenNumbers(int[] nums){

        if (nums == null || nums.length == 0){
            return new int[0];
        }

        List<Integer> odd = new ArrayList<>();

        for (int num : nums){
            if (num%2 != 0){
                odd.add(num);
            }
        }

        int[] ret = new int[odd.size()];

        int index = 0;
        for (Integer i : odd){
            ret[index++] = i;
        }
        return ret;
    }

    public void removeEven(int[] nums) {
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] % 2 != 0)
                nums[counter++] = nums[i];
        }

        while (counter < nums.length){
            nums[counter++] = 0;
        }
    }

    public static void main(String[] args) {
        RemoveEvens re = new RemoveEvens();
        int nums[] = {1,0,43,24,6,8,81,99};
        System.out.println("Original array: "+ Arrays.toString(nums));
        System.out.println("Odds array: "+ Arrays.toString(re.removeEvenNumbers(nums)));
        re.removeEven(nums);
        System.out.println("Odds array: "+ Arrays.toString(nums));
    }
}
