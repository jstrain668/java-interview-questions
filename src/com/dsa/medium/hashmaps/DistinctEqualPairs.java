package com.dsa.medium.hashmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DistinctEqualPairs {

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
    public List<Pair[]> findPairs(int[] nums) {
        List< Pair[]> pairs = new ArrayList<>();
        Map<Integer,Pair> map = new HashMap<>();

        for (int i=0; i < nums.length; i++){
            for (int j=i+1; j < nums.length; j++){
                int sum = nums[i] + nums[j];

                if (map.containsKey(sum)){
                    Pair pair = map.get(sum);
                    if (pair.x != nums[i] && pair.y != nums[j]){
                        Pair[] twoPair = new Pair[2];
                        twoPair[0] = pair;
                        twoPair[1] = new Pair(nums[i],nums[j]);
                        pairs.add(twoPair);
                    }
                } else{
                    map.put(sum,new Pair(nums[i],nums[j]));
                }
            }
        }

        return pairs;
    }

    public static void main(String[] args) {
        DistinctEqualPairs tep = new DistinctEqualPairs();
        int[] nums = {3, 4, 7, 1, 2, 9, 8};

        List<Pair[]> pairs = tep.findPairs(nums);

        for (Pair[] pair : pairs){
            System.out.println("("+pair[0].x+","+pair[0].y+") and ("+pair[1].x+","+pair[1].y+")");
        }
    }
}
