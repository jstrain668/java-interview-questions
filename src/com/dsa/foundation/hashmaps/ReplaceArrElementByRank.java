package com.dsa.foundation.hashmaps;

//Question: Given an array of distinct integers, replace each array element by its corresponding rank in the array.

//https://leetcode.com/problems/rank-transform-of-an-array/

//https://www.techiedelight.com/replace-each-element-corresponding-rank-array/

//The idea is to store each element’s index in an ordered map (Since the array contains all distinct integers, we can
// use array elements and their index as key-value pairs in the map). Since elements are stored in sorted order in an
// ordered map, if we iterate through the map, we get elements in increasing order. Therefore, for each element in
// increasing order, we start assigning values starting from number 1 to n.

import java.util.*;

public class ReplaceArrElementByRank {

    // Function to replace each array element by its rank in the array
    //The time complexity of the above solution is O(n.log(n)), where n is the size of the input. This assumes O(log(n))
    // time operation for std::map or TreeMap. The auxiliary space required by the program is O(n).
    public void transform(int[] input) {
        // create an empty `TreeMap`
        Map<Integer,Integer> map = new TreeMap<>();

        // store (element, index) pair in `TreeMap`
        for (int i = 0; i < input.length; i++) {
            map.put(input[i], i);
        }


        // keys are stored in sorted order in `TreeMap`

        // rank starts from 1
        int rank = 1;

        for (Map.Entry<Integer, Integer> ent : map.entrySet()) {
            input[ent.getValue()] = rank++;
        }
    }

    public int[] arrayRankTransform(int[] arr) {
        int nums[]=new int[arr.length];
        for(int i=0;i<nums.length;i++)
        {
            nums[i]=arr[i];

        }
        Arrays.sort(arr);
        HashMap<Integer,Integer> map= new HashMap<>();
        int k=1;
        for(int i=0;i<nums.length;i++)
        {
            if(!map.containsKey(arr[i]))
            {
                map.put(arr[i],k);
                k++;
            }
        }
        int res[]=new int[nums.length];
        for(int i=0;i<nums.length;i++)
        {
            res[i]=map.get(nums[i]);
        }
        return res;
    }

    public static void main(String[] args)
    {
        ReplaceArrElementByRank rankTransform = new ReplaceArrElementByRank();
        int[] input = { 10, 8, 15, 12, 6, 20, 1 };

        // transform the array
        rankTransform.transform(input);

        // print the transformed array
        System.out.println(Arrays.toString(input));

        int[] input2 = { 10, 8, 15, 12, 6, 20, 1 };
        int[] result = rankTransform.arrayRankTransform(input2);
        System.out.println(Arrays.toString(result));
    }


}
