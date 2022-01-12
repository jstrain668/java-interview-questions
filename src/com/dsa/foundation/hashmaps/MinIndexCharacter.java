package com.dsa.foundation.hashmaps;

import java.util.HashMap;
//Question: Given a string str and another string patt. Find the first position (considering 0-based indexing) of the
//character in patt that is present at the minimum index in str.

//https://www.geeksforgeeks.org/find-character-first-string-present-minimum-index-second-string/

public class MinIndexCharacter {

    public int findMinIndexChar(String str, String patt)
    {
        // map to store the first index of each character of 'str'
        HashMap<Character, Integer> hm = new HashMap<>();

        // to store the index of character having
        // minimum index
        int minIndex = Integer.MAX_VALUE;

        // lengths of the two strings
        int m = str.length();
        int n = patt.length();

        // store the first index of each character of 'str'
        for (int i = 0; i < m; i++)
            if(!hm.containsKey(str.charAt(i)))
                hm.put(str.charAt(i),i);

        // traverse the string 'patt'
        for (int i = 0; i < n; i++)
            // if patt[i] is found in 'hm', check if
            // it has the minimum index or not accordingly
            // update 'minIndex'
            if (hm.containsKey(patt.charAt(i)) &&
                    hm.get(patt.charAt(i)) < minIndex)
                minIndex = hm.get(patt.charAt(i));

        // print the minimum index character
        if (minIndex != Integer.MAX_VALUE)
            return minIndex;
            // if no character of 'patt' is present in 'str'
        else
            return -1;
    }

    public static void main(String[] args) {

        MinIndexCharacter mic = new MinIndexCharacter();
        String str = "geeksforgeeks";
        String patt = "set";
        System.out.println("Min index character found at "+mic.findMinIndexChar(str, patt));
    }
}
