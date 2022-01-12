package com.dsa.foundation.hashmaps;

import java.util.HashMap;
import java.util.Map;

//Question and reference: https://www.geeksforgeeks.org/non-repeating-element/
//https://leetcode.com/problems/first-unique-character-in-a-string/

public class FirstNonRepeatingNumber {

    public int firstNonRepeating(int[] nums){
        Map<Integer,Integer> map = new HashMap<>();

        for (int num : nums){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        for (int num : nums){
            if (map.get(num) == 1){
                return num;
            }
        }

        return -1;
    }

    public int firstNonRepeatingAlt(String s){
        int[] alphabet = new int[26];

        for (int i=0; i < s.length(); i++){
            int index = s.charAt(i) - 'a';
            alphabet[index]++;
        }

        for (int i=0; i < s.length(); i++){
            int index = s.charAt(i) - 'a';
            if (alphabet[index] == 1){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        FirstNonRepeatingNumber first = new FirstNonRepeatingNumber();

        int arr[] = { 9, 4, 9, 6, 7, 4 };
        int n = arr.length;

        System.out.println(first.firstNonRepeating(arr));

        String s = "loveleetcode";
        System.out.println(first.firstNonRepeatingAlt(s));

    }
}
