package com.dsa.foundation.hashmaps;

import java.util.HashMap;

//https://www.geeksforgeeks.org/check-if-an-array-can-be-divided-into-pairs-whose-sum-is-divisible-by-k/

public class PairSumDivisible {

    private static boolean canPair(int[] arr, int k){

        // An odd length array cannot be divided into pairs
        if (arr.length % 2 == 1)
            return false;

        // Create a frequency array to count occurrences
        // of all remainders when divided by k.
        HashMap<Integer, Integer> hm = new HashMap<>();

        // Count occurrences of all remainders
        for (int i = 0; i < arr.length; i++) {
            int rem = ((arr[i] % k) + k) % k;
            hm.put(rem, hm.getOrDefault(rem,0) + 1);
        }

        // Traverse input array and use freq[] to decide
        // if given array can be divided in pairs
        for (int i = 0; i < arr.length; i++) {
            // Remainder of current element
            int rem = ((arr[i] % k) + k) % k;

            // If remainder with current element divides
            // k into two halves.
            if (2 * rem == k) {
                // Then there must be even occurrences of
                // such remainder
                if (hm.get(rem) % 2 == 1)
                    return false;
            }

            // If remainder is 0, then there must be two
            // elements with 0 remainder
            else if (rem == 0) {
                // Then there must be even occurrences of
                // such remainder
                if (hm.get(rem) % 2 == 1)
                    return false;
            }

            // Else number of occurrences of remainder
            // must be equal to number of occurrences of
            // k - remainder
            else {
                if (hm.get(k - rem) != hm.get(rem))
                    return false;
            }
        }
        return true;

    }

    public static void main(String[] args) {
        PairSumDivisible psd = new PairSumDivisible();

        int[] nums = {9, 5, 7, 3};
        int k = 6;

        System.out.println("Is Divisible: "+psd.canPair(nums,6));

        int[] nums2 = {2, 4, 1, 3};
        k = 4;
        System.out.println("Is Divisible: "+psd.canPair(nums2,6));
    }
}
