package com.dsa.medium.hashmaps;

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

    //The time complexity of the above solution is O(m.log(m) + n), where m and n are the total number of elements in
    // the first and second array, respectively.
    public void customSort(int[] first, int[] second)
    {
        //Ordered Hashmap (TreeMap sorted in ascending order) for storing elements of 'first' and frequency
        Map<Integer,Integer> tMap = new TreeMap<>();

        //Add number and freq to TreeMap
        for (int num : first){
            tMap.put(num,tMap.getOrDefault(num,0)+1);
        }

        //index for setting sorted output in 'first'
        int index = 0;

        //Sort according to 'second' order
        for (int num : second){
            int n = tMap.getOrDefault(num,0);
            while (n-- > 0){
                first[index++] = num;
            }
            //remove entry from TreeMap
            tMap.remove(num);
        }

        //Set the remaining elements in 'first' in ascending order.
        for (var entry : tMap.entrySet()){
            int n = entry.getValue();
            while (n-- > 0){
                first[index++] = entry.getKey();
            }
        }
    }

    //The time complexity of the above solution is O(m.log(m) + n), where m and n are the total number of elements in
    // the first and second array, respectively.
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer,Integer> tMap = new TreeMap<>();

        for (int num : arr1){
            tMap.put(num,tMap.getOrDefault(num,0)+1);
        }

        int index = 0;
        for (int num : arr2){
            int freq = tMap.getOrDefault(num,0);
            while (freq-- > 0){
                arr1[index++] = num;
            }

            tMap.remove(num);
        }

        for (var entry : tMap.entrySet()){
            int freq = entry.getValue();
            while (freq-- > 0){
                arr1[index++] = entry.getKey();
            }
        }
        return arr1;
    }


    public static void main(String[] args)
    {
        RelativeSorting rs = new RelativeSorting();

        int[] first = { 5, 8, 9, 3, 5, 7, 1, 3, 4, 9, 3, 5, 1, 8, 4 };
        int[] second = { 3, 5, 7, 2 };

        rs.customSort(first, second);
        System.out.println("The array after sorting is " + Arrays.toString(first));

        int[] arr1 = {2,3,1,3,2,4,6,7,9,2,19};
        int[] arr2 = {2,1,4,3,9,6};
        System.out.println("The array after sorting is " + Arrays.toString(rs.relativeSortArray(arr1,arr2)));

    }
}
