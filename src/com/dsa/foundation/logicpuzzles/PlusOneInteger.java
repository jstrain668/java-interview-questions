package com.dsa.foundation.logicpuzzles;

import java.util.Arrays;

//Reference: https://leetcode.com/problems/plus-one/

public class PlusOneInteger {
    // Description: Process the addition of one to the least significant digit which is the last element
    // in the array. If the least significant digit (last element in the array) is not 9 then break out
    // of the loop after adding one to the last element in the array. Otherwise set value to zero and
    // continue with loop processing. If all digits are 9s then an additional digit is required to process
    // the last carry. A new array is required to cater for the additional digit.
    // Time Complexity: O(n) for worst case
    // Space Complexity: O(n) for worst case
    public int[] plusOne(int[] digits){

        for (int i=digits.length-1; i >= 0; i--){
            // If the ith digit is not 9 just add one. There is no carry to process
            if (digits[i] != 9){
                digits[i] += 1;
                break;
            } else {  // ith digit is 9, so value is 0. A carry of one needs to be processed
                digits[i] = 0;
            }
        }

        // If after adding one the most significant digit is zero then there is a carry that needs to
        // be processed which means an extra digit to be added to the array.
        // An extra digit requires an additional entry in the output array which as one + nine =0 with
        // a carry of one. The rest of the output array is zero.
        // Primitive int array initialised to zero

        if (digits[0] == 0) {
            int[] res = new int[digits.length + 1];
            res[0] = 1;
            return res;
        }

        return digits;
    }

    //Description: Convert digits into a string, by appending each digit in the int array into a
    //StringBuffer and then convert into an integer. Increment integer by 1. Create a new int[] array
    //if number of digits greater than length of original array. Convert integer into string and process
    // each character as a digit to be inserted into array
    // Time Complexity: O(n + n) or O(n + (n+1)) = O(n)
    // Space Complexity: O(1) however in worst case O(n) for new array
    public int[] plusOneAlt(int[] digits){
        if (digits == null || digits.length == 0){
            throw new IllegalArgumentException("Null or empty array passed");
        }

        StringBuffer sb = new StringBuffer();
        for (int i=0; i < digits.length; i++){
            sb.append(digits[i]);
        }

        Integer num = Integer.valueOf(sb.toString());
        num++;
        String number = String.valueOf(num);
        if (number.length() > digits.length) {
            digits = new int[number.length()];
        }

        for (int i=0; i <number.length(); i++){
            digits[i] = number.charAt(i) - '0';
        }

        return digits;
    }

    public static void main(String[] args) {
        PlusOneInteger poi = new PlusOneInteger();
        int[] digits = {1,2,3};
        System.out.println("Add one to source array: "+ Arrays.toString(digits)+" is "+Arrays.toString(poi.plusOne(digits)));
        digits = new int[] {1,2,3};
        System.out.println("Add one to source array: "+ Arrays.toString(digits)+" is "+Arrays.toString(poi.plusOneAlt(digits)));
    }
}
