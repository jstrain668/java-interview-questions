package com.dsa.foundation.hashmaps;

import java.util.HashSet;
import java.util.Set;

//Question: https://www.educative.io/courses/data-structures-coding-interviews-java/399kZ1rn1BO

public class IsDisjoint {

    //Runtime: O(n+m)
    //Space: O(n)
    public boolean isDisjoint(int[] A,int[] B){

        Set<Integer> set =  new HashSet<>();

        for (int num : A){
            set.add(num);
        }

        for (int num : B){
            if (set.contains(num)){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        IsDisjoint set = new IsDisjoint();
        int arr1[] = { 11, 1, 13, 21, 3, 7 };
        int arr2[] = { 12, 5, 9, 10 };

        if (set.isDisjoint(arr1, arr2))
            System.out.println("arr2[] is disjoint subset of arr1[]");
        else
            System.out.println("arr2[] is not a disjoint subset of arr1[]");

        int arr3[] = { 15, 11, 13, 21, 3, 7 };
        int arr4[] = { 12, 7, 9, 10 };

        if (set.isDisjoint(arr3, arr4))
            System.out.println("arr4[] is disjoint subset of arr3[] ");
        else
            System.out.println("arr4[] is not a disjoint subset of arr3[]");
    }
}
