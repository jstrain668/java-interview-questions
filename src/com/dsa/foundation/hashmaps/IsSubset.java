package com.dsa.foundation.hashmaps;

import java.util.HashSet;

public class IsSubset {

    public boolean isSubset(int[] A,int[] B){

        HashSet<Integer> set = new HashSet<>();

        for (int num : A){
            set.add(num);
        }

        for (int num : B){
            if (!set.contains(num)){
                return false;
            }
        }
        return true;
    }

    public boolean isSubsetAlt(int[] A,int[] B){

        HashSet<Integer> set1 = new HashSet<>();

        for (int num : A){
            set1.add(num);
        }

        int size = set1.size();

        for (int num : B){
            set1.add(num);
        }

        if (set1.size() == size){
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        IsSubset subSet = new IsSubset();
        int arr1[] = { 11, 1, 13, 21, 3, 7 };
        int arr2[] = { 11, 3, 7, 7 };

        if (subSet.isSubset(arr1, arr2))
            System.out.println("arr2[] is subset of arr1[] ");
        else
            System.out.println("arr2[] is not a subset of arr1[]");

        if (subSet.isSubsetAlt(arr1, arr2))
            System.out.println("arr2[] is subset of arr1[] ");
        else
            System.out.println("arr2[] is not a subset of arr1[]");
    }
}
