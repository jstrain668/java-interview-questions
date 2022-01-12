package com.dsa.foundation.hashmaps;

import java.util.*;

//Question and Reference: https://www.geeksforgeeks.org/given-an-array-of-pairs-find-all-symmetric-pairs-in-it/


public class SymmetricPairs {

    class Pair{
        int x;
        int y;
        public Pair(int i,int j){
            x = i;
            y = j;
        }
    }

    public List<Pair> findSymPairs(int[][] arr){

        Map<Integer,Integer> map = new HashMap<>();
        List<Pair> result = new ArrayList<>();

        for (int[] pair : arr){

            int x = pair[0];
            int y = pair[1];

            //Does y match with an x of another pair?
            if (map.containsKey(y)){
                //Do we have a symmetric pair?
                int j = map.get(y);
                if (j == x){
                    result.add(new Pair(x,y));
                }
            }

            map.put(x,y);
        }
        return result;
    }

    public static void main(String[] args) {

        SymmetricPairs sp = new SymmetricPairs();

        int arr[][] = new int[5][2];
        arr[0][0] = 11; arr[0][1] = 20;
        arr[1][0] = 30; arr[1][1] = 40;
        arr[2][0] = 5;  arr[2][1] = 10;
        arr[3][0] = 40;  arr[3][1] = 30;
        arr[4][0] = 10;  arr[4][1] = 5;

        List<Pair> result = sp.findSymPairs(arr);

        if (result.size() == 0){
            System.out.println("No symmetric pairs found");
        } else{
            System.out.println("Following pairs have a symmetric pair:");
            for (Pair pair : result){
                System.out.print("("+pair.x+","+pair.y+") ");
            }
        }
    }
}
