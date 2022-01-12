package com.dsa.foundation.hashmaps;

import java.util.HashMap;
import java.util.Map;

//Question: First element to occur k times

//Reference: https://practice.geeksforgeeks.org/problems/first-element-to-occur-k-times5150/1

public class FirstElementKTimes {

    public int firstElementKTime(int[] A, int k) {

        Map<Integer,Integer> map = new HashMap<>();
        for (int num : A){
            map.put(num,map.getOrDefault(num,0)+1);

            if (map.get(num) == k){
                return num;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        FirstElementKTimes kTimes = new FirstElementKTimes();
        int[] A = {1, 7, 4, 3, 4, 8, 7};
        int k = 2;
        System.out.println("First element occurs k times: "+kTimes.firstElementKTime(A,k));
    }
}
