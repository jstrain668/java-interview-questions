package com.dsa.foundation.hashmaps;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

//Question: Given two integer arrays, reorder elements of the first array by the order of elements defined by the second
// array. The elements that are not present in the second array but present in the first array should be appended at the
// end sorted. The second array can contain some extra elements which are not part of the first array.

//Reference: https://www.techiedelight.com/custom-sort-sort-elements-array-order-elements-defined-second-array/

//count the frequency of each element of the first array and store it in a hash table. Now for each element of the
// second array, check if the element is present in the map or not. If it is present on the map, print the element n
// number of times, where n is the frequency of that element in the first array. We also remove that element from the
// map so that we are only left with only present elements in the first array (but not present in the second array).
// To append them at the end, they need to be sorted.
//
//The keys are already ordered in TreeMap, but we get O(log(n)) insertion and retrieval time. If we use HashMap, we get
// O(1) insertion and retrieval time, but will require sorting since its keys are unordered.

public class RelativeSorting {

    public void customSort(int[] first, int[] second)
    {
        // map to store the frequency of each element of the first array
        Map<Integer, Integer> freq = new TreeMap<>();

        // find the frequency of each element of the first array and
        // store it in a map
        for (int i: first) {
            freq.put(i, freq.getOrDefault(i, 0) + 1);
        }

        // Note that once we have the frequencies of all elements of
        // the first array, we can overwrite elements of the first array

        int index = 0;

        // do for every element of the second array
        for (int i: second)
        {
            // If the current element is present on the map, print it `n` times
            // where `n` is the frequency of that element in the first array
            int n = freq.getOrDefault(i, 0);
            while (n-- > 0) {
                first[index++] = i;
            }

            // erase the element from the map
            freq.remove(i);
        }

        // Now we are left with elements only present in the first array,
        // but not in the second array.

        // Sort the remaining elements present on the map (Note that the keys are
        // already sorted since we are using `TreeMap`)

        for (var entry: freq.entrySet())
        {
            int count = entry.getValue();
            while (count-- > 0) {
                first[index++] = entry.getKey();
            }
        }
    }

    public static void main(String[] args)
    {
        RelativeSorting rs = new RelativeSorting();

        int[] first = { 5, 8, 9, 3, 5, 7, 1, 3, 4, 9, 3, 5, 1, 8, 4 };
        int[] second = { 3, 5, 7, 2 };

        rs.customSort(first, second);
        System.out.println("The array after sorting is " + Arrays.toString(first));
    }
}
