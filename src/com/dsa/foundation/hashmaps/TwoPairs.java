package com.dsa.foundation.hashmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Question: Find two pairs in an Array such that a+b = c+d

//Reference: https://www.geeksforgeeks.org/find-four-elements-a-b-c-and-d-in-an-array-such-that-ab-cd/

public class TwoPairs {

    class Pair{
        int x;
        int y;
        public Pair(int i,int j){
            x = i;
            y = j;
        }
    }

    //Generate all the possible 'sum's (i+j) and store it as a key in the Hashmap. If a 'sum' already exists then add to
    //'pairs list.
    //Time Complexity: O(n*n)
    //Space Complexity: O(n)
    public List<Pair> findPairs(int[] nums){
        List<Pair> pairs = new ArrayList<>();
        Map<Integer,Pair> map = new HashMap<>();

        for (int i=0; i < nums.length; i++){
            for (int j=i+1; j < nums.length; j++){
                int sum = nums[i] + nums[j];

                if (!map.containsKey(sum)){
                    map.put(sum,new Pair(i,j));
                }
                else {
                    Pair p = map.get(sum);
                    // Since array elements are distinct, we don't
                    // need to check if any element is common among pairs
                    System.out.println("("+nums[p.x]+", "+nums[p.y]+
                            ") and ("+nums[i]+", "+nums[j]+")");
                }

            }
        }
        return pairs;
    }

    public static void main(String[] args) {
        TwoPairs tp = new TwoPairs();
        int[] nums = {3, 4, 7, 1, 2, 9, 8};

        List<Pair> pairs = tp.findPairs(nums);

        for (Pair pair : pairs){
            System.out.println(pair.x+","+pair.y+" ");
        }
    }
}
