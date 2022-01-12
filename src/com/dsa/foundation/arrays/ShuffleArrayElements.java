package com.dsa.foundation.arrays;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShuffleArrayElements {

    public int[] shuffleArray(int[] nums){

        Random rand = new Random();

        for (int i = 0; i < nums.length; i++) {
            int randomIndexToSwap = rand.nextInt(nums.length);
            int temp = nums[randomIndexToSwap];
            nums[randomIndexToSwap] = nums[i];
            nums[i] = temp;
        }

        return nums;
    }

    public static void main(String[] args) {
        ShuffleArrayElements sae = new ShuffleArrayElements();

        Integer[] intArray = { 1, 2, 3, 4, 5, 6, 7 };

        List<Integer> intList = Arrays.asList(intArray);

        Collections.shuffle(intList);

        intList.toArray(intArray);

        System.out.println(Arrays.toString(intArray));

        int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
        System.out.println("Original array: "+Arrays.toString(nums));
        System.out.println("Shuffled array: "+Arrays.toString(sae.shuffleArray(nums)));


    }
}
