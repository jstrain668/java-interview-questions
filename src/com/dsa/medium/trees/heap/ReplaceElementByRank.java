package com.dsa.medium.trees.heap;

//Given an array of distinct integers, replace each array element by its corresponding rank in the array.

//he idea is to store each element’s index in an ordered map (Since the array contains all distinct integers, we can use
// array elements and their index as key-value pairs in the map). Since elements are stored in sorted order in an ordered
// map, if we iterate through the map, we get elements in increasing order. Therefore, for each element in increasing
// order, we start assigning values starting from number 1 to n.

import java.util.*;

public class ReplaceElementByRank {

    class Pair {
        int value;
        int index;
        public Pair(int value,int index){
            this.value = value;
            this.index = index;
        }
    }

    //The time complexity of the above solution is O(n.log(n)), where n is the size of the input. This assumes O(log(n))
    //time operation for std::map or TreeMap. The auxiliary space required by the program is O(n).
    public void transform(int[] nums){
        Map<Integer,Integer> map = new TreeMap<>();

        //Insert and sort keys in tree map
        for (int i=0; i < nums.length; i++){
            map.put(nums[i],i);
        }

        int rank = 1;
        for (Integer index : map.values()){
            nums[index] = rank++;
        }
    }

    //The time complexity of the above solution remains the same as the previous approach, i.e., O(n.log(n)). The
    // auxiliary space required by the program also remains O(n)

    public void transformAlt(int[] nums){

        Queue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return Integer.compare(o2.value, o1.value);
            }
        });

        //Insert and sort keys in tree map
        for (int i=0; i < nums.length; i++){
            pq.add(new Pair(nums[i],i));
        }

        int rank = nums.length;
        while (!pq.isEmpty()) {
            nums[pq.poll().value] = rank--;
        }

    }

    public static void main(String[] args)
    {
        ReplaceElementByRank replaceElementByRank = new ReplaceElementByRank();
        int[] input = { 10, 8, 15, 12, 6, 20, 1 };

        // transform the array
        replaceElementByRank.transform(input);

        // print the transformed array
        System.out.println(Arrays.toString(input));

        int[] input2 = { 10, 8, 15, 12, 6, 20, 1 };

        replaceElementByRank.transformAlt(input2);


        // print the transformed array
        System.out.println(Arrays.toString(input2));
    }
}
