package com.dsa.foundation.arrays;


import java.util.Arrays;

public class ArrayOfProductsExceptItself {

    //Brute force solution with time complexity of O(n^2) where n is the length of the array
    public int[] productExceptSelf(int[] nums) {

        if (nums == null || nums.length == 0){
            return null;
        }

        int[] products = new int[nums.length];

        for (int i=0; i < nums.length; i++){
            products[i] = 1;
            for (int j=0; j < nums.length; j++){
                if (i!=j){
                    products[i] *= nums[j];
                }
            }
        }
        return products;
    }


    //Solution: We can distribute the input in left and right section, so that we can multiply left and right to get
    // our result.
    // Index: 0
    // Nums[Index] = 1 (default)
    // Left[Index] = 1
    // Right[Index] = 2 * 3 * 4 = 24
    // Result[Index] = Left[Index] * Right[Index] = 1 * 24 = 24
    // Index: 1
    // Nums[Index] = 2
    // Left[Index] = 1
    // Right[Index] = 3 * 4 = 12
    // Result[Index] = Left[Index] * Right[Index] = 1 * 12 = 12
    // Index: 2
    // Nums[Index] = 3
    // Left[Index] = 1 * 2 = 2
    // Right[Index] = 4
    // Result[Index] = Left[Index] * Right[Index] = 4 * 2 = 8
    // Index: 3
    // Nums[Index] = 4
    // Left[Index] = 1 * 2 * 3 = 6
    // Right[Index] = 1 (default)
    // Result[Index] = Left[Index] * Right[Index] = 6 * 1 = 6
    // Time Complexity: O(3n) = O(n)
    public int[] effProductExceptSelf(int[] nums) {

        if (nums == null || nums.length == 0){
            return null;
        }

        int[] left = new int[nums.length];
        left[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            left[i] = left[i - 1] * nums[i - 1];
        }

        int[] right = new int[nums.length];
        right[nums.length - 1] = 1;

        for (int i = nums.length - 2; i >= 0; i--) {
            right[i] = right[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < nums.length; i++) {
             nums[i] = left[i] * right[i];
        }
        return nums;
    }

    public static void main(String[] args) {

        ArrayOfProductsExceptItself ap = new ArrayOfProductsExceptItself();
        int[] nums = {1,2,3,4};
        int[] products = ap.productExceptSelf(nums);
        System.out.println("Original array: "+ Arrays.toString(nums));
        System.out.println("Products array: "+ Arrays.toString(products));
        products = ap.effProductExceptSelf(nums);
        System.out.println("Products array: "+ Arrays.toString(products));
    }
}
