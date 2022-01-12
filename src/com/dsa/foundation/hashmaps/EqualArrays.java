package com.dsa.foundation.hashmaps;

//Question: Given two arrays A and B of equal size N, the task is to find if given arrays
// are equal or not. Two arrays are said to be equal if both of them contain same set of
// elements, arrangements (or permutation) of elements may be different though.

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class EqualArrays {

    public boolean check(int[] A,int B[])
    {
        //Your code here
        Map<Integer,Integer> map1 = new HashMap<>();

        for (int num : A){
            map1.put(num,map1.getOrDefault(num,0)+1);
        }

        Map<Integer,Integer> map2 = new HashMap<>();

        for (int num : B){
            map2.put(num,map2.getOrDefault(num,0)+1);
        }

        if(map1.size() != map2.size()){
            return false;
        }

        for (var entry : map1.entrySet()){
            int key = entry.getKey();
            int count = entry.getValue();
            if (!map2.containsKey(key)){
                return false;
            }else {
                if (map2.get(key) != count){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        EqualArrays ea = new EqualArrays();
        int[] A = {1,2,5,4,0};
        int[] B = {2,4,5,0,1};

        System.out.println("Arrays are equal: "+ea.check(A,B));

        int[] C = {20,6,17,15,19};
        int[] D = {6,17,20,19,19};
        System.out.println("Arrays are equal: "+ea.check(C,D));

        int[] E = {16,1,2,14,13,17,1};
        int[] F = {14,1,16,2,13,2,17};
        System.out.println("Arrays are equal: "+ea.check(E,F));

    }
}
